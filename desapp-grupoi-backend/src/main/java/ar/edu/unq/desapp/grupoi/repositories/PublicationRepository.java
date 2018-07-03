package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PublicationRepository {
  List<Publication> getUserPublications(User user);

  List<Publication> all();

  void save(Publication publication);

  Optional<Publication> findByVehicle(Vehicle vehicle);

  Publication load(Long id);
}
