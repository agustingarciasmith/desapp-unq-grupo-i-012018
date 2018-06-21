package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.rest.requests.PublicationDTO;

import java.util.List;
import java.util.Optional;

public interface PublicationService {
    List<PublicationDTO> getAll();

    Publication create(PublicationDTO publication);

    List<PublicationDTO> getUserPublications(Long id);
}
