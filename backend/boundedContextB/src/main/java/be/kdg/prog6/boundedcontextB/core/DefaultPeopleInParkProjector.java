package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.domain.Park;
import be.kdg.prog6.boundedcontextB.events.GateOpenedEvent;
import be.kdg.prog6.boundedcontextB.ports.in.PeopleInParkProjector;
import be.kdg.prog6.boundedcontextB.ports.out.ParkProjectionOutPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DefaultPeopleInParkProjector implements PeopleInParkProjector {
    private ParkProjectionOutPort parkProjectionOutPort;

    @Override
    @Transactional
    public Park project(GateOpenedEvent event) {
        Park park = Park.getInstance();

        switch (event.gateAction()) {
            case IN -> park.setTotalPeopleInside(park.getTotalPeopleInside() + 1);
            case OUT -> park.setTotalPeopleInside(park.getTotalPeopleInside() - 1);
        }
        parkProjectionOutPort.savePark(park);
        return park;
    }
}
