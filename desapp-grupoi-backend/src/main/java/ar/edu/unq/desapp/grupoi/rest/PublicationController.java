package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.services.publication.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(method = GET)
    public List<Publication> getPublications() {
        return service.getAll();
    }

    @RequestMapping(method = GET, path = Endpoints.Publications.USER_PUBLICATIONS + "/{id}")
    public List<Publication> getUserPublications(@PathVariable("id") Long id) {
        return this.service.getUserPublications(id);
    }

    @RequestMapping(method = POST, path = Endpoints.Publications.CREATE)
    public Publication create(@RequestBody Publication publication) {
        return service.create(publication);
    }
}

