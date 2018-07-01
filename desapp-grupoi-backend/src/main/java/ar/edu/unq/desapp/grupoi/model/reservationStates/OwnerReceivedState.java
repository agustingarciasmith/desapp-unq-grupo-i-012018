package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "OwnerReceived")
public class OwnerReceivedState extends ReservationState {

    public void clientReturnVehicle(Reservation reservation) {
        reservation.setState(new RentFinishedState());
        reservation.calculateCost();
    }
}
