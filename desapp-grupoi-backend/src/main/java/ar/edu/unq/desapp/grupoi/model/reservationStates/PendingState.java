package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "Pending")
public class PendingState extends ReservationState {
  public void confirm(Reservation reservation) {
    reservation.setState(new ConfirmedState());
  }

  public void ownerCancelReservation(Reservation reservation) {
    reservation.setState(new CanceledState());
  }

  @Override
  public String getDescription() {
    return "PENDING_OWNER_CONFIRMATION";
  }
}
