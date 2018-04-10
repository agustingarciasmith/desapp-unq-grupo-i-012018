package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;
import ar.edu.unq.desapp.grupoi.model.errors.ActionNotAllowed;

public class OwnerDeliveredState extends ReservationState {

    public void vehicleReceivedByClient(Reservation reservation) {
        reservation.setState(new RentStartedState());
    }
}
