package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.reservationStates.RejectedState;
import ar.edu.unq.desapp.grupoi.model.reservationStates.RentStartedState;
import ar.edu.unq.desapp.grupoi.model.support.ReservationBuilder;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReservationTimeTests extends TestCase{
    private ReservationBuilder builder = new ReservationBuilder();
    private Clock mockClock;
    private Reservation reservationWaitingClientReceptionConfirmation;
    private Reservation reservationWaitingOwnerDeliverConfirmation;
    private Reservation reservationStarted;
    private Instant tenHoursLater;
    private Instant thirtyOneMinutesLater;

//    @Before
//    public void setUp() {
//        tenHoursLater = Instant.now().plus(10, ChronoUnit.HOURS);
//        thirtyOneMinutesLater = Instant.now().plus(31, ChronoUnit.MINUTES);
//        mockClock = mock(Clock.class);
//        reservationWaitingClientReceptionConfirmation = builder.ownerInformsDeliverReservation();
//        reservationWaitingOwnerDeliverConfirmation = builder.clientInformsReceptionReservation();
//        reservationStarted = builder.ownerInformsReceptionReservation();
//    }
//
//    @Test
//    public void testReservationGetsConfirmedIfClientDoesntConfirmsReceptionAfter30Minutes() {
//        reservationWaitingClientReceptionConfirmation.setClock(mockClock);
//        when(mockClock.instant()).thenReturn(thirtyOneMinutesLater);
//        reservationWaitingClientReceptionConfirmation.checkStartConfirmation();
//
//        assertTrue(reservationWaitingClientReceptionConfirmation.getState() instanceof RentStartedState);
//    }
//
//    @Test
//    public void testReservationGetsRejectedIfOwnerDoesntConfirmsDeliveryAfter30Minutes() {
//        reservationWaitingOwnerDeliverConfirmation.setClock(mockClock);
//        when(mockClock.instant()).thenReturn(thirtyOneMinutesLater);
//        reservationWaitingOwnerDeliverConfirmation.checkStartConfirmation();
//
//        assertTrue(reservationWaitingOwnerDeliverConfirmation.getState() instanceof RejectedState);
//    }
//
//    @Test
//    public void testWhenTheReservationIsFinishedTheAmountOfHoursGetsCalculated() {
//        reservationStarted.setClock(mockClock);
//        when(mockClock.instant()).thenReturn(tenHoursLater);
//        reservationStarted.vehicleDeliveredByClient();
//
//        assertThat(reservationStarted.getRentDurationInHours()).isEqualTo(10);
//        assertThat(reservationStarted.getFinalCost()).isEqualTo(reservationStarted.getPublication().getCost() * 10);
//    }
}

