package be.kdg.prog6.boundedContextA.ports.out;

import be.kdg.prog6.boundedContextA.domain.Ticket;
import be.kdg.prog6.boundedContextA.domain.TicketActivity;


public interface TicketActivityCreatePort {
    void createTicketActivity(Ticket.TicketUUID ticketUUID, TicketActivity ticketActivity);
}
