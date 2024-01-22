package be.kdg.prog6.boundedcontextB.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionRepository extends JpaRepository<AttractionProjectionJpaEntity, Long> {
    List<AttractionProjectionJpaEntity> findByTags(String tags);
}
