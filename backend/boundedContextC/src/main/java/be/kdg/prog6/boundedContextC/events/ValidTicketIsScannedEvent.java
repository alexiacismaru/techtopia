package be.kdg.prog6.boundedContextC.events;

import be.kdg.prog6.boundedContextC.domain.GateAction;

import java.time.LocalDateTime;

public record ValidTicketIsScannedEvent(long gateUUID, LocalDateTime pit, GateAction action) {
}
