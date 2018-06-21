package ar.edu.unq.desapp.grupoi.services.vehicle;

import ar.edu.unq.desapp.grupoi.rest.requests.VehicleDTO;

import java.util.List;

public interface VehicleService {
    List<VehicleDTO> getFromUser(Long userId);
    VehicleDTO create(VehicleDTO vehicleDTO);
    void delete(Long vehicleId);
}
