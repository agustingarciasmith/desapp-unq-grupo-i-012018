package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;
import ar.edu.unq.desapp.grupoi.model.errors.model.ActionNotAllowed;

import javax.persistence.Entity;

@Entity(name = "ClientReceived")
public class ClientReceivedState extends ReservationState {

  public void ownerConfirmVehicleDelivery(Reservation reservation) {
    reservation.setState(new RentStartedState());
  }

  public void expire(Reservation reservation) {
    reservation.setState(new CanceledState());
  }

  @Override
  public String getDescription() {
    return "CLIENT_GET_VEHICLE";
  }
}
