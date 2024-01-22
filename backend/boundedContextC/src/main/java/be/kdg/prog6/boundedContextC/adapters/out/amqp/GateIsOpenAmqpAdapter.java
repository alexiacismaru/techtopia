package be.kdg.prog6.boundedContextC.adapters.out.amqp;

import be.kdg.prog6.boundedContextC.adapters.config.RabbitMQModuleTopology;
import be.kdg.prog6.boundedContextC.events.ValidTicketIsScannedEvent;
import be.kdg.prog6.boundedContextC.ports.out.GateOpenedOutPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import events.EventCatalog;
import events.EventHeader;
import events.EventMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class GateIsOpenAmqpAdapter implements GateOpenedOutPort {
    public static final Logger log = LoggerFactory.getLogger(GateIsOpenAmqpAdapter.class);
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    @Override
    public void processGateEvent (ValidTicketIsScannedEvent validTicketIsScanned) {
        var eventHeader = EventHeader.builder().eventID(UUID.randomUUID()).eventCatalog(EventCatalog.GATE_IS_OPEN_EVENT).build();
        try {
            rabbitTemplate.convertAndSend(RabbitMQModuleTopology.GATE_EVENTS_EXCHANGE, "gate.action", EventMessage.builder().eventHeader(eventHeader).eventBody(objectMapper.writeValueAsString(validTicketIsScanned)).build());
            log.info("Gate is open: "+ validTicketIsScanned.gateUUID() +" "+ validTicketIsScanned.action() );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
