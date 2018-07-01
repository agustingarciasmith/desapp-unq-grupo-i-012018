package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepository;
import ar.edu.unq.desapp.grupoi.rest.requests.PublicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class PublicationServiceImpl implements PublicationService {

  private PublicationRepository repository;
  private UserRepository userRepository;
  private VehicleRepository vehicleRepository;

  @Autowired
  public PublicationServiceImpl(PublicationRepository repository, UserRepository userRepository, VehicleRepository vehicleRepository) {
    this.repository = repository;
    this.userRepository = userRepository;
    this.vehicleRepository = vehicleRepository;
  }

  @Override
  public List<PublicationDTO> getAll() {
    return mapToPublicationDTO(this.repository.all());
  }

  private List<PublicationDTO> mapToPublicationDTO(List<Publication> publications) {
    return publications.stream().map(PublicationDTO::from).collect(Collectors.toList());
  }

  @Override
  public PublicationDTO create(PublicationDTO publicationDTO) {
    validate(publicationDTO);

    User user = userRepository.get(publicationDTO.getUserId());
    Vehicle vehicle = findVehicle(publicationDTO.getVehicleId(), user);

    Publication publication = Publication.from(user, vehicle, publicationDTO);
    repository.save(publication);
    publicationDTO.setPublicationId(publication.getId());
    return publicationDTO;
  }

  @Override
  public List<PublicationDTO> getUserPublications(Long id) {
    return mapToPublicationDTO(repository.getUserPublications(userRepository.get(id)));
  }

  private void validate(PublicationDTO publicationDTO) {
    List<String> errors = new ArrayList<>();

    if (publicationDTO == null) {
      errors.add(ErrorCode.Publication.NOT_PRESENT);
    } else {
      if (publicationDTO.getUserId() == null) {
        errors.add(ErrorCode.Publication.USER_NOT_PRESENT);
      }
      if (publicationDTO.getVehicleId() == null) {
        errors.add(ErrorCode.Publication.VEHICLE_NOT_PRESENT);
      }
      if (publicationDTO.getCity() == null || publicationDTO.getCity().isEmpty()) {
        errors.add(ErrorCode.Publication.CITY_NOT_PRESENT);
      }
      if (publicationDTO.getContactPhone() == null || publicationDTO.getContactPhone().isEmpty()) {
        errors.add(ErrorCode.Publication.PHONE_NOT_PRESENT);
      }
      if (publicationDTO.getCost() == null) {
        errors.add(ErrorCode.Publication.COST_NOT_PRESENT);
      }
      if (publicationDTO.getPickUpAddress() == null || publicationDTO.getPickUpAddress().isEmpty()) {
        errors.add(ErrorCode.Publication.PICK_UP_ADDRESS_NOT_PRESENT);
      }
      if (publicationDTO.getReturnAddress() == null || publicationDTO.getReturnAddress().isEmpty()) {
        errors.add(ErrorCode.Publication.RETURN_ADDRESS_NOT_PRESENT);
      }
      if (publicationDTO.getAvailableDates() == null || publicationDTO.getAvailableDates().isEmpty()) {
        errors.add(ErrorCode.Publication.AVAILABLE_DATES_NOT_PRESENT);
      }
    }

    if (!errors.isEmpty()) {
      throw new InvalidRequestException("Error creting publication", errors);
    }
  }

  private Vehicle findVehicle(Long vehicleId, User user) {
    Vehicle vehicle = vehicleRepository.getUserVehicles(user).stream()
        .filter(vehicle1 -> vehicle1.getId().equals(vehicleId))
        .findFirst()
        .orElseThrow(() -> new InvalidRequestException(
            "Error creating publication",
            Collections.singletonList(ErrorCode.Publication.VEHICLE_DOESNT_BELONGS_TO_USER)));

    repository.findByVehicle(vehicle).ifPresent(publication -> {
      throw new InvalidRequestException("Error creating publication", Collections.singletonList(ErrorCode.Publication.PUBLICACION_WITH_VEHICLE_EXISTS));
    });

    return vehicle;
  }
}
