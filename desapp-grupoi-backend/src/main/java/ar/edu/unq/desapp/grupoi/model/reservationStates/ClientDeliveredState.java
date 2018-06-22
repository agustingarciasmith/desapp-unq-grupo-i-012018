package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "ClientDelivered")
public class ClientDeliveredState extends ReservationState {

    public void vehicleReceivedByOwner(Reservation reservation) {
        reservation.setState(new RentFinishedState());
        reservation.calculateCost();
    }
}
