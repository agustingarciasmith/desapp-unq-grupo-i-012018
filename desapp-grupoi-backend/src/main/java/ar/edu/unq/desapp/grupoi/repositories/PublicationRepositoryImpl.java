package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;

public class PublicationRepositoryImpl extends CarbnbRepository<Publication, Long> implements PublicationRepository {
    @Override
    public void create(Publication publication) { this.save(publication); }
}
