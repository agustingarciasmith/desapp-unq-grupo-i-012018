package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.services.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Reservation.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {

    private ReservationService service;

    @Autowired
    ReservationController(ReservationService service) { this.service = service; }

    @RequestMapping(method = POST, path = Endpoints.Reservation.CREATE)
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
}
