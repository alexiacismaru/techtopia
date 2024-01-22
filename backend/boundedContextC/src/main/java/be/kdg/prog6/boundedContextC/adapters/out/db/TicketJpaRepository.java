package be.kdg.prog6.boundedContextC.adapters.out.db;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TicketJpaRepository extends JpaRepository<TicketJpaEntity, UUID> {
    @Transactional
    @Modifying
    @Query("update TicketJpaEntity t set t.isValid = ?1 where t.ticketUUID = ?2")
    void updateIsValidByTicketUUID(boolean isIn, UUID ticketUUID);
}
