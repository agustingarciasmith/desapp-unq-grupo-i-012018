package ar.edu.unq.desapp.grupoi.services.publication;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.rest.requests.PublicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
  public List<PublicationDTO> getAll() {
    return mapToPublicationDTO(this.repository.all());
  }

  private List<PublicationDTO> mapToPublicationDTO(List<Publication> publications) {
    return publications.stream().map(publication -> {
      PublicationDTO dto = new PublicationDTO();
      dto.setPublicationId(publication.getId());
      dto.setAvalilableDates(publication.getAvailableDates());
      dto.setVehicleId(publication.getVehicle().getId());
      dto.setCity(publication.getCity());
      dto.setPickUpAddress(publication.getPickUpAddress());
      dto.setReturnAddress(publication.getReturnAddress());
      dto.setContactPhone(publication.getContactPhone());
      dto.setCost(publication.getCost());
      dto.setUserId(publication.getOwner().getId());

      return dto;
    }).collect(Collectors.toList());
  }

  @Override
  public PublicationDTO create(PublicationDTO publicationDTO) {
    validate(publicationDTO);
    userRepository.get(publicationDTO.getUserId());
    Publication publication = new Publication()
    repository.save(publication);
    return publication;
  }

  @Override
  public List<PublicationDTO> getUserPublications(Long id) {
    return mapToPublicationDTO(repository.getUserPublications(userRepository.get(id)));
  }
}
