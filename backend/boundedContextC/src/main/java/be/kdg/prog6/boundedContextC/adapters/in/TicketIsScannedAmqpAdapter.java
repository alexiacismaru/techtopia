package be.kdg.prog6.boundedContextC.adapters.in;

import be.kdg.prog6.boundedContextC.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.boundedContextC.events.TicketExistsEvent;
import be.kdg.prog6.boundedContextC.ports.in.TicketExistsUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TicketIsScannedAmqpAdapter {
    public static final Logger log = LoggerFactory.getLogger(TicketIsScannedAmqpAdapter.class);
    private final TicketExistsUseCase ticketExists;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQModuleTopology.TICKET_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void handelTicketCreated(EventMessage eventMessage) {
        log.info(eventMessage.toString());
        eventMessage.getEventBody();
        try {
            TicketExistsEvent ticketExistsEvent = objectMapper.readValue(eventMessage.getEventBody(), TicketExistsEvent.class);
            ticketExists.processTicket(ticketExistsEvent);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
