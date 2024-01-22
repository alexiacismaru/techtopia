package be.kdg.prog6.boundedContextA.ports.out;

import be.kdg.prog6.boundedContextA.domain.Guest;
import be.kdg.prog6.boundedContextA.domain.Ticket;

import java.util.Optional;

public interface TicketLoadPort {
    Optional<Ticket> loadTicketForOwner(Guest.GuestUUID guestUUID);
}
