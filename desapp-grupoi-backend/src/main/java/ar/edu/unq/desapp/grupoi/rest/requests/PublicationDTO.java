package ar.edu.unq.desapp.grupoi.rest.requests;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class PublicationDTO {
  private Long publicationId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private List<LocalDate> availableDates;

  private Long vehicleId;
  private VehicleDTO vehicle;
  private String city;
  private String pickUpAddress;
  private List<String> returnAddress;
  private String contactPhone;
  private Integer cost;
  private Long userId;
  private User owner;

  public static PublicationDTO from(Publication publication) {
    PublicationDTO dto = new PublicationDTO();
    dto.setPublicationId(publication.getId());
    dto.setAvailableDates(publication.getAvailableDates());
    dto.setVehicleId(publication.getVehicle().getId());
    dto.setCity(publication.getCity());
    dto.setPickUpAddress(publication.getPickUpAddress());
    dto.setReturnAddress(publication.getReturnAddress());
    dto.setContactPhone(publication.getContactPhone());
    dto.setCost(publication.getCost());
    dto.setUserId(publication.getOwner().getId());
    dto.setOwner(publication.getOwner());
    dto.setVehicle(VehicleDTO.from(publication.getVehicle()));

    return dto;
  }

  @Override
  public String toString() {
    return this.publicationId.toString();
  }

  public void setPublicationId(Long publicationId) {
    this.publicationId = publicationId;
  }

  public void setVehicleId(Long vehicleId) {
    this.vehicleId = vehicleId;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setPickUpAddress(String pickUpAddress) {
    this.pickUpAddress = pickUpAddress;
  }

  public void setReturnAddress(List<String> returnAddress) {
    this.returnAddress = returnAddress;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public void setCost(Integer cost) {
    this.cost = cost;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getPublicationId() {
    return publicationId;
  }

  public List<LocalDate> getAvailableDates() {
    return availableDates;
  }

  public void setAvailableDates(List<LocalDate> availableDates) {
    this.availableDates = availableDates;
  }

  public Long getVehicleId() {
    return vehicleId;
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

  public Long getUserId() {
    return userId;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public VehicleDTO getVehicle() {
    return vehicle;
  }

  public void setVehicle(VehicleDTO vehicle) {
    this.vehicle = vehicle;
  }
}
