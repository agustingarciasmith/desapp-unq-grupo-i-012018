package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.support.DummyData;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
import ar.edu.unq.desapp.grupoi.services.publications.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET)
    public List<Publication> getPublications() {
        return service.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Publication getPublication(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @RequestMapping(method = POST, path = Endpoints.Publications.CREATE)
    public void create(@RequestBody Publication publication) {
        service.create(publication);
    }
}

