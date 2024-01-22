package be.kdg.prog6.boundedContextC.core;

import be.kdg.prog6.boundedContextC.domain.Ticket;
import be.kdg.prog6.boundedContextC.events.TicketExistsEvent;
import be.kdg.prog6.boundedContextC.ports.in.TicketExistsUseCase;
import be.kdg.prog6.boundedContextC.ports.out.TicketExistsOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DefaultTicketExistsUseCase implements TicketExistsUseCase {
    private TicketExistsOutPort port;
    @Override
    public void processTicket (TicketExistsEvent event) {
        Ticket ticket = new Ticket(new Ticket.TicketUUID(event.ticketUUID()), event.start(), event.end());
        port.saveTicket(ticket);
    }
}
