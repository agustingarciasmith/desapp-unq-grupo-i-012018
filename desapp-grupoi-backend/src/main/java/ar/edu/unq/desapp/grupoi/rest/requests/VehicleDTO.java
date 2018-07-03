package ar.edu.unq.desapp.grupoi.rest.requests;

import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.VehicleType;

public class VehicleDTO {
  private Long userId;
  private Long vehicleId;
  private VehicleType vehicleType;
  private Integer numberOfPassangers;
  private String description;
  private String license;

  public static VehicleDTO from(Vehicle vehicle) {
    VehicleDTO dto = new VehicleDTO();
    dto.setType(vehicle.getType());
    dto.setUserId(vehicle.getOwner().getId());
    dto.setLicense(vehicle.getLicense());
    dto.setDescription(vehicle.getDescription());
    dto.setVehicleId(vehicle.getId());

    return dto;
  }

  public Long getUserId() {
    return this.userId;
  }

  public VehicleType getType() {
    return this.vehicleType;
  }

  public Integer getNumberOfPassengers() {
    return this.numberOfPassangers;
  }

  public String getDescription() {
    return this.description;
  }

  public String getLicense() {
    return this.license;
  }

  public void setVehicleId(Long id) {
    this.vehicleId = id;
  }

  public Long getVehicleId() {
    return this.vehicleId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setLicense(String license) {
    this.license = license;
  }

  public void setType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public void setNumberOfPassengers(Integer numberOfPassangers) {
    this.numberOfPassangers = numberOfPassangers;
  }
}
