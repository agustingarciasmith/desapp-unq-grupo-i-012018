package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "RentStarted")
public class RentStartedState extends ReservationState {

    public void clientReturnVehicle(Reservation reservation) {
        reservation.setState(new ClientDeliveredState());
    }
}
