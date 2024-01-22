package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.adapters.out.AttractionProjectionJpaEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.AttractionRepository;
import be.kdg.prog6.boundedcontextB.adapters.out.RefreshmentStandProjectionJpaEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.RefreshmentStandProjectionRepository;
import be.kdg.prog6.boundedcontextB.ports.in.ManagePOIUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DefaultManageAttractionUseCase implements ManagePOIUseCase {
    private final AttractionRepository attractionRepository;
    private final RefreshmentStandProjectionRepository refreshmentStandProjectionRepository;

    @Override
    public AttractionProjectionJpaEntity getAttraction(long id) {
        return attractionRepository.findById(id).orElseThrow();
    }

    @Override
    public List<AttractionProjectionJpaEntity> getAllAttractions() {
        return attractionRepository.findAll();
    }

    @Override
    public void createAttraction(String name, String location, String description, String image, String tags, String ageGroup) {
        attractionRepository.save(new AttractionProjectionJpaEntity(name, description, location, ageGroup, image, tags));
    }

    @Override
    public void deleteAttraction(long id) {
        attractionRepository.deleteById(id);
    }

    @Override
    public List<RefreshmentStandProjectionJpaEntity> getAllRefreshmentStands() {
        return refreshmentStandProjectionRepository.findAll();
    }

    @Override
    public void createRefreshmentStand(String name, boolean isOpen, String image) {
        refreshmentStandProjectionRepository.save(new RefreshmentStandProjectionJpaEntity(name, isOpen, image));
    }

    @Override
    public RefreshmentStandProjectionJpaEntity getRefreshmentStand(long id) {
        return refreshmentStandProjectionRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteRefreshmentStand(long id) {
        refreshmentStandProjectionRepository.deleteById(id);
    }

    @Override
    public List<AttractionProjectionJpaEntity> getAttractionsByTags(String tags) {
        return attractionRepository.findByTags(tags);
    }
}
