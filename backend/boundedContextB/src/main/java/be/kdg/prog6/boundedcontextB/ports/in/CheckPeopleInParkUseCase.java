package be.kdg.prog6.boundedcontextB.ports.in;

import be.kdg.prog6.boundedcontextB.events.GateOpenedEvent;
import org.springframework.transaction.annotation.Transactional;

public interface CheckPeopleInParkUseCase {
    @Transactional
    void checkPeople(GateOpenedEvent event);
}
