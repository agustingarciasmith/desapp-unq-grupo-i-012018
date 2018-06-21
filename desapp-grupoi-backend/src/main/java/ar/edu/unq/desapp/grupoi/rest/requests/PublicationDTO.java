package ar.edu.unq.desapp.grupoi.rest.requests;

import java.time.LocalDate;
import java.util.List;

public class PublicationDTO {
  private Long publicationId;
  private List<LocalDate> availableDates;
  private Long vehicleId;
  private String city;
  private String pickUpAddress;
  private List<String> returnAddress;
  private String contactPhone;
  private Integer cost;
  private Long userId;

  public void setPublicationId(Long publicationId) {
    this.publicationId = publicationId;
  }

  public void setAvalilableDates(List<LocalDate> availableDates) {
    this.availableDates = availableDates;
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
}
