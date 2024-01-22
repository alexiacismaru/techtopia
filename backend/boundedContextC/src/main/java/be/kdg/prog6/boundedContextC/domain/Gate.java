package be.kdg.prog6.boundedContextC.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
public class Gate implements GateInterface{
    private long id;
    private GateAction gateAction;

    public Gate(long id) {
        this.id = id;
    }

    @Override
    public boolean validateTicket(List<Ticket> tickets) {
        return false;
    }

    public Gate(GateAction gateAction) {
        this.gateAction = gateAction;
    }

    @Override
    public void open() {
        System.out.println("Open!");
    }

    public record GateUUID(UUID uuid) { }
}
