package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@Repository
public class PublicationRepositoryImpl extends CarpnbRepository<Publication, Long> implements PublicationRepository {
  @Override
  public List<Publication> getUserPublications(User user) {
    Query query = entityManager.createQuery(
        "SELECT u FROM Publication u WHERE u.owner = :user")
        .setParameter("user", user);
    return query.getResultList();
  }

  @Override
  public Optional<Publication> findByVehicle(Vehicle vehicle) {
    Query query = entityManager.createQuery(
        "SELECT p FROM Publication p WHERE p.vehicle = :vehicle"
    ).setParameter("vehicle", vehicle);

    return OptionalQueryResult.of(query);
  }
}
