package be.kdg.prog6.boundedContextC.ports.in;

import be.kdg.prog6.boundedContextC.domain.Gate;
import be.kdg.prog6.boundedContextC.domain.Ticket;

import java.util.List;

public interface TicketScannedAtGateUseCase {
    void checkTicket (Gate gate, List<Ticket> tickets);
}
