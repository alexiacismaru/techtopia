package be.kdg.prog6.boundedContextA.ports.out;

import be.kdg.prog6.boundedContextA.domain.Ticket;

public interface TicketUpdatePort {
    void updateTicket(Ticket ticket);
}
