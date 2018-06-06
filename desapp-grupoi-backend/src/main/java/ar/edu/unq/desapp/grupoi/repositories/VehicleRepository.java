package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Vehicle;

public interface VehicleRepository {
    void create(Vehicle vehicle);

    void delete(Vehicle vehicle);
}
