package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.rest.requests.PublicationDTO;
import ar.edu.unq.desapp.grupoi.services.publication.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static ar.edu.unq.desapp.grupoi.rest.Endpoints.Publications.CREATE;
import static ar.edu.unq.desapp.grupoi.rest.Endpoints.Publications.USER_PUBLICATIONS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Publications.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class PublicationController {
  private PublicationService service;

  @Autowired
  PublicationController(PublicationService service) {
    this.service = service;
  }

  @RequestMapping(method = GET, path = "/all")
  public List<PublicationDTO> getPublications() {
    return service.getAll();
  }

  @RequestMapping(method = GET, path = USER_PUBLICATIONS + "/{id}")
  public List<PublicationDTO> getUserPublications(@PathVariable("id") Long id) {
    return this.service.getUserPublications(id);
  }

  @RequestMapping(method = GET, path = "/{id}")
  public PublicationDTO getPublication(@PathVariable("id") Long id) {
    return this.service.getPublication(id);
  }

  @RequestMapping(method = POST, path = CREATE)
  public PublicationDTO create(@RequestBody PublicationDTO publication) {
    return service.create(publication);
  }
}

