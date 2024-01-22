package be.kdg.prog6.boundedcontextB.core;

import be.kdg.prog6.boundedcontextB.adapters.in.RabbitEventHandler;
import be.kdg.prog6.boundedcontextB.domain.Park;
import be.kdg.prog6.boundedcontextB.events.GateOpenedEvent;
import be.kdg.prog6.boundedcontextB.ports.in.CheckPeopleInParkUseCase;
import be.kdg.prog6.boundedcontextB.ports.in.CheckWeatherUseCase;
import be.kdg.prog6.boundedcontextB.ports.in.PeopleInParkProjector;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DefaultCheckPeopleInParkUseCase implements CheckPeopleInParkUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitEventHandler.class);

    private PeopleInParkProjector peopleInParkProjector;
//    private CheckWeatherUseCase checkWeatherUseCase;

//    public DefaultCheckPeopleInParkUseCase(PeopleInParkProjector peopleInParkProjector, CheckWeatherUseCase checkWeatherUseCase) {
//
//        this.peopleInParkProjector = peopleInParkProjector;
//        this.checkWeatherUseCase = checkWeatherUseCase;
//    }

    @Override
    @Transactional
    public void checkPeople(GateOpenedEvent event) {
//        Park park = peopleInParkProjector.project(event);
//        if (park.isTimeToOpenAttraction(checkWeatherUseCase.isColdRainyStormy())) {
//            LOGGER.info("It is cold,rainy or stormy");
//            LOGGER.info("People inside: " + park.getTotalPeopleInside());
//        } else {
//            LOGGER.info("Good weather");
//        }
    }
}
