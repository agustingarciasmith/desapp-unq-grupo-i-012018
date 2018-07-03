package ar.edu.unq.desapp.grupoi.model;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "PASSANGERS_NUMBER", nullable = false)
  private Integer numberOfPassengers;

  @Column(name = "TYPE", nullable = false)
  private VehicleType type;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  @Column(name = "LICENSE", unique = true, nullable = false)
  private String license;

  @Column(name = "PICTURE")
  private String picture;

  @OneToOne
  private User owner;

  public Vehicle() {
  }

  public Vehicle(VehicleType type, Integer numberOfPassengers, String description, String license, String picture, User owner) {
    this.id = id;
    this.type = type;
    this.numberOfPassengers = numberOfPassengers;
    this.description = description;
    this.license = license;
    this.picture = picture;
    this.owner = owner;
  }

  public VehicleType getType() {
    return type;
  }

  public Integer getNumberOfPassengers() {
    return numberOfPassengers;
  }

  public String getDescription() {
    return description;
  }

  public String getLicense() {
    return license;
  }

  public Long getId() {
    return this.id;
  }

  public String getPicture(){ return this.picture; }

  public User getOwner() {
    return this.owner;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
