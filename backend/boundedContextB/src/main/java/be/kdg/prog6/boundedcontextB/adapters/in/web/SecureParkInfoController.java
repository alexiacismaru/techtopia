package be.kdg.prog6.boundedcontextB.adapters.in.web;

import be.kdg.prog6.boundedcontextB.adapters.out.AttractionProjectionJpaEntity;
import be.kdg.prog6.boundedcontextB.adapters.out.RefreshmentStandProjectionJpaEntity;
import be.kdg.prog6.boundedcontextB.messages.SecuredMessage;
import be.kdg.prog6.boundedcontextB.ports.in.ManagePOIUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SecureParkInfoController {
    private ManagePOIUseCase manageAttractionsUseCase;

    @GetMapping("/attractions")
    public List<AttractionProjectionJpaEntity> getSecureAttractions(){
        return manageAttractionsUseCase.getAllAttractions();
    }

    @GetMapping("/attractions/{id}")
    private AttractionProjectionJpaEntity getAttraction(@PathVariable long id) {
        return manageAttractionsUseCase.getAttraction(id);
    }

    @PostMapping("/addAttraction")
    public void createAttraction(@RequestBody AttractionProjectionJpaEntity attraction) {
        manageAttractionsUseCase.createAttraction(attraction.getName(), attraction.getLocation(), attraction.getDescription(), attraction.getImage(), attraction.getTags(), attraction.getAgeGroup());
    }

    @DeleteMapping("/attractions/{id}")
    public void deleteAttraction(@PathVariable long id) {
        manageAttractionsUseCase.deleteAttraction(id);
    }

    @GetMapping("/refreshmentStands")
    public List<RefreshmentStandProjectionJpaEntity> getSecureRefreshmentStands(){
        return manageAttractionsUseCase.getAllRefreshmentStands();
    }

    @PostMapping("/addRefreshmentStand")
    public void createRefreshmentStand(@RequestBody RefreshmentStandProjectionJpaEntity refreshmentStand) {
        manageAttractionsUseCase.createRefreshmentStand(refreshmentStand.getName(), refreshmentStand.isOpen(), refreshmentStand.getImage());
    }

    @GetMapping("/refreshmentStands/{id}")
    private RefreshmentStandProjectionJpaEntity getRefreshmentStand(@PathVariable long id) {
        return manageAttractionsUseCase.getRefreshmentStand(id);
    }

    @DeleteMapping("/refreshmentStands/{id}")
    public void deleteRefreshmentStand(@PathVariable long id) {
        manageAttractionsUseCase.deleteRefreshmentStand(id);
    }

    @GetMapping("/attractions/tags/{tags}")
    public List<AttractionProjectionJpaEntity> getAttractionsByTags(@PathVariable String tags){
        return manageAttractionsUseCase.getAttractionsByTags(tags);
    }


    private static SecuredMessage getInfoFromToken(Jwt token, String message){
        return SecuredMessage.builder()
                .subjectId(token.getClaimAsString("sub"))
                .username(token.getClaimAsString("username"))
                .email(token.getClaimAsString("email"))
                .password(token.getClaimAsString("password"))
                .message(message)
                .build();
    }
}
