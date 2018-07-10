package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.rest.requests.ReservationDTO;
import ar.edu.unq.desapp.grupoi.services.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Reservation.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {
  private ReservationService service;

  @Autowired
  ReservationController(ReservationService service) {
    this.service = service;
  }

  @RequestMapping(method = POST, path = "/create")
  public void create(@RequestBody ReservationDTO reservationDTO) {
    service.create(reservationDTO);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
  public List<ReservationDTO> getReservationsAsOwner(@PathVariable Long id) {
    return service.getAllAsOwner(id);
  }

  @RequestMapping(value = "/owner/{ownerId}/confirm/{reservationId}", method = RequestMethod.PUT)
  public void ownerConfirmReservation(@PathVariable("ownerId") Long ownerId, @PathVariable("reservationId") Long reservationId) {
    service.confirmReservation(reservationId, ownerId);
  }

  @RequestMapping(value = "/owner/{ownerId}/cancel/{reservationId}", method = RequestMethod.PUT)
  public void ownerCancelReservation(@PathVariable("ownerId") Long ownerId, @PathVariable("reservationId") Long reservationId) {
    service.ownerCancelReservation(reservationId, ownerId);
  }

  @RequestMapping(value = "/owner/{ownerId}/confirmVehicleDelivery/{reservationId}", method = RequestMethod.PUT)
  public void ownerConfirmVehicleDelivery(@PathVariable("ownerId") Long ownerId, @PathVariable("reservationId") Long reservationId) {
    service.ownerConfirmVehicleDelivery(reservationId, ownerId);
  }

  @RequestMapping(value = "/owner/{ownerId}/reciveVehicle/{reservationId}", method = RequestMethod.PUT)
  public void ownerReciveVehicle(@PathVariable("ownerId") Long ownerId, @PathVariable("reservationId") Long reservationId, @RequestBody Map<String, Integer> score) {
    service.ownerReciveVehicle(reservationId, ownerId, score.get("score"));
  }

  @RequestMapping(method = RequestMethod.GET, value = "/client/{id}")
  public List<ReservationDTO> getReservationsAsClient(@PathVariable Long id) {
    return service.getAllAsClient(id);
  }

  @RequestMapping(value = "/client/{clientId}/getVehicle/{reservationId}", method = RequestMethod.PUT)
  public void clientGetVehicle(@PathVariable("clientId") Long clientId, @PathVariable("reservationId") Long reservationId) {
    service.clientGetVehicle(reservationId, clientId);
  }

  @RequestMapping(value = "/client/{clientId}/returnVehicle/{reservationId}", method = RequestMethod.PUT)
  public void clientReturnVehicle(@PathVariable("clientId") Long clientId, @PathVariable("reservationId") Long reservationId, @RequestBody Map<String, Integer> score) {
    service.clientReturnVehicle(reservationId, clientId, score.get("score"));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ReservationDTO getReservation(@PathVariable("id") Long id) {
    return service.getById(id);
  }

}
