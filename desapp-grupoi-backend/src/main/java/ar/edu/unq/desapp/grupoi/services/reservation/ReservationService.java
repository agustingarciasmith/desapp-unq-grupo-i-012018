package ar.edu.unq.desapp.grupoi.services.reservation;

import ar.edu.unq.desapp.grupoi.rest.requests.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO create(ReservationDTO reservation);

    void confirmReservation(Long reservationId, Long ownerId);

    void clientGetVehicle(Long reservationId, Long clientId);

    void ownerConfirmVehicleDelivery(Long reservationId, Long ownerId);

    void clientReturnVehicle(Long reservationId, Long clientId, Integer score);

    void ownerReciveVehicle(Long reservationId, Long ownerId, Integer score);

    List<ReservationDTO> getAllAsOwner(Long ownerId);

    List<ReservationDTO> getAllAsClient(Long clientId);

    ReservationDTO getById(Long id);
}
