package ar.edu.unq.desapp.grupoi.model.reservationstates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

public class PendingState extends ReservationState {
    public void confirm(Reservation reservation) {
        reservation.setState(new ConfirmedState());
    }
}
