package be.kdg.prog6.boundedContextA.ports.in;

import be.kdg.prog6.boundedContextA.domain.Guest;
import be.kdg.prog6.boundedContextA.domain.TicketAction;

import java.time.LocalDateTime;

public record BuyTicketsAmountCommand(double price, TicketAction action, LocalDateTime start, LocalDateTime end, Guest.GuestUUID owner) {}
