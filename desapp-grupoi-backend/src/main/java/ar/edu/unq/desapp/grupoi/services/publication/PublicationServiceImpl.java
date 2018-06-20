package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository repository;
    private UserRepository userRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    @Override
    public List<Publication> getAll() {
        return (List<Publication>) this.repository.all();
    }

    @Override
    public Publication create(Publication publication) {
        repository.save(publication);
        return publication;
    }

    @Override
    public List<Publication> getUserPublications(Long id) {
        return repository.getUserPublications(userRepository.get(id));
    }
}
