package core;

import be.kdg.prog6.boundedContextA.core.DefaultBuyingATicketUseCase;
import be.kdg.prog6.boundedContextA.domain.BuyTicket;
import be.kdg.prog6.boundedContextA.domain.Guest;
import be.kdg.prog6.boundedContextA.domain.Ticket;
import be.kdg.prog6.boundedContextA.domain.TicketAction;
import be.kdg.prog6.boundedContextA.ports.in.BuyTicketsAmountCommand;
import be.kdg.prog6.boundedContextA.ports.out.TicketActivityCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketLoadPort;
import be.kdg.prog6.boundedContextA.ports.out.TicketUpdatePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import(TestConfigForThisTest.class)
public class IntegrationDefaultBuyingATicketUseCaseTest {
    private DefaultBuyingATicketUseCase buyingATicketUseCase;

    @Mock
    private BuyTicket buyTicket;

    @Mock
    private TicketCreatePort ticketCreatePort;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        List<TicketCreatePort> ticketCreatePorts = new ArrayList<>();
        ticketCreatePorts.add(ticketCreatePort);

        buyingATicketUseCase = new DefaultBuyingATicketUseCase(buyTicket, ticketCreatePorts);
    }

    @Test
    public void testBuyTicket() {
        TicketAction action = TicketAction.CHILD;
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusHours(10);
        Guest.GuestUUID owner = new Guest.GuestUUID(UUID.randomUUID());
        BuyTicketsAmountCommand command = new BuyTicketsAmountCommand(10.5, action, startTime, endTime, owner);
        when(buyTicket.buyTicket(action, startTime, endTime, 10.5, owner))
                .thenReturn(new Ticket(new Ticket.TicketUUID(UUID.randomUUID()), owner));
        buyingATicketUseCase.buyTicket(command);
        verify(ticketCreatePort, times(1)).createTicket(any());
    }
}

@TestConfiguration
class TestConfigForThisTest {
    @Bean
    TicketUpdatePort ticketUpdatePortMock() {
        return Mockito.mock(TicketUpdatePort.class);
    }

    @Bean
    TicketLoadPort ticketLoadPortMock() {
        return Mockito.mock(TicketLoadPort.class);
    }

    @Bean
    TicketActivityCreatePort ticketActivityCreatePortMock() {
        return Mockito.mock(TicketActivityCreatePort.class);
    }
}
