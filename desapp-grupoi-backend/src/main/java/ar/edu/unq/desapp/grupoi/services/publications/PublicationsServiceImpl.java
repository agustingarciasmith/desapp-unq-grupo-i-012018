package ar.edu.unq.desapp.grupoi.services.publications;

import ar.edu.unq.desapp.grupoi.model.Publication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicationsServiceImpl implements PublicationService {

    private List<Publication> publications;

    public PublicationsServiceImpl() {
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
    public Publication create(Publication publication) {
        Integer lastId =
                this.publications
                        .stream()
                        .map(publication1 -> publication1.getId())
                        .max(Integer::compareTo)
                        .orElse(0);

        publication.setId(lastId + 1);
        this.publications.add(publication);
        return publication;
    }
}
