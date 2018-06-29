package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Reservation;

import java.util.List;

public interface ReservationRepository {

    void save(Reservation reservation);

    List<Reservation> getAllAsOwner(Long id);

    List<Reservation> getAllAsClient(Long id);

    Reservation load(Long id);
}
