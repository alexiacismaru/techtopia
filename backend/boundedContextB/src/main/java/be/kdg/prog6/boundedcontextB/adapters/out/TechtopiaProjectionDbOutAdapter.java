package be.kdg.prog6.boundedcontextB.adapters.out;

import be.kdg.prog6.boundedcontextB.domain.Park;
import be.kdg.prog6.boundedcontextB.ports.out.ParkProjectionOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TechtopiaProjectionDbOutAdapter implements ParkProjectionOutPort {
    TechtopiaProjectionJpaRepository repository;
    @Override
    public void savePark(Park park) {
        Optional<TechtopiaProjectionJpaEntity> parkProjectionJpaEntity = repository.findById(1L);
        if (parkProjectionJpaEntity.isPresent()) {
            TechtopiaProjectionJpaEntity toUpdate = parkProjectionJpaEntity.get();
            toUpdate.setTotalPeopleInside(park.getTotalPeopleInside());
            repository.save(toUpdate);
        } else {
            TechtopiaProjectionJpaEntity toCreate = new TechtopiaProjectionJpaEntity();
            toCreate.setTotalPeopleInside(park.getTotalPeopleInside());
            toCreate.setId(1L);
            repository.save(toCreate);
        }
    }
}
