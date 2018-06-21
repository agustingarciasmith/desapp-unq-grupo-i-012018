package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl extends CarpnbRepository<Vehicle, Long> implements VehicleRepository {
  @Override
  public void create(Vehicle vehicle) {
    this.save(vehicle);
  }

  @Override
  public void delete(Long vehicleId) {
    this.delete(this.load(vehicleId));
  }

  @Override
  public List<Vehicle> getUserVehicles(User user) {
    Query query = entityManager.createQuery(
        "SELECT u FROM Vehicle u WHERE u.owner = :owner")
        .setParameter("owner", user);
    return query.getResultList();
  }
}
