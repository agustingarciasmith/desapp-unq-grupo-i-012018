package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.DescriptionLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;

public class Vehicle {
  public static final String TYPE = "Type";
  public static final String NUMBER_OF_PASSENGERS = "Number of passengers";
  public static final String DESCRIPTION = "Description";
  private Integer numberOfPassengers;
  private VehicleType type;
  private String description;

  public Vehicle(VehicleType type, Integer numberOfPassengers, String description) {
    if(type == null) throw new FieldMissing(TYPE);
    if(numberOfPassengers == null) throw new FieldMissing(NUMBER_OF_PASSENGERS);
    if(description == null) throw new FieldMissing(DESCRIPTION);
    if(description.length() < 30 || description.length() > 200) throw new DescriptionLengthOutOfBounds();
    this.type = type;
    this.numberOfPassengers = numberOfPassengers;
    this.description = description;
  }

  public VehicleType getType(){ return type; }
  public Integer getNumberOfPassengers(){ return  numberOfPassengers; }
  public String getDescription(){ return description; }
}
