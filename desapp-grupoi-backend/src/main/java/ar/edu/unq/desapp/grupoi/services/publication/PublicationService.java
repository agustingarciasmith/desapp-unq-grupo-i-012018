package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;

import java.util.List;
import java.util.Optional;

public interface PublicationService {
    List<Publication> getAll();

    Optional<Publication> getById(Long id);

    void create(Publication publication);
}
