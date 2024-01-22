package be.kdg.prog6.boundedContextC.ports.out;

import be.kdg.prog6.boundedContextC.events.ValidTicketIsScannedEvent;

public interface GateOpenedOutPort {
    void processGateEvent(ValidTicketIsScannedEvent validTicketIsScannedEvent);
}
