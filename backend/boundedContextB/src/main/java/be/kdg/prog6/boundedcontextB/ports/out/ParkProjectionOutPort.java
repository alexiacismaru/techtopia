package be.kdg.prog6.boundedcontextB.ports.out;

import be.kdg.prog6.boundedcontextB.domain.Park;

public interface ParkProjectionOutPort {
    void savePark(Park park);
}
