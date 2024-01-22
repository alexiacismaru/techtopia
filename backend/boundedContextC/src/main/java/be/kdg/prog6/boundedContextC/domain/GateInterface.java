package be.kdg.prog6.boundedContextC.domain;

import java.util.List;

public interface GateInterface {
    boolean validateTicket(List<Ticket> tickets);
    void open();
}
