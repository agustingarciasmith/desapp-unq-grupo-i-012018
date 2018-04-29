package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class OwnerReceivedState extends ReservationState {

    public void vehicleDeliveredByClient(Reservation reservation) {
        reservation.setState(new RentFinishedState());
        reservation.calculateCost();
    }
}
