package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.DescriptionLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.model.FieldMissing;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLES")
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column
  private Integer numberOfPassengers;

  @Column
  private VehicleType type;

  @Column
  private String description;

  @Column
  private String license;

  public Vehicle() {
  }

  public Vehicle(VehicleType type, Integer numberOfPassengers, String description, String license) {
    this.type = type;
    this.numberOfPassengers = numberOfPassengers;
    this.description = description;
    this.license = license;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Vehicle vehicle = (Vehicle) o;

    return license != null ? license.equals(vehicle.license) : vehicle.license == null;
  }

  @Override
  public int hashCode() {
    return license != null ? license.hashCode() : 0;
  }

  public Long getId() {
    return this.id;
  }
}
