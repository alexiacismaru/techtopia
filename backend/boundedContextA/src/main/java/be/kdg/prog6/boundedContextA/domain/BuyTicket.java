package be.kdg.prog6.boundedContextA.domain;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class BuyTicket {
    public Ticket buyTicket(TicketAction ticketAction, LocalDateTime start, LocalDateTime end, double price, Guest.GuestUUID owner) {
        return new Ticket(new Ticket.TicketUUID(UUID.randomUUID()), start, end, price, ticketAction, owner);
    }
}
