package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationServiceImpl implements PublicationService {
    public PublicationRepository repository;

    public PublicationServiceImpl() {
        this.repository = new PublicationRepositoryImpl();
    }


    @Override
    public List<Publication> getAll() {
        return this.repository.getAll();
    }

    @Override
    public Publication getById(Long id) {
        return null;
    }

    @Override
    public void create(Publication publication) {
        repository.create(publication);
    }
}
