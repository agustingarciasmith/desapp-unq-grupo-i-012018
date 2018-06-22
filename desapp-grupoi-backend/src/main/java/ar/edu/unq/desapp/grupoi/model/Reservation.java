package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.InvalidReservation;
import ar.edu.unq.desapp.grupoi.model.reservationStates.PendingState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Entity
@Table(name = "RESERVATIONS")
public class Reservation {

    @Transient
    private Clock clock = Clock.systemUTC();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private Publication publication;

    @OneToOne
    private User owner;

    @OneToOne
    private User client;

    @OneToOne(targetEntity = ReservationState.class)
    private ReservationState state;

    @Column(name = "START_TIME")
    private Instant startTime;

    @Column(name = "DURATION")
    private int rentDurationInHours;

    @Column(name = "FINAL_COST")
    private double finalCost;

    @Column(name = "START_WAITING_TIME")
    private Instant startWaitingTime;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<LocalDate> selectedDates = new ArrayList<>();

    public Reservation(){}

    public Reservation(Publication publication, User client, List<LocalDate> selectedDates) {
        if (client.getCuil().equals(publication.getOwner().getCuil())) throw new InvalidReservation();
        this.client = client;
        this.publication = publication;
        this.state = new PendingState();
        this.selectedDates = selectedDates;
        this.owner = publication.getOwner();
    }

    public User getClient() {
        return client;
    }

    public User getOwner() { return owner; }

    public Publication getPublication() {
        return publication;
    }

    public ReservationState getState() {
        return state;
    }

    public void setState(ReservationState state) {
        this.state = state;
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

    public Reservation confirmReservationAsOwner() {
        state.confirm(this);
        excludeSelectedDatesOfPublication();
        return this;
    }

    public Reservation informReceptionAsClient() {
        this.state.vehicleReceivedByClient(this);
        return this;
    }

    public Reservation informDeliverAsOwner() {
        this.state.vehicleDeliveredByOwner(this);
        return this;
    }

    public Reservation informDeliverAsClientAndScore(int score) {
        this.state.vehicleDeliveredByClient(this);
        getOwner().addScore(score);
        return this;
    }

    public Reservation informReceptionAsOwnerAndScore(int score) {
        this.state.vehicleReceivedByOwner(this);
        getClient().addScore(score);
        return this;
    }

    private void excludeSelectedDatesOfPublication() {
        publication.getAvailableDates().removeAll(selectedDates);
    }
}
