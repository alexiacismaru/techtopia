package be.kdg.prog6.boundedcontextB.ports.in;

import be.kdg.prog6.boundedcontextB.adapters.out.AttractionProjectionJpaEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.RefreshmentStandProjectionJpaEntity;

import java.util.List;

public interface ManagePOIUseCase {
    AttractionProjectionJpaEntity getAttraction(long id);
    List<AttractionProjectionJpaEntity> getAllAttractions();
    void createAttraction(String name, String location, String description, String image,  String tags, String ageGroup);
    void deleteAttraction(long id);
    List<RefreshmentStandProjectionJpaEntity> getAllRefreshmentStands();
    void createRefreshmentStand(String name, boolean isOpen, String image);
    RefreshmentStandProjectionJpaEntity getRefreshmentStand(long id);
    void deleteRefreshmentStand(long id);
    List<AttractionProjectionJpaEntity> getAttractionsByTags(String tags);
}
