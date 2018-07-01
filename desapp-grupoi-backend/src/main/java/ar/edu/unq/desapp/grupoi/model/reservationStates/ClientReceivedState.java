package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "ClientReceived")
public class ClientReceivedState extends ReservationState {

    public void ownerConfirmVehicleDelivery(Reservation reservation) {
        reservation.setState(new RentStartedState());
    }
}
