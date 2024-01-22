package events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.UUID;

public class EventHeader {

    private UUID eventID;
    private EventCatalog eventCatalog;


    public EventHeader() {
    }

    public EventHeader(UUID eventID, EventCatalog eventCatalog) {
        this.eventID = eventID;
        this.eventCatalog = eventCatalog;
    }

    private EventHeader(Builder builder) {
        setEventID(builder.eventID);
        eventCatalog = builder.eventCatalog;
    }


    public UUID getEventID() {
        return eventID;
    }

    public void setEventID(UUID eventID) {
        this.eventID = eventID;
    }

    public EventCatalog getEventType() {
        return eventCatalog;
    }

    @JsonIgnore
    public static Builder builder() {
        return new Builder();
    }

    public void setEventType(EventCatalog eventCatalog) {
        this.eventCatalog = eventCatalog;
    }

    @JsonIgnoreType
    public static final class Builder {
        private UUID eventID;

        private EventCatalog eventCatalog;

        private Builder() {
        }

        public Builder eventID(UUID val) {
            eventID = val;
            return this;
        }

        public Builder eventCatalog(EventCatalog val) {
            eventCatalog = val;
            return this;
        }

        public EventHeader build() {
            return new EventHeader(this);
        }
    }

    @Override
    public String toString() {
        return "EventHeader{" +
                "eventID=" + eventID +
                ", eventCatalog=" + eventCatalog +
                '}';
    }
}
