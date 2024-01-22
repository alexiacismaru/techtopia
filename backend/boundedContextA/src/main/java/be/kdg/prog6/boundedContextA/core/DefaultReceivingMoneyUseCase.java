package be.kdg.prog6.boundedContextA.core;

import be.kdg.prog6.boundedContextA.domain.Guest;
import be.kdg.prog6.boundedContextA.domain.Ticket;
import be.kdg.prog6.boundedContextA.domain.TicketActivity;
import be.kdg.prog6.boundedContextA.ports.in.GiveMoneyCommand;
import be.kdg.prog6.boundedContextA.ports.in.ReceivingMoneyUseCase;
import be.kdg.prog6.boundedContextA.ports.out.TicketActivityCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketLoadPort;
import be.kdg.prog6.boundedContextA.ports.out.TicketUpdatePort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DefaultReceivingMoneyUseCase implements ReceivingMoneyUseCase {
    private final TicketUpdatePort ticketUpdatePort;
    private final TicketCreatePort ticketCreatePort;
    private final TicketLoadPort ticketLoadPort;
    private final List<TicketActivityCreatePort> ticketActivityCreatePorts;
    @Override
    @Transactional
    public void receiveMoney(GiveMoneyCommand giveMoneyCommand) {
        Guest randGuest = new Guest("Random", "Guest", new Guest.GuestUUID(UUID.randomUUID()));
        Optional<Ticket> optionalTicket = ticketLoadPort.loadTicketForOwner(giveMoneyCommand.guestUUID());
        Ticket ticket;
        if (optionalTicket.isEmpty()) {
            ticket = new Ticket(new Ticket.TicketUUID(UUID.randomUUID()), randGuest.getSso());
            ticketCreatePort.createTicket(ticket);
        } else {
            ticket = optionalTicket.get();
        }
        ticketUpdatePort.updateTicket(ticket);
        TicketActivity activity = randGuest.giveMoney(giveMoneyCommand.amount());
        ticketActivityCreatePorts.forEach(port -> port.createTicketActivity(ticket.getTicketUUID(), activity));

    }
}

