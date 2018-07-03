package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.rest.requests.PublicationDTO;

import java.util.List;

public interface PublicationService {
  List<PublicationDTO> getAll();

  PublicationDTO create(PublicationDTO publication);

  List<PublicationDTO> getUserPublications(Long id);

  PublicationDTO getPublication(Long id);
}
