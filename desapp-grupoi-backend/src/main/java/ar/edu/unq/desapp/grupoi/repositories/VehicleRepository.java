package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
  void create(Vehicle vehicle);

  void delete(Long vehicleId);

  List<Vehicle> getUserVehicles(User user);
}
