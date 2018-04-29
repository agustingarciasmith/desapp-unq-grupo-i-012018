package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import java.time.Instant;

public class ConfirmedState extends ReservationState {
    public void vehicleReceivedByClient(Reservation reservation) {
        reservation.setStartWaitingTime(Instant.now());
        reservation.setState(new ClientReceivedState());
    }

    public void vehicleDeliveredByOwner(Reservation reservation) {
        reservation.setStartWaitingTime(Instant.now());
        reservation.setState(new OwnerDeliveredState());
    }
}
