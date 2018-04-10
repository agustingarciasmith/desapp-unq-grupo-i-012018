package ar.edu.unq.desapp.grupoi.model.reservationstates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class OwnerDeliveredState extends ReservationState {

    public void vehicleReceivedByClient(Reservation reservation) {
        reservation.setState(new RentStartedState());
    }
}
