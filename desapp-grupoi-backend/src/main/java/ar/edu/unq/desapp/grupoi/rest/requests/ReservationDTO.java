package ar.edu.unq.desapp.grupoi.rest.requests;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public class ReservationDTO {
  private Long publicationId;
  private Long clientId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
  private List<LocalDate> selectedDates;

  private Long id;
  private User client;
  private Publication publication;
  private String reservationState;

  public static ReservationDTO from(Reservation reservation) {
    ReservationDTO dto = new ReservationDTO();
    dto.setId(reservation.getId());
    dto.setClientId(reservation.getClient().getId());
    dto.setPublicationId(reservation.getPublication().getId());
    dto.setSelectedDates(reservation.getSelectedDates());
    dto.setClient(reservation.getClient());
    dto.setPublication(reservation.getPublication());
    dto.setReservationState(reservation.getState().getDescription());
    return dto;
  }

  @Override
  public String toString() {
    return this.id.toString();
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

  public void setClient(User client) {
    this.client = client;
  }

  public User getClient() {
    return client;
  }

  public void setPublication(Publication publication) {
    this.publication = publication;
  }

  public Publication getPublication() {
    return publication;
  }

  public void setReservationState(String reservationState) {
    this.reservationState = reservationState;
  }

  public String getReservationState() {
    return reservationState;
  }
}
