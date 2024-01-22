package be.kdg.prog6.boundedContextA.ports.out;

import be.kdg.prog6.boundedContextA.domain.Ticket;

public interface TicketCreatePort {
    void createTicket(Ticket ticket);
}
