package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.ActionNotAllowed;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ReservationState {

  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  @Column(name = "id", updatable = false, nullable = false)
  protected Long id;

  public ReservationState() {
  }

  public void confirm(Reservation reservation) {
    throw new ActionNotAllowed();
  }

  public void clientObtainVehicle(Reservation reservation) {
    throw new ActionNotAllowed();
  }

  public void ownerConfirmVehicleDelivery(Reservation reservation) {
    throw new ActionNotAllowed();
  }

  public void clientReturnVehicle(Reservation reservation) {
    throw new ActionNotAllowed();
  }

  public void ownerReciveVehicle(Reservation reservation) {
    throw new ActionNotAllowed();
  }

  public void expire(Reservation reservation){
    throw new ActionNotAllowed();
  };

  public void checkStartConfirmation(Reservation reservation) {
    return;
  }

  public void ownerCancelReservation(Reservation reservation){
    throw new ActionNotAllowed();
  };

  public String createEmailMessage(Reservation reservation) {
    return "Mail";
  }

  public String getDescription(){
    return null;
  }
}
