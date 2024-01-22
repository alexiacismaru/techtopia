package be.kdg.prog6.boundedContextA.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Ticket {
    private TicketUUID ticketUUID;
    private LocalDateTime start;
    private LocalDateTime end;
    private double price;
    private TicketAction ticketAction;
    private final Guest.GuestUUID owner;
    private ActivityWindow activityWindow;

    public record TicketUUID(UUID uuid) {
    }

    public Ticket(TicketUUID ticketUUID, Guest.GuestUUID owner) {
        this.ticketUUID = ticketUUID;
        this.owner = owner;
    }

    public Ticket(TicketUUID ticketUUID, LocalDateTime start, LocalDateTime end, double price, TicketAction ticketAction, Guest.GuestUUID owner) {
        this.ticketUUID = ticketUUID;
        this.start = start;
        this.end = end;
        this.price = price;
        this.ticketAction = ticketAction;
        this.owner = owner;
    }

    public Ticket(TicketUUID ticketUUID, LocalDateTime start, LocalDateTime end, double price, Guest.GuestUUID owner, ActivityWindow activityWindow) {
        this.ticketUUID = ticketUUID;
        this.start = start;
        this.end = end;
        this.price = price;
        this.owner = owner;
        this.activityWindow = activityWindow;
    }

    public void addTicketActivity(TicketActivity ticketActivity) {
        this.activityWindow.add(ticketActivity);
    }
}
