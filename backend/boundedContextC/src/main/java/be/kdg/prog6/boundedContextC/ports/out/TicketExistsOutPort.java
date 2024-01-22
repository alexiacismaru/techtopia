package be.kdg.prog6.boundedContextC.ports.out;

import be.kdg.prog6.boundedContextC.domain.Ticket;

public interface TicketExistsOutPort {
    void saveTicket(Ticket ticket);
}
