package ar.edu.unq.desapp.grupoi.model.reservationstates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class ConfirmedState extends ReservationState {
    public void vehicleReceivedByClient(Reservation reservation) {
        reservation.setState(new ClientReceivedState());
    }

    public void vehicleDeliveredByOwner(Reservation reservation) {
        reservation.setState(new OwnerDeliveredState());
    }
}
