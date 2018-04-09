package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.InvalidReservation;
import ar.edu.unq.desapp.grupoi.model.reservationStates.*;

import java.time.Clock;
import java.time.LocalDateTime;

public class Reservation {
    private final Publication publication;
    private User client;
    private ReservationState state;

    public Reservation(Publication publication, User client) {
        if (client.equals(publication.getOwner())) throw new InvalidReservation();
        this.client = client;
        this.publication = publication;
        this.state = new PendingState();
    }

    public User getClient() {
        return client;
    }

    public Publication getPublication() {
        return publication;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
    }

    public User getOwner(){
        return publication.getOwner();
    }

    public void confirm() { this.state.confirm(this); }

    public void vehicleReceivedByClient() { this.state.vehicleReceivedByClient(this); }

    public void vehicleDeliveredByOwner() { this.state.vehicleDeliveredByOwner(this); }

    public void vehicleDeliveredByClient() { this.state.vehicleDeliveredByClient(this); }

    public void vehicleReceivedByOwner() { this.state.vehicleReceivedByOwner(this); }
}
