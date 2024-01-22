package be.kdg.prog6.boundedcontextB.adapters.in;

import be.kdg.prog6.boundedcontextB.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.boundedcontextB.events.Event;
import events.EventMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RabbitEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitEventHandler.class);
    private final List<ParkEventHandler<? extends Event>> parkEventHandlers;


    @Autowired
    public RabbitEventHandler(List<ParkEventHandler<? extends Event>> parkEventHandlers) {
        this.parkEventHandlers = parkEventHandlers;
    }

    @RabbitListener(queues = RabbitMQModuleTopology.GATE_EVENTS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receiveEventMessage(EventMessage eventMessage) {
        LOGGER.info(eventMessage.toString());
        parkEventHandlers.stream().filter(parkEventHandler -> parkEventHandler.appliesTo(eventMessage.getEventHeader().getEventType()))
                .forEach(piggyBankEventHandler ->
                        piggyBankEventHandler.receive(eventMessage).handle(piggyBankEventHandler.map(eventMessage.getEventBody())));
    }

}
