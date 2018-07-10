package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.reservationStates.PendingState;
import ar.edu.unq.desapp.grupoi.rest.requests.ReservationDTO;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
  private User client;

  @OneToOne(targetEntity = ReservationState.class)
  @Cascade(org.hibernate.annotations.CascadeType.ALL)
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
  @ElementCollection(targetClass = LocalDate.class)
  private List<LocalDate> selectedDates;

  @Column(name = "LAST_UPDATE")
  private LocalDateTime lastUpdate;

  public Reservation() {
  }

  public Reservation(Publication publication, User client, List<LocalDate> selectedDates) {
    this.client = client;
    this.publication = publication;
    this.state = new PendingState();
    this.selectedDates = selectedDates;
    this.lastUpdate = LocalDateTime.now();
  }

  public static Reservation from(User client, ReservationDTO reservationDTO, Publication publication) {
    return new Reservation(publication, client, reservationDTO.getSelectedDates());
  }

  public void ownerConfirmReservation() {
    this.state.confirm(this);
    update();
  }

  public void clientObtainVehicle() {
    this.state.clientObtainVehicle(this);
    update();
  }

  private void update() {
    this.lastUpdate = LocalDateTime.now();
  }

  public void ownerConfirmVehicleDelivery() {
    this.state.ownerConfirmVehicleDelivery(this);
    update();
  }

  public void clientReturnVehicle() {
    this.state.clientReturnVehicle(this);
    update();
  }

  public void ownerConfirmVehicleReseption() {
    this.state.ownerReciveVehicle(this);
    update();
  }

  public void expire() {
    this.state.expire(this);
    update();
  }

  public Long getId() {
    return id;
  }

  public User getClient() {
    return client;
  }

  public User getOwner() {
    return this.publication.getOwner();
  }

  public Publication getPublication() {
    return publication;
  }

  public List<LocalDate> getSelectedDates() {
    return selectedDates;
  }

  public LocalDateTime getLastUpdate() {

    return this.lastUpdate;
  }

//Viejo

  public ReservationState getState() {
    return state;
  }


  public void setState(ReservationState state) {
    this.state = state;
  }

  public void calculateCost() {
    this.rentDurationInHours = calculateRentDurationInHours();
    this.finalCost = this.publication.getCost() * this.rentDurationInHours;
  }

  private int calculateRentDurationInHours() {
    return Math.round(ChronoUnit.HOURS.between(startTime, clock.instant()) + 1);
  }

  public Reservation confirmReservationAsOwner() {
    state.confirm(this);
    excludeSelectedDatesOfPublication();
    return this;
  }

  public Reservation informReceptionAsClient() {
    this.state.clientObtainVehicle(this);
    return this;
  }

  public Reservation informDeliverAsOwner() {
    this.state.ownerConfirmVehicleDelivery(this);
    return this;
  }

  public void ownerCancelReservation() {
    this.state.ownerCancelReservation(this);
  }

  private void excludeSelectedDatesOfPublication() {
    publication.getAvailableDates().removeAll(selectedDates);
  }
}
