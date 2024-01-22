package be.kdg.prog6.boundedcontextB.domain;

import lombok.*;

import java.time.Duration;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    private String name;
    private String location;
    private String description;
    private String image;
    private String ageGroup;
    private String tags;
    private AttractionUUID attractionUUID;
    private Queue queue;

    public record AttractionUUID(UUID uuid) { }

    public Attraction(String name, String location, String description, String image, String ageGroup, String tags) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.image = image;
        this.ageGroup = ageGroup;
        this.tags = tags;
    }
}
