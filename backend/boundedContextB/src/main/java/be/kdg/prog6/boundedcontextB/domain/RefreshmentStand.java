package be.kdg.prog6.boundedcontextB.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RefreshmentStand {
    private String name;
    private boolean isOpen;
    private String image;
}
