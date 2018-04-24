package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.DescriptionLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;

public class Vehicle {
  public static final String TYPE = "Type";
  public static final String NUMBER_OF_PASSENGERS = "Number of passengers";
  public static final String DESCRIPTION = "Description";
  public static final String LICENSE = "License";
  private Integer numberOfPassengers;
  private VehicleType type;
  private String description;
  private String license;

  public Vehicle(VehicleType type, Integer numberOfPassengers, String description, String license) {
    if(type == null) throw new FieldMissing(TYPE);
    if(numberOfPassengers == null) throw new FieldMissing(NUMBER_OF_PASSENGERS);
    if(description == null) throw new FieldMissing(DESCRIPTION);
    if(license == null) throw new FieldMissing(LICENSE);
    if(description.length() < 30 || description.length() > 200) throw new DescriptionLengthOutOfBounds();
    this.type = type;
    this.numberOfPassengers = numberOfPassengers;
    this.description = description;
    this.license = license;
  }

  public VehicleType getType(){ return type; }
  public Integer getNumberOfPassengers(){ return  numberOfPassengers; }
  public String getDescription(){ return description; }
  public String getLicense(){ return license; }

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
}
