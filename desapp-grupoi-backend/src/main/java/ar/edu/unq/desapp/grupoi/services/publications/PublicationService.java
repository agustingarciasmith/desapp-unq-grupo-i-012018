package ar.edu.unq.desapp.grupoi.services.publications;

import ar.edu.unq.desapp.grupoi.model.Publication;

import java.util.List;

public interface PublicationService {
    List<Publication> getAll();

    Publication getById(Long id);

    Publication create(Publication publication);
}
