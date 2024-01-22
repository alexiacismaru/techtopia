package be.kdg.prog6.boundedContextC.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Ticket {
    private LocalDateTime start;
    private LocalDateTime end;
    private TicketUUID ticketUUID;
    public Ticket(TicketUUID ticketUUID) {
        this.ticketUUID = ticketUUID;
    }
    public record TicketUUID(UUID uuid) { }
    private boolean isValid;

    public Ticket(TicketUUID ticketUUID, LocalDateTime start, LocalDateTime end) {
        this.ticketUUID = ticketUUID;
        this.start = start;
        this.end = end;
        this.isValid = false;
    }
}
