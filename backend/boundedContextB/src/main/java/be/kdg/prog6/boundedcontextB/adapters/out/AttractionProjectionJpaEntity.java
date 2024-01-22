package be.kdg.prog6.boundedcontextB.adapters.out;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "boundedContextB", name = "boundedContextB.attractionprojection")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AttractionProjectionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String location;
    @Column
    private String ageGroup;
    @Column
    private String image;
    @Column
    private String tags;

    public AttractionProjectionJpaEntity(String name, String description, String location, String ageGroup, String image, String tags) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.ageGroup = ageGroup;
        this.image = image;
        this.tags = tags;
    }
}
