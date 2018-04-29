package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class ClientReceivedState extends ReservationState {

    public void vehicleDeliveredByOwner(Reservation reservation) {
        reservation.setState(new RentStartedState());
        reservation.setStartTime();
    }

    public void checkStartConfirmation(Reservation reservation) {
        if (reservation.waitingTimeOver()) reservation.setState(new RejectedState());
    }
}
