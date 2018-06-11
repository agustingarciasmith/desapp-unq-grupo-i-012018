package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class VehicleController {

    private VehicleService service;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        service.create(id, vehicle);
    }

}
