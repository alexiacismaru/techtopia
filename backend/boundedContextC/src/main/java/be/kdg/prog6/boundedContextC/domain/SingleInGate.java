package be.kdg.prog6.boundedContextC.domain;

import java.time.LocalDateTime;
import java.util.List;

public class SingleInGate extends Gate implements GateInterface {

    public SingleInGate(Long id) {
        super(id);
        setGateAction(GateAction.IN);
    }

    @Override
    public boolean validateTicket(List<Ticket> tickets) {
        if (tickets.size() != 1) {
            throw new ValidationOfTicketException("Cannot only processes 1 ticket!");

        } else {
            Ticket ticket = tickets.get(0);
            if (LocalDateTime.now().isAfter(ticket.getEnd()) || LocalDateTime.now().isBefore(ticket.getStart())) {
                throw new ValidationOfTicketException("This ticket is not valid for this date");

            } else if (ticket.isValid()) {
                throw new ValidationOfTicketException("This ticket was already used to go inside!");

            } else {
                return true;
            }
        }
    }

    @Override
    public void open() {
        System.out.println("Single in gate open!");
    }
}
