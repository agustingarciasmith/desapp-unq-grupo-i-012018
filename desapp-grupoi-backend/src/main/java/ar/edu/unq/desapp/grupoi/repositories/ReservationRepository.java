package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;

import java.util.List;

public interface ReservationRepository {

  void save(Reservation reservation);

  List<Reservation> getAllAsOwner(User owner);

  List<Reservation> getAllAsClient(User client);

  Reservation load(Long id);

  List<Reservation> findByPublication(Publication publication);
}
