package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.InvalidReservation;
import ar.edu.unq.desapp.grupoi.model.reservationStates.PendingState;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private Clock clock = Clock.systemUTC();
    private Publication publication;
    private User client;
    private ReservationState state;
    private Instant startTime;
    private int rentDurationInHours;
    private double finalCost;
    private Instant startWaitingTime;

    public Reservation() {

    }

    public Reservation(Publication publication, User client) {
        if (client.getCuil().equals(publication.getOwner().getCuil())) throw new InvalidReservation();
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

    public User getOwner() {
        return publication.getOwner();
    }

    public void confirm() {
        this.state.confirm(this);
    }

    public void vehicleReceivedByClient() {
        this.state.vehicleReceivedByClient(this);
    }

    public void vehicleDeliveredByOwner() {
        this.state.vehicleDeliveredByOwner(this);
    }

    public void vehicleDeliveredByClient() {
        this.state.vehicleDeliveredByClient(this);
    }

    public void vehicleReceivedByOwner() {
        this.state.vehicleReceivedByOwner(this);
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime() {
        this.startTime = clock.instant();
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public void calculateCost() {
        this.rentDurationInHours = calculateRentDurationInHours();
        this.finalCost = this.publication.getCost() * this.rentDurationInHours;
    }

    private int calculateRentDurationInHours() {
        return Math.round(ChronoUnit.HOURS.between(startTime, clock.instant()) + 1);
    }

    public int getRentDurationInHours() {
        return rentDurationInHours;
    }

    public double getFinalCost() {
        return finalCost;
    }

    public void checkStartConfirmation() {
        state.checkStartConfirmation(this);
    }

    public boolean waitingTimeOver() {
        return (ChronoUnit.MINUTES.between(startWaitingTime, clock.instant()) + 1) > 30;
    }

    public void setStartWaitingTime(Instant time) {
        startWaitingTime = time;
    }
}
