package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;

import java.util.List;

public interface PublicationRepository {
    void create(Publication publication);
    List<Publication> getAll();
}
