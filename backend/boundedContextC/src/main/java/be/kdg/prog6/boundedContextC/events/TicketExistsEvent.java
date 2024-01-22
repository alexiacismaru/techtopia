package be.kdg.prog6.boundedContextC.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketExistsEvent(UUID ticketUUID, LocalDateTime start, LocalDateTime end) {
}
