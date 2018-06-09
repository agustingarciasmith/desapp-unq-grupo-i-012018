package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class PublicationRepositoryImpl extends CarpnbRepository<Publication, Long> implements PublicationRepository {

    @Override
    public void create(Publication publication) { this.save(publication); }

    @Override
    public List<Publication> getAll() {
        return all();
    }
}
