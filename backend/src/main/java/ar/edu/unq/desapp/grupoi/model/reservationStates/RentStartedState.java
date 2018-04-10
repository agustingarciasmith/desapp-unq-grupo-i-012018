package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;


public class RentStartedState extends ReservationState {

    public void vehicleDeliveredByClient(Reservation reservation) {
        reservation.setState(new ClientDeliveredState());
    }

    public void vehicleReceivedByOwner(Reservation reservation) {
        reservation.setState(new OwnerReceivedState());
    }
}
