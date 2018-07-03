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
  private String picture;

  public static VehicleDTO from(Vehicle vehicle) {
    VehicleDTO dto = new VehicleDTO();
    dto.setVehicleType(vehicle.getType());
    dto.setUserId(vehicle.getOwner().getId());
    dto.setLicense(vehicle.getLicense());
    dto.setDescription(vehicle.getDescription());
    dto.setVehicleId(vehicle.getId());
    dto.setPicture(vehicle.getPicture());

    return dto;
  }

  public Long getUserId() {
    return this.userId;
  }

  public VehicleType getVehicleType() {
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

  public void setVehicleType(VehicleType vehicleType) {
    this.vehicleType = vehicleType;
  }

  public void setNumberOfPassengers(Integer numberOfPassangers) {
    this.numberOfPassangers = numberOfPassangers;
  }

  public void setPicture(String picture) { this.picture = picture; }

  public String getPicture() { return this.picture; }
}
