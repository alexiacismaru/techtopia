package facades;

import java.time.LocalDate;
import java.util.UUID;

public record CreateAppointmentCommand(UUID guestUUID, LocalDate date) {
}
