package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
  void create(Vehicle vehicle);

  List<Vehicle> getUserVehicles(User user);

  Vehicle load(Long vehicleId);

  void delete(Vehicle vehicle);
}
