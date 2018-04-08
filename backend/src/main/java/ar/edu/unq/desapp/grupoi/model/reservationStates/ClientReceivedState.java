package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class ClientReceivedState extends ReservationState {

    public void vehicleDeliveredByOwner(Reservation reservation) {
        reservation.setState(new RentStartedState());
    }
}
