package be.kdg.prog6.boundedcontextB.adapters.in;

import be.kdg.prog6.boundedcontextB.events.Event;
import events.EventCatalog;
import events.EventMessage;

public interface ParkEventHandler<T extends Event> {

    boolean appliesTo(EventCatalog eventCatalog);

    default ParkEventHandler<T> receive(EventMessage eventMessage) {
        //check if this is a duplicate message in the eventstore
        //if not handle the event
        return this;
    }

    Event map(String eventBody);

    void handle(Event eventBody);


}
