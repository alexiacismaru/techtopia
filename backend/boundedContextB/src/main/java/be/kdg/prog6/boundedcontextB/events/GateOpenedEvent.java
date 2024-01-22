package be.kdg.prog6.boundedcontextB.events;

import be.kdg.prog6.boundedcontextB.domain.GateAction;

import java.time.LocalDateTime;

public record GateOpenedEvent(Long gateId, GateAction gateAction, LocalDateTime pit) implements Event{
}
