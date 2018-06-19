package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Vehicles.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private VehicleService service;

  @Autowired
  VehicleController(VehicleService service) {
    this.service = service;
  }

  @RequestMapping(method = GET, path = "/{id}/vehicles")
  public List<Vehicle> getVehiclesFromUser(@PathVariable Long id) {
    return service.getFromUser(id);
  }

  @RequestMapping(method = POST, path = "/{id}")
  public Vehicle addVehicleToUser(@PathVariable Long id, @RequestBody Vehicle vehicle) {
    logger.info(String.format("Agregando Vehiculo a usuario => %s %s %s", id, vehicle.getLicense(), vehicle.getNumberOfPassengers()));
    return service.create(id, vehicle);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
  public void deleteVehicleFromUser(@PathVariable Long id, @RequestBody Vehicle vehicle) {
    service.delete(id, vehicle);
  }
}
