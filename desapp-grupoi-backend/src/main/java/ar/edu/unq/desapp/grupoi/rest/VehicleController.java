package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.rest.requests.VehicleDTO;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @RequestMapping(method = GET, path = "/user/{id}")
  public List<VehicleDTO> getVehiclesFromUser(@PathVariable Long id) {
    return service.getFromUser(id);
  }

  @RequestMapping(method = POST, path = "/create")
  public VehicleDTO createVehicle(@RequestBody VehicleDTO vehicle) {
    logger.info(String.format("Agregando Vehiculo a usuario => %s %s %s", vehicle.getVehicleId(), vehicle.getLicense(), vehicle.getNumberOfPassengers()));
    return service.create(vehicle);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
  public void deleteVehicleFromUser(@PathVariable("id") Long vehicleId) {
    service.delete(vehicleId);
  }
}
