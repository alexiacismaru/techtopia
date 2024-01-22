package be.kdg.prog6.boundedContextC.domain;

import java.time.LocalDateTime;
import java.util.List;

public class MultipleInGate extends Gate implements GateInterface {

    public MultipleInGate(Long id) {
        super(id);
        setGateAction(GateAction.IN);
    }

    @Override
    public boolean validateTicket(List<Ticket> tickets) {
        if (tickets.size() != 2) {
            throw new ValidationOfTicketException("Can only processes 2 tickets!");
        } else {
            for (Ticket ticket : tickets) {
                if (LocalDateTime.now().isAfter(ticket.getEnd()) || LocalDateTime.now().isBefore(ticket.getStart())) {
                    throw new ValidationOfTicketException("This ticket is not valid for this date");

                }
                else if (ticket.isValid()) {
                    throw new ValidationOfTicketException("This ticket was already used to go inside!");

                }
            }
            return true;
        }
    }

    @Override
    public void open() {
        System.out.println("Multiple out gate open!");
    }
}
