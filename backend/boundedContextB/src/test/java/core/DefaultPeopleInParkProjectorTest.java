package core;

import be.kdg.prog6.boundedcontextB.core.DefaultPeopleInParkProjector;
import be.kdg.prog6.boundedcontextB.domain.GateAction;
import be.kdg.prog6.boundedcontextB.domain.Park;
import be.kdg.prog6.boundedcontextB.events.GateOpenedEvent;
import be.kdg.prog6.boundedcontextB.ports.out.ParkProjectionOutPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class DefaultPeopleInParkProjectorTest {
    @Mock
    private ParkProjectionOutPort parkProjectionOutPort;

    private DefaultPeopleInParkProjector projector;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        projector = new DefaultPeopleInParkProjector(parkProjectionOutPort);
    }

    @Test
    public void testProjectGateOpenedEventIn() {
        Park park = new Park();
        park.setTotalPeopleInside(0);

        GateOpenedEvent event = new GateOpenedEvent(1L, GateAction.IN, LocalDateTime.now());

        ArgumentCaptor<Park> parkCaptor = ArgumentCaptor.forClass(Park.class);

        projector.project(event);

        verify(parkProjectionOutPort).savePark(parkCaptor.capture());
        Park capturedPark = parkCaptor.getValue();

        assertEquals(1, capturedPark.getTotalPeopleInside());
    }

    @Test
    public void testProjectGateOpenedEventOut() {
        Park park = new Park();
        park.setTotalPeopleInside(0);

        GateOpenedEvent event = new GateOpenedEvent(1L, GateAction.OUT, LocalDateTime.now());

        ArgumentCaptor<Park> parkCaptor = ArgumentCaptor.forClass(Park.class);

        projector.project(event);

        verify(parkProjectionOutPort).savePark(parkCaptor.capture());
        Park capturedPark = parkCaptor.getValue();

        assertEquals(-1, capturedPark.getTotalPeopleInside());
    }
}
