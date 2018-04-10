package ar.edu.unq.desapp.grupoi.model.reservationstates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class ClientDeliveredState extends ReservationState {

    public void vehicleReceivedByOwner(Reservation reservation) {
        reservation.setState(new RentFinishedState());
    }
}
