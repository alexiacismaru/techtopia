package be.kdg.prog6.boundedContextA.adapters.out.amqp;

import be.kdg.prog6.boundedContextA.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.boundedContextA.adapters.out.db.TicketActivityRepository;
import be.kdg.prog6.boundedContextA.adapters.out.db.TicketBoughtJpaEntity;
import be.kdg.prog6.boundedContextA.adapters.out.db.TicketJpaActivity;
import be.kdg.prog6.boundedContextA.adapters.out.db.TicketRepository;
import be.kdg.prog6.boundedContextA.domain.ActivityWindow;
import be.kdg.prog6.boundedContextA.domain.Guest;
import be.kdg.prog6.boundedContextA.domain.Ticket;
import be.kdg.prog6.boundedContextA.domain.TicketActivity;
import be.kdg.prog6.boundedContextA.events.TicketIsBoughtEvent;
import be.kdg.prog6.boundedContextA.ports.out.TicketActivityCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketLoadPort;
import be.kdg.prog6.boundedContextA.ports.out.TicketUpdatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventCatalog;
import events.EventHeader;
import events.EventMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class BoughtTicketDBAdapter implements TicketCreatePort, TicketUpdatePort, TicketLoadPort, TicketActivityCreatePort {
    public static final Logger log = LoggerFactory.getLogger(BoughtTicketDBAdapter.class);
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final TicketRepository ticketRepository;
    private final TicketActivityRepository ticketActivityRepository;

    @Override
    public void createTicket(Ticket ticket) {
        log.info("Buying ticket");
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.TICKET_BOUGHT_EVENT).build();
        var eventBody = new TicketIsBoughtEvent(ticket.getTicketUUID().uuid(), ticket.getStart(), ticket.getEnd());

        try {
            rabbitTemplate.convertAndSend(RabbitMQModuleTopology.TICKET_EVENTS_FAN_OUT, "ticket.bought", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(eventBody)).build());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateTicket(Ticket ticket) {
        TicketBoughtJpaEntity ticketJpaEntity = new TicketBoughtJpaEntity(ticket.getTicketUUID().uuid());
        ticketJpaEntity.setOwner(ticket.getOwner().uuid());
        ticketJpaEntity.setEnd(ticket.getEnd());
        ticketJpaEntity.setPrice(ticket.getPrice());
        ticketJpaEntity.setStart(ticket.getStart());
        ticketRepository.save(ticketJpaEntity);
    }

    @Override
    public Optional<Ticket> loadTicketForOwner(Guest.GuestUUID guestUUID) {
        Optional<TicketBoughtJpaEntity> ticketJpaEntity = ticketRepository.findByOwner(guestUUID.uuid());
        if (ticketJpaEntity.isEmpty()) {
            return Optional.empty();
        }
        Ticket ticket = new Ticket(new Ticket.TicketUUID(ticketJpaEntity.get().getUuid()), ticketJpaEntity.get().getStart(), ticketJpaEntity.get().getEnd(), ticketJpaEntity.get().getPrice(), new Guest.GuestUUID(ticketJpaEntity.get().getOwner()), new ActivityWindow());
        List<TicketJpaActivity> ticketJpaActivityList = null;
        if (ticketJpaEntity.get().getStart() != null) {
            ticketJpaActivityList = ticketActivityRepository.findByTicketAndPitGreaterThan(ticket.getTicketUUID().uuid(), ticketJpaEntity.get().getStart());
        } else {
            ticketJpaActivityList = ticketActivityRepository.findByTicket(ticket.getTicketUUID().uuid());
        }

        for (TicketJpaActivity ticketJpaActivity : ticketJpaActivityList) {
            ticket.addTicketActivity(new TicketActivity(ticketJpaActivity.getAction(), ticketJpaActivity.getPrice(), ticketJpaActivity.getPit()));
        }
        return Optional.of(ticket);
    }

    @Override
    public void createTicketActivity(Ticket.TicketUUID ticketUUID, TicketActivity ticketActivity) {
        TicketJpaActivity ticketJpaActivity = new TicketJpaActivity();
        ticketJpaActivity.setPrice(ticketActivity.price());
        ticketJpaActivity.setAction(ticketActivity.ticketAction());
        ticketJpaActivity.setTicket(ticketUUID.uuid());
        ticketJpaActivity.setPit(ticketActivity.pit());
        ticketActivityRepository.save(ticketJpaActivity);
    }
}
