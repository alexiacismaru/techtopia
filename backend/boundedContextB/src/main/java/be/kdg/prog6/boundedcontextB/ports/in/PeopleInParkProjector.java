package be.kdg.prog6.boundedcontextB.ports.in;

import be.kdg.prog6.boundedcontextB.domain.Park;
import be.kdg.prog6.boundedcontextB.events.GateOpenedEvent;

public interface PeopleInParkProjector {
    Park project(GateOpenedEvent event);
}
