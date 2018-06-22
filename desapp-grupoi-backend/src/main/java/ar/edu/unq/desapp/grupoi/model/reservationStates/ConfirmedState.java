package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;
import java.time.Instant;

@Entity(name = "Confirmed")
public class ConfirmedState extends ReservationState {
    public void vehicleReceivedByClient(Reservation reservation) {
        reservation.setStartWaitingTime(Instant.now());
        reservation.setState(new ClientReceivedState());
    }

    public void vehicleDeliveredByOwner(Reservation reservation) {
        reservation.setStartWaitingTime(Instant.now());
        reservation.setState(new OwnerDeliveredState());
    }
}
