package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {
    private List<Publication> publications;
    public PublicationRepository repository;

    public PublicationServiceImpl() {
        this.repository = new PublicationRepositoryImpl();
        this.publications = new ArrayList<>();
    }


    @Override
    public List<Publication> getAll() {
        return this.publications;
    }

    @Override
    public Publication getById(Long id) {
        return this.publications
                .stream()
                .filter(publication -> publication.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public void create(Publication publication) {
        repository.create(publication);
    }
}
