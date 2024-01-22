package be.kdg.prog6.boundedContextA.adapters.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleTopology {
    public static final String TICKET_EVENTS_FAN_OUT = "ticket-events";
    public static final String TICKET_EVENTS_QUEUE = "ticket-queue";

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
}

