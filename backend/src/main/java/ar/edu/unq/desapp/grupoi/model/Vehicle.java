package ar.edu.unq.desapp.grupoi.model;

public class Vehicle {
  public static final String TYPE_NOT_PRESENT = "A vehicle must have a type";
  private VehicleType type;

  public Vehicle(VehicleType type) {
    if(type == null) throw new RuntimeException(TYPE_NOT_PRESENT);
    this.type = type;
  }
}
