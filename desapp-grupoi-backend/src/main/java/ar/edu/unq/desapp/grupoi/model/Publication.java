package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.FieldMissing;
import ar.edu.unq.desapp.grupoi.rest.requests.PublicationDTO;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PUBLICATIONS")
public class Publication {

  private static final String COST = "Cost";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "AVALILABLE_DATES", nullable = false)
  @ElementCollection(targetClass = LocalDate.class)
  private List<LocalDate> availableDates;

  @OneToOne(optional = false)
  private Vehicle vehicle;

  @Column(name = "CITY", nullable = false)
  private String city;

  @Column(name = "PICKUP_ADDRESS", nullable = false)
  private String pickUpAddress;

  @Column(name = "RETURN_ADDRESS")
  @ElementCollection(targetClass = String.class)
  private List<String> returnAddress;

  @Column(name = "PHONE", nullable = false)
  private String contactPhone;

  @Column(name = "COST_X_HOUR", nullable = false)
  private Integer cost;

  @OneToOne(optional = false)
  private User owner;

  public Publication() {
  }

  public Publication(User owner, Vehicle vehicle, String city, String pickUpAddress, List<String> returnAddress, String contactPhone, List<LocalDate> availableDates, Integer cost) {
    if (cost == null) throw new FieldMissing(COST);
    this.owner = owner;
    this.vehicle = vehicle;
    this.city = city;
    this.pickUpAddress = pickUpAddress;
    this.returnAddress = returnAddress;
    this.contactPhone = contactPhone;
    this.availableDates = availableDates;
    this.cost = cost;
  }

  public static Publication from(User user, Vehicle vehicle, PublicationDTO publicationDTO) {
    return new Publication(
        user,
        vehicle,
        publicationDTO.getCity(),
        publicationDTO.getPickUpAddress(),
        publicationDTO.getReturnAddress(),
        publicationDTO.getContactPhone(),
        publicationDTO.getAvailableDates(),
        publicationDTO.getCost()
    );
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public String getCity() {
    return city;
  }

  public String getPickUpAddress() {
    return pickUpAddress;
  }

  public List<String> getReturnAddress() {
    return returnAddress;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public Integer getCost() {
    return cost;
  }

  public List<LocalDate> getAvailableDates() {
    return availableDates;
  }

  public User getOwner() {
    return owner;
  }

  public Long getId() {
    return id;
  }
}
