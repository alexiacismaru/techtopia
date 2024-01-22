package be.kdg.prog6.boundedContextC.core;

import be.kdg.prog6.boundedContextC.adapters.out.db.TicketJpaRepository;
import be.kdg.prog6.boundedContextC.domain.Gate;
import be.kdg.prog6.boundedContextC.domain.Ticket;
import be.kdg.prog6.boundedContextC.domain.ValidationOfTicketException;
import be.kdg.prog6.boundedContextC.events.ValidTicketIsScannedEvent;
import be.kdg.prog6.boundedContextC.ports.in.TicketScannedAtGateUseCase;
import be.kdg.prog6.boundedContextC.ports.out.GateOpenedOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DefaultGateTicketScannedUseCase implements TicketScannedAtGateUseCase {
    private TicketJpaRepository ticketJpaRepository;
    GateOpenedOutPort gateIsOpenOutPort;

    @Override
    public void checkTicket(Gate gate, List<Ticket> tickets) {
        try {
            gate.validateTicket(tickets);
            gate.open();
            tickets.stream().forEach(ticket -> ticketJpaRepository.updateIsValidByTicketUUID(!ticket.isValid(), ticket.getTicketUUID().uuid()));
            tickets.stream().forEach(ticket -> gateIsOpenOutPort.processGateEvent(new ValidTicketIsScannedEvent(gate.getId(), LocalDateTime.now(), gate.getGateAction())));
        } catch (ValidationOfTicketException e) {
            throw new ValidationOfTicketException("Ticket is not valid");
        }
    }
}
