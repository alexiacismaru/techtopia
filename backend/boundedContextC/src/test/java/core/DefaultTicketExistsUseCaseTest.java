package core;

import be.kdg.prog6.boundedContextC.core.DefaultTicketExistsUseCase;
import be.kdg.prog6.boundedContextC.domain.Ticket;
import be.kdg.prog6.boundedContextC.events.TicketExistsEvent;
import be.kdg.prog6.boundedContextC.ports.in.TicketExistsUseCase;
import be.kdg.prog6.boundedContextC.ports.out.TicketExistsOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;


public class DefaultTicketExistsUseCaseTest {

    @Mock
    private TicketExistsOutPort ticketExistsOutPort;

    private TicketExistsUseCase ticketExistsUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketExistsUseCase = new DefaultTicketExistsUseCase(ticketExistsOutPort);
    }

    @Test
    public void testProcessTicket() {
        TicketExistsEvent event = new TicketExistsEvent(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now().plusHours(1));
        ticketExistsUseCase.processTicket(event);
        Mockito.verify(ticketExistsOutPort).saveTicket(Mockito.any(Ticket.class));
    }
}

