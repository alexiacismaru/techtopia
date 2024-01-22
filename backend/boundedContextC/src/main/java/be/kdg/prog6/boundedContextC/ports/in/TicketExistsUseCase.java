package be.kdg.prog6.boundedContextC.ports.in;

import be.kdg.prog6.boundedContextC.events.TicketExistsEvent;

public interface TicketExistsUseCase {
    void processTicket(TicketExistsEvent event);
}
