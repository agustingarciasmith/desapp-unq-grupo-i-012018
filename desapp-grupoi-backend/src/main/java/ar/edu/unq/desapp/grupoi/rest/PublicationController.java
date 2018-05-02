package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.support.DummyData;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/publications")
public class PublicationController {

    private List<Publication> publications;


    PublicationController() {
        this.publications = DummyData.setOfPublications();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Publication> getPublications() {
        return this.publications;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Publication getPublication(@PathVariable("id") Long id) {
        return this.publications.stream().filter(publication -> publication.getId() == id).findFirst().orElse(null);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Publication savePublication(@RequestBody Publication publication) {
        int nextId = 0;
        if (this.publications.size() != 0) {
            Publication lastPublication = this.publications.stream().skip(this.publications.size() - 1).findFirst().orElse(null);
            nextId = lastPublication.getId() + 1;
        }

        publication.setId(nextId);
        this.publications.add(publication);
        return publication;
    }
}

