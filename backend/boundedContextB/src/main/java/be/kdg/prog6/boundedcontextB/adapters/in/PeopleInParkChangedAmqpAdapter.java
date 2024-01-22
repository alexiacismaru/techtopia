package be.kdg.prog6.boundedcontextB.adapters.in;

import be.kdg.prog6.boundedcontextB.events.Event;
import be.kdg.prog6.boundedcontextB.events.GateOpenedEvent;
import be.kdg.prog6.boundedcontextB.ports.in.CheckPeopleInParkUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventCatalog;
import org.springframework.stereotype.Component;

@Component
public class PeopleInParkChangedAmqpAdapter implements ParkEventHandler<GateOpenedEvent> {

    private final CheckPeopleInParkUseCase checkPeopleInParkUseCase;
    private final ObjectMapper objectMapper;

    public PeopleInParkChangedAmqpAdapter(CheckPeopleInParkUseCase checkPeopleInParkUseCase, ObjectMapper objectMapper) {
        this.checkPeopleInParkUseCase = checkPeopleInParkUseCase;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean appliesTo(EventCatalog eventCatalog) {
        return EventCatalog.GATE_IS_OPEN_EVENT == eventCatalog;
    }

    @Override
    public GateOpenedEvent map(String eventBody) {
        System.out.println("Handling event: " + eventBody);
        try {
            return objectMapper.readValue(eventBody, GateOpenedEvent.class);
        } catch (JsonProcessingException e) {
            throw new HandlingException(e);
        }
    }

    @Override
    public void handle(Event eventBody) {
        checkPeopleInParkUseCase.checkPeople((GateOpenedEvent) eventBody);
    }
}

