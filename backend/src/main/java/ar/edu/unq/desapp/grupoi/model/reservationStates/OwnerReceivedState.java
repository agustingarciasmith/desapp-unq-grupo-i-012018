package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;
import ar.edu.unq.desapp.grupoi.model.errors.ActionNotAllowed;

public class OwnerReceivedState extends ReservationState {

    public void vehicleDeliveredByClient(Reservation reservation) {
        reservation.setState(new RentFinishedState());
    }
}
