package be.kdg.prog6.boundedcontextB.adapters.out;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(schema = "boundedContextB", name = "boundedContextB.refreshmentstandprojection")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RefreshmentStandProjectionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private boolean isOpen;
    @Column
    private String image;

    public RefreshmentStandProjectionJpaEntity(String name, boolean isOpen, String image) {
        this.name = name;
        this.isOpen = isOpen;
        this.image = image;
    }
}
