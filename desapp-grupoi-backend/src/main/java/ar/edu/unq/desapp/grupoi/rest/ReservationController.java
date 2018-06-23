package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.services.reservation.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Reservation.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
    private ReservationService service;

    @Autowired
    ReservationController(ReservationService service) { this.service = service; }

    @RequestMapping(method = POST, path = "/create")
    public void create(@RequestBody Reservation reservation) {
        service.create(reservation);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
    public List<Reservation> getReservationsAsOwner(@PathVariable Long id) {
        return service.getAllAsOwner(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/{id}")
    public List<Reservation> getReservationsAsClient(@PathVariable Long id) {
        return service.getAllAsClient(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Reservation> getReservation(@PathVariable("id") Long id) {
        return Optional.ofNullable(service.getById(id));
    }
}
