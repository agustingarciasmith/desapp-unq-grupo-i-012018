package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    public PublicationRepository repository;

    public PublicationServiceImpl() {}

    @Override
    public List<Publication> getAll() {
        return (List<Publication>) this.repository.findAll();
    }

    @Override
    public Optional<Publication> getById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void create(Publication publication) {
        repository.save(publication);
    }
}
