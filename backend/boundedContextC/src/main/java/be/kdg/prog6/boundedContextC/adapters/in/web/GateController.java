package be.kdg.prog6.boundedContextC.adapters.in.web;

import be.kdg.prog6.boundedContextC.adapters.out.db.TicketJpaEntity;
import be.kdg.prog6.boundedContextC.adapters.out.db.TicketJpaRepository;
import be.kdg.prog6.boundedContextC.domain.*;
import be.kdg.prog6.boundedContextC.ports.in.TicketScannedAtGateUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gate")
public class GateController {
    List<Gate> gateList = initializeGates();

    public static List<Gate> initializeGates() {
        List<Gate> gates = new ArrayList<>();

        SingleInGate singleInGate = new SingleInGate(0L);
        SingleOutGate singleOutGate = new SingleOutGate(1L);

        MultipleOutGate multipleOutGate = new MultipleOutGate(2L);
        MultipleInGate multipleInGate = new MultipleInGate(3L);

        gates.add(singleInGate);
        gates.add(singleOutGate);
        gates.add(multipleOutGate);
        gates.add(multipleInGate);

        return gates;
    }

    TicketJpaRepository ticketJpaRepository;
    TicketScannedAtGateUseCase ticketScannedAtGateUseCase;

    public GateController(TicketJpaRepository ticketJpaRepository, TicketScannedAtGateUseCase ticketScannedAtGateUseCase) {
        this.ticketJpaRepository = ticketJpaRepository;
        this.ticketScannedAtGateUseCase = ticketScannedAtGateUseCase;
    }

    @PostMapping("/{id}")
    public void scanTicket(@PathVariable Long id, @RequestBody List<UUID> requestBody) {

        Gate gate = gateList.get(id.intValue());
        List<Ticket> tickets = new ArrayList<>();
        for (UUID uuid : requestBody
        ) {
            TicketJpaEntity entity = ticketJpaRepository.findById(uuid).get();
            tickets.add(new Ticket(entity.getEnd(), entity.getStart(), new Ticket.TicketUUID(entity.getTicketUUID()), entity.isValid()));
        }
        ticketScannedAtGateUseCase.checkTicket(gate, tickets);
    }
}
