package be.kdg.prog6.boundedContextA.adapters.out.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketBoughtJpaEntity, UUID> {
    Optional<TicketBoughtJpaEntity> findByOwner(UUID uuid);
}
