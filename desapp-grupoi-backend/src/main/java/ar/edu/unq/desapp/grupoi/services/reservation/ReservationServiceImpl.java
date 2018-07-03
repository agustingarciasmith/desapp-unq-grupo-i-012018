package ar.edu.unq.desapp.grupoi.services.reservation;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.ReservationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.rest.requests.ReservationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

  public ReservationRepository reservationRepository;
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private PublicationRepository publicationRespository;
  private UserRepository userRepository;

  @Autowired
  public ReservationServiceImpl(PublicationRepository publicationRespository,
                                ReservationRepository reservationRepository,
                                UserRepository userRepository) {

    this.publicationRespository = publicationRespository;
    this.reservationRepository = reservationRepository;
    this.userRepository = userRepository;
  }

  @Override
  public ReservationDTO create(ReservationDTO reservationDTO) {
    validateReservation(reservationDTO);

    User client = findUser(reservationDTO.getClientId());
    Publication publication = publicationRespository.load(reservationDTO.getPublicationId());
    validateExistingReservationForPublication(publication, reservationDTO);
    validateExistingReservationForClient(client, reservationDTO);
    validateCanMakeReservationForSelectedDates(publication, reservationDTO);

    Reservation reservation = Reservation.from(client, reservationDTO, publication);
    reservationRepository.save(reservation);
    reservationDTO.setId(reservation.getId());

    return reservationDTO;
  }

  @Override
  public void confirmReservation(Long reservationId, Long ownerId) {
    Reservation reservation = findReservation(reservationId);
    User owner = findUser(ownerId);

    validateOwnerOfReservationIs(reservation, owner);

    reservation.ownerConfirmReservation();
  }

  @Override
  public void clientGetVehicle(Long reservationId, Long clientId) {
    Reservation reservation = findReservation(reservationId);
    User client = findUser(clientId);

    validateUserIsClientOfReservation(client, reservation);

    reservation.clientObtainVehicle();
  }

  @Override
  public void ownerConfirmVehicleDelivery(Long reservationId, Long ownerId) {
    Reservation reservation = findReservation(reservationId);
    User owner = findUser(ownerId);

    validateOwnerOfReservationIs(reservation, owner);

    reservation.ownerConfirmVehicleDelivery();
  }

  @Override
  public void clientReturnVehicle(Long reservationId, Long clientId, Integer score) {
    Reservation reservation = findReservation(reservationId);
    User client = findUser(clientId);

    validateUserIsClientOfReservation(client, reservation);

    reservation.clientReturnVehicle();
    reservation.getOwner().reciveScore(score);
  }

  @Override
  public void ownerReciveVehicle(Long reservationId, Long ownerId, Integer score) {
    Reservation reservation = findReservation(reservationId);
    User owner = findUser(ownerId);

    validateOwnerOfReservationIs(reservation, owner);

    reservation.ownerConfirmVehicleReseption();
    reservation.getClient().reciveScore(score);

  }

  @Override
  public List<ReservationDTO> getAllAsOwner(Long ownerId) {
    User owner = findUser(ownerId);
    return reservationRepository.getAllAsOwner(owner).stream().map(ReservationDTO::from).collect(Collectors.toList());
  }

  @Override
  public List<ReservationDTO> getAllAsClient(Long clientId) {
    User client = findUser(clientId);
    return reservationRepository.getAllAsClient(client).stream().map(ReservationDTO::from).collect(Collectors.toList());
  }

  @Override
  public ReservationDTO getById(Long id) {
    return ReservationDTO.from(reservationRepository.load(id));
  }

  private void validateReservation(ReservationDTO reservationDTO) {
    List<String> errors = new ArrayList<>();
    if (reservationDTO.getClientId() == null) {
      errors.add(ErrorCode.Reservation.CLIENT_ID_NOT_PRESENT);
    }
    if (reservationDTO.getPublicationId() == null) {
      errors.add(ErrorCode.Reservation.PUBLICATION_ID_NOT_PRESENT);
    }
    if (reservationDTO.getSelectedDates() == null || reservationDTO.getSelectedDates().isEmpty()) {
      errors.add(ErrorCode.Reservation.SELECTED_DATES_NOT_PRESENT);
    }

    if (!errors.isEmpty()) {
      throw new InvalidRequestException("Error creating reservation", errors);
    }
  }

  private void validateExistingReservationForPublication(Publication publication, ReservationDTO reservationDTO) {
    this.reservationRepository.findByPublication(publication).stream()
        .flatMap(reservation -> reservation.getSelectedDates().stream())
        .filter(reservationDate -> reservationDTO.getSelectedDates().contains(reservationDate))
        .findFirst()
        .ifPresent(localDate -> {
          throw new InvalidRequestException(
              "Error creating reservation",
              Collections.singletonList(ErrorCode.Reservation.PUBLICATION_DUPLICATED_RESERVATION));
        });
  }

  private void validateExistingReservationForClient(User client, ReservationDTO reservationDTO) {
    this.reservationRepository.getAllAsClient(client).stream()
        .flatMap(reservation -> reservation.getSelectedDates().stream())
        .filter(reservationDate -> reservationDTO.getSelectedDates().contains(reservationDate))
        .findFirst()
        .ifPresent((duplicatedDate) -> {
              throw new InvalidRequestException(
                  "Error creating reservation",
                  Collections.singletonList(ErrorCode.Reservation.CLIENT_DUPLICATED_RESERVATION));
            }
        );
  }

  private User findUser(Long userId) {
    if (userId == null) {
      throw new InvalidRequestException(
          "Error searching for user",
          Collections.singletonList(ErrorCode.Reservation.USER_ID_NOT_PRESENT)
      );
    }

    return this.userRepository.get(userId);
  }

  private Reservation findReservation(Long reservationId) {
    if (reservationId == null) {
      throw new InvalidRequestException(
          "Error searching for reservation",
          Collections.singletonList(ErrorCode.Reservation.ID_NOT_PRESENT));
    }

    return this.reservationRepository.load(reservationId);
  }

  private void validateOwnerOfReservationIs(Reservation reservation, User owner) {
    if (!reservation.getOwner().getId().equals(owner.getId())) {
      throw new InvalidRequestException(
          "Action not permited",
          Collections.singletonList(ErrorCode.Reservation.USER_IS_NOT_OWNER)
      );
    }
  }

  private void validateUserIsClientOfReservation(User client, Reservation reservation) {
    if (!reservation.getClient().getId().equals(client.getId())) {
      throw new InvalidRequestException(
          "Action not permited",
          Collections.singletonList(ErrorCode.Reservation.USER_IS_NOT_CLIENT));
    }
  }

  private void validateCanMakeReservationForSelectedDates(Publication publication, ReservationDTO reservationDTO) {
    if(!publication.getAvailableDates().containsAll(reservationDTO.getSelectedDates())){
      throw new InvalidRequestException(
          "Error creating reservation",
          Collections.singletonList(ErrorCode.Reservation.PUBLICATION_NOT_IN_SELECTED_DATES));
    }
  }
}
