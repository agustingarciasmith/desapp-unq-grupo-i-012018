package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private VehicleService service;

  @Autowired
  VehicleController(VehicleService service) {
    this.service = service;
  }

  @GetMapping("/users/{id}/vehicles")
  public List<Vehicle> getVehiclesFromUser(@PathVariable Long id) {
    return service.getFromUser(id);
  }

  @PostMapping("users/{id}/vehicles")
  public void addVehicleToUser(@PathVariable Long id, @RequestBody Vehicle vehicle) {
    logger.info(String.format("Agregando Vehiculo a usuario => %s %s %s", id, vehicle.getLicense(), vehicle.getNumberOfPassengers()));
    service.create(id, vehicle);
  }
}