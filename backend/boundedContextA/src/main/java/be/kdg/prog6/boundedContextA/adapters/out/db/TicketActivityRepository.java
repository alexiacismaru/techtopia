package be.kdg.prog6.boundedContextA.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TicketActivityRepository extends JpaRepository<TicketJpaActivity, UUID> {
    List<TicketJpaActivity> findByTicket(UUID ticketUUID);

    List<TicketJpaActivity> findByTicketAndPitBetween(UUID ticketUUID, LocalDateTime start, LocalDateTime end);

    List<TicketJpaActivity> findByTicketAndPitGreaterThan(UUID ticketUUID, LocalDateTime from);
}

