package be.kdg.prog6.boundedContextA.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketIsBoughtEvent(UUID uuid, LocalDateTime start, LocalDateTime end) {
}
