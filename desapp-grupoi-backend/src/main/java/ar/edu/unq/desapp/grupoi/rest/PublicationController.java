package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.support.DummyData;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
import ar.edu.unq.desapp.grupoi.services.publications.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "localhost:4200")
@RestController
@RequestMapping(value = "/publications")
public class PublicationController {

    private PublicationService service;

    @Autowired
    PublicationController(PublicationService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Publication> getPublications() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Publication getPublication(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Publication createPublication(@RequestBody Publication publication) {
        return service.create(publication);
    }
}

