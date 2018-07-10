package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "OwnerDelivered")
public class OwnerDeliveredState extends ReservationState {

    public void checkStartConfirmation(Reservation reservation) {
        reservation.setState(new RentStartedState());
    }

    @Override
    public String getDescription() {
        return "OWNER CONFIRM ";
    }
}
