package be.kdg.prog6.boundedContextA.domain;

import java.time.LocalDateTime;

public record TicketActivity(TicketAction ticketAction, double price, LocalDateTime pit) {
}
