package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "Canceled")
public class CanceledState extends ReservationState {

  @Override
  public String getDescription() {
    return "OWNER_CANCELED_DESCRIPTION";
  }
}
