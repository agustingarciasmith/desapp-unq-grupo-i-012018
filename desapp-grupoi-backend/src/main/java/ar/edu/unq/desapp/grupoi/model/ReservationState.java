package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.ActionNotAllowed;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class ReservationState {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    public ReservationState(){}

    public void confirm(Reservation reservation) {
        throw new ActionNotAllowed();
    }

    public void vehicleReceivedByClient(Reservation reservation) {
        throw new ActionNotAllowed();
    }

    public void vehicleDeliveredByOwner(Reservation reservation) {
        throw new ActionNotAllowed();
    }

    public void vehicleDeliveredByClient(Reservation reservation) {
        throw new ActionNotAllowed();
    }

    public void vehicleReceivedByOwner(Reservation reservation) {
        throw new ActionNotAllowed();
    }

    public void checkStartConfirmation(Reservation reservation){ return; }

    public String createEmailMessage(Reservation reservation) { return "Mail"; }
}