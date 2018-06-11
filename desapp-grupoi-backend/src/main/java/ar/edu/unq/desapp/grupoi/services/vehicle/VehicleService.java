package ar.edu.unq.desapp.grupoi.services.vehicle;

import ar.edu.unq.desapp.grupoi.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<Vehicle> getFromUser(Long userId);
    void create(Long userId, Vehicle vehicle);
    void delete(Long userId, Vehicle vehicle);
}
