package be.kdg.prog6.boundedContextA.ports.in;

import be.kdg.prog6.boundedContextA.domain.Guest;

public record GiveMoneyCommand(double amount, Guest.GuestUUID guestUUID) { }
