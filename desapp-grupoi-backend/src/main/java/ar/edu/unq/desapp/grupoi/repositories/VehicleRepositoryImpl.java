package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepositoryImpl extends CarpnbRepository<Vehicle, Long> implements VehicleRepository {
    @Override
    public void create(Vehicle vehicle) {
        this.save(vehicle);
    }
}
