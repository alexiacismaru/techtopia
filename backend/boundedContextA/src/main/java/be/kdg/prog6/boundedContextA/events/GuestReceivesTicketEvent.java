package be.kdg.prog6.boundedContextA.events;

import java.util.UUID;

public record GuestReceivesTicketEvent(UUID owner, String fullName, UUID ticketUUID) {
}
