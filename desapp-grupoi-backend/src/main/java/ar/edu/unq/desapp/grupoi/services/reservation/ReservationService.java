package ar.edu.unq.desapp.grupoi.services.reservation;

import ar.edu.unq.desapp.grupoi.model.Reservation;

import java.util.List;

public interface ReservationService {
    void create(Reservation reservation);

    List<Reservation> getAllAsOwner(Long ownerId);

    List<Reservation> getAllAsClient(Long clientId);
}
