package be.kdg.prog6.boundedcontextB.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QueueProjectionJpaRepository extends JpaRepository<QueueProjectionJpaEntity, Long> {
}
