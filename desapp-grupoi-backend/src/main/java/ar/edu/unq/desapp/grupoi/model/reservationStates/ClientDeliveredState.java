package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "ClientDelivered")
public class ClientDeliveredState extends ReservationState {

    public void ownerReciveVehicle(Reservation reservation) {
        reservation.setState(new RentFinishedState());
    }

    @Override
    public String getDescription() {
        return "CLIENT_RETURN_VEHICLE";
    }
}
