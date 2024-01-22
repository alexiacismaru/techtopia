package be.kdg.prog6.boundedContextC.adapters.out.db;

import be.kdg.prog6.boundedContextC.domain.Ticket;
import be.kdg.prog6.boundedContextC.ports.out.TicketExistsOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TicketDBAdapter implements TicketExistsOutPort {
    TicketJpaRepository repository;
    @Override
    public void saveTicket(Ticket ticket) {
        repository.save(new TicketJpaEntity(ticket.getTicketUUID().uuid(), ticket.getStart(),ticket.getEnd(),ticket.isValid()));
    }
}
