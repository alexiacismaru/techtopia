package be.kdg.prog6.boundedContextC.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {


    public static final String TICKET_EVENTS_FAN_OUT = "ticket-events";
    public static final String TICKET_EVENTS_QUEUE = "ticket-queue";

    public static final String GATE_EVENTS_EXCHANGE = "gate-events";
    public static final String GATE_EVENTS_QUEUE = "gate-queue";

    @Bean
    FanoutExchange ticketEventsExchange() {
        return new FanoutExchange(TICKET_EVENTS_FAN_OUT);
    }

    @Bean
    Queue ticketEventsQueue() {
        return new Queue(TICKET_EVENTS_QUEUE);
    }


    @Bean
    Binding bindTicketQueueToTopic(FanoutExchange ticketEventsExchange, Queue ticketEventsQueue) {
        return BindingBuilder.bind(ticketEventsQueue).to(ticketEventsExchange);
    }

    @Bean
    TopicExchange gateEventsTopicExchange() {
        return new TopicExchange(GATE_EVENTS_EXCHANGE);
    }
    @Bean
    Queue gateEventsQueue() {
        return new Queue(GATE_EVENTS_QUEUE);
    }

    @Bean
    Binding bindGateQueueToTopic(TopicExchange gateEventsTopicExchange, Queue gateEventsQueue) {
        return BindingBuilder.bind(gateEventsQueue).to(gateEventsTopicExchange).with("gate.*");
    }

}
