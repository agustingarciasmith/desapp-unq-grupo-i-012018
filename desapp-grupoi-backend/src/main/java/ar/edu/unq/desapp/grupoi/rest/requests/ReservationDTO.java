package ar.edu.unq.desapp.grupoi.rest.requests;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDTO {
  private Long publicationId;
  private Long clientId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private List<LocalDate> selectedDates;

  private Long id;

  public static ReservationDTO from(Reservation reservation) {
    ReservationDTO dto = new ReservationDTO();
    dto.setId(reservation.getId());
    dto.setClientId(reservation.getClient().getId());
    dto.setPublicationId(reservation.getPublication().getId());
    dto.setSelectedDates(reservation.getSelectedDates());

    return dto;
  }

  public Long getPublicationId() {
    return this.publicationId;
  }

  public void setPublicationId(Long publicationId) {
    this.publicationId = publicationId;
  }

  public Long getClientId() {
    return this.clientId;
  }

  public void setClientId(Long clientId) {
    this.clientId = clientId;
  }

  public List<LocalDate> getSelectedDates() {
    return this.selectedDates;
  }

  public void setSelectedDates(List<LocalDate> selectedDates) {
    this.selectedDates = selectedDates;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
