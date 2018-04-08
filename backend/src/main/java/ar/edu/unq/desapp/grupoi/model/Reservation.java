package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.InvalidReservation;

public class Reservation {
    private final Publication publication;
    private User client;
    private ReservationState state = ReservationState.PENDING;

    public Reservation(Publication publication, User client) {
        if (client.equals(publication.getOwner())) throw new InvalidReservation();
        this.client = client;
        this.publication = publication;
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

    public User getOwner(){
        return publication.getOwner();
    }

    public void confirm() {
        this.state = ReservationState.CONFIRMED;
    }
}
