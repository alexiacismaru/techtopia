package core;

import be.kdg.prog6.boundedContextA.core.DefaultBuyingATicketUseCase;
import be.kdg.prog6.boundedContextA.domain.*;
import be.kdg.prog6.boundedContextA.ports.in.BuyTicketsAmountCommand;
import be.kdg.prog6.boundedContextA.ports.in.BuyingATicketUseCase;
import be.kdg.prog6.boundedContextA.ports.out.TicketActivityCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketCreatePort;
import be.kdg.prog6.boundedContextA.ports.out.TicketLoadPort;
import be.kdg.prog6.boundedContextA.ports.out.TicketUpdatePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockingDefaultBuyingATicketUseCaseTest {
    @Mock
    TicketUpdatePort ticketUpdatePortMock;
    @Mock
    TicketLoadPort ticketLoadPortMock;
    @Mock
    TicketCreatePort ticketCreatePortMock;
    @Captor
    ArgumentCaptor<Ticket> ticketArgumentCaptor;

    @Test
    void buyingTicketViaMocks() {
        TicketUpdatePort ticketUpdatePortMock = Mockito.mock(TicketUpdatePort.class);
        TicketLoadPort ticketLoadPortMock = Mockito.mock(TicketLoadPort.class);
        TicketActivityCreatePort ticketActivityCreatePort = Mockito.mock(TicketActivityCreatePort.class);
        BuyingATicketUseCase buyingATicketUseCase = new DefaultBuyingATicketUseCase((BuyTicket) ticketLoadPortMock, List.of(ticketCreatePortMock));
        buyingATicketUseCase.buyTicket(new BuyTicketsAmountCommand(10.5, TicketAction.CHILD, LocalDateTime.now(), LocalDateTime.now().plusHours(10), new Guest.GuestUUID(UUID.randomUUID())));
        Mockito.verifyNoInteractions(ticketActivityCreatePort);
    }

    @Test
    void buyingATicketViaMockUsingCaptor() {
        ActivityWindow testWindow = new ActivityWindow();
        testWindow.add(new TicketActivity(TicketAction.CHILD, 10.5, LocalDateTime.now()));
        Ticket bobbysTicket = new Ticket(new Ticket.TicketUUID(UUID.randomUUID()), new Guest.GuestUUID(UUID.randomUUID()));
        when(ticketLoadPortMock.loadTicketForOwner(Mockito.any(Guest.GuestUUID.class))).thenReturn(Optional.of(bobbysTicket));
        BuyingATicketUseCase buyingATicketUseCase = new DefaultBuyingATicketUseCase((BuyTicket) ticketLoadPortMock, List.of(ticketCreatePortMock));
        buyingATicketUseCase.buyTicket(new BuyTicketsAmountCommand(10.5, TicketAction.CHILD, LocalDateTime.now(), LocalDateTime.now().plusHours(10), new Guest.GuestUUID(UUID.randomUUID())));
        verify(ticketUpdatePortMock).updateTicket(ticketArgumentCaptor.capture());
        assertEquals(LocalDateTime.now().plusHours(10), testWindow.getEndTimeStamp());
        assertEquals(LocalDateTime.now().plusHours(10), ticketArgumentCaptor.getValue().getEnd());
    }
}
