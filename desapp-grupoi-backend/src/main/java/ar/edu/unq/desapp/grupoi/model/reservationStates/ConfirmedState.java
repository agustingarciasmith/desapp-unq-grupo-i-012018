package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;
import java.time.Instant;

@Entity(name = "Confirmed")
public class ConfirmedState extends ReservationState {
    public void clientObtainVehicle(Reservation reservation) {
        reservation.setState(new ClientReceivedState());
    }
}
