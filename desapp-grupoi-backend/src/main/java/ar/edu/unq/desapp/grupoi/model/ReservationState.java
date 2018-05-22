package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.ActionNotAllowed;

public abstract class ReservationState {

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