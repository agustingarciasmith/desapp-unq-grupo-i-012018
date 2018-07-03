package ar.edu.unq.desapp.grupoi.services.vehicle;

import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepository;
import ar.edu.unq.desapp.grupoi.rest.requests.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class VehicleServiceImpl implements VehicleService {
  private UserRepository userRepository;
  private VehicleRepository vehicleRepository;
  private Parameters parameters;
  private PublicationRepository publicationRepository;

  @Autowired
  public VehicleServiceImpl(
      UserRepository userRepository,
      VehicleRepository vehicleRepository,
      Parameters parameters,
      PublicationRepository publicationRepository) {

    this.userRepository = userRepository;
    this.vehicleRepository = vehicleRepository;
    this.parameters = parameters;
    this.publicationRepository = publicationRepository;
  }

  @Override
  public List<VehicleDTO> getFromUser(Long userId) {
    validateUserIdPresence(userId);
    User user = userRepository.get(userId);

    return this.vehicleRepository.getUserVehicles(user).stream().map(vehicle -> {
      VehicleDTO dto = new VehicleDTO();
      dto.setVehicleId(vehicle.getId());
      dto.setUserId(vehicle.getOwner().getId());
      dto.setDescription(vehicle.getDescription());
      dto.setLicense(vehicle.getLicense());
      dto.setNumberOfPassengers(vehicle.getNumberOfPassengers());
      dto.setVehicleType(vehicle.getType());

      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public VehicleDTO create(VehicleDTO vehicleDTO) {
    validateVehicleCreation(vehicleDTO);
    validateUserIdPresence(vehicleDTO.getUserId());

    User user = userRepository.get(vehicleDTO.getUserId());
    Vehicle vehicle = new Vehicle(vehicleDTO.getVehicleType(), vehicleDTO.getNumberOfPassengers(), vehicleDTO.getDescription(), vehicleDTO.getLicense(), vehicleDTO.getPicture(), user);

    vehicleRepository.create(vehicle);

    vehicleDTO.setVehicleId(vehicle.getId());
    return vehicleDTO;
  }

  @Override
  public void delete(Long vehicleId) {
    validateVehicleIdPresence(vehicleId);

    Vehicle vehicle = this.vehicleRepository.load(vehicleId);

    validateExistentPublication(vehicle);

    this.vehicleRepository.delete(vehicle);
  }

  private void validateExistentPublication(Vehicle vehicle) {
    this.publicationRepository.findByVehicle(vehicle).ifPresent(publication -> {
      throw new InvalidRequestException(
          "Error deleting vehicle",
          Collections.singletonList(ErrorCode.Vehicle.CANT_DELETE_PUBLICATION_ASOCIATED));
    });
  }

  private void validateVehicleIdPresence(Long vehicleId) {
    if (vehicleId == null) {
      throw new InvalidRequestException(
          "Error fetching vehicle",
          Collections.singletonList(ErrorCode.Vehicle.ID_NOT_PRESENT));

    }
  }

  private void validateVehicleCreation(VehicleDTO vehicle) {
    List<String> errors = new ArrayList<>();

    if (vehicle == null) {
      errors.add(ErrorCode.Vehicle.NOT_PRESENT);
    } else {
      if (vehicle.getVehicleType() == null) {
        errors.add(ErrorCode.Vehicle.TYPE_NOT_PRESENT);
      }

      if (vehicle.getNumberOfPassengers() == null || vehicle.getNumberOfPassengers() <= 0) {
        errors.add(ErrorCode.Vehicle.NUMBER_OF_PASSANGERS_INVALID);
      }

      if (vehicle.getDescription() == null ||
          (vehicle.getDescription().length() < parameters.getMinVehicleDescriptionLength() ||
              vehicle.getDescription().length() > parameters.getMaxVehicleDescriptionLength())) {
        errors.add(ErrorCode.Vehicle.DESCRIPTION_OUT_OF_BOUNDS);
      }

      if (vehicle.getLicense() == null || vehicle.getLicense().isEmpty()) {
        errors.add(ErrorCode.Vehicle.LICENSE_NOT_PRESENT);
      }
    }


    if (!errors.isEmpty()) {
      throw new InvalidRequestException("Error creting vehicle", errors);
    }
  }


  private void validateUserIdPresence(Long userId) {
    if (userId == null) throw new InvalidRequestException(
        "Error fetching user",
        Collections.singletonList(ErrorCode.User.ID_NOT_PRESENT));
  }
}
