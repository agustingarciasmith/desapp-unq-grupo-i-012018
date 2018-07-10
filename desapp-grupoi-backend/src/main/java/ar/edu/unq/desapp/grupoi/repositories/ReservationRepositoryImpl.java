package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.reservationStates.ClientReceivedState;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ReservationRepositoryImpl extends CarpnbRepository<Reservation, Long> implements ReservationRepository {
  @Override
  public List<Reservation> getAllAsOwner(User owner) {
    TypedQuery<Reservation> query = entityManager.createQuery(
        "SELECT r FROM Reservation r WHERE r.publication.owner = :owner",
        Reservation.class)
        .setParameter("owner", owner);
    return query.getResultList();
  }

  @Override
  public List<Reservation> getAllAsClient(User client) {
    TypedQuery<Reservation> query = entityManager.createQuery(
        "SELECT r FROM Reservation r WHERE r.client = :client",
        Reservation.class)
        .setParameter("client", client);
    return query.getResultList();
  }

  @Override
  public List<Reservation> findByPublication(Publication publication) {
    TypedQuery<Reservation> query = entityManager.createQuery(
        "SELECT r FROM Reservation r WHERE r.publication = :publication",
        Reservation.class)
        .setParameter("publication", publication);
    return query.getResultList();
  }

  @Override
  public List<Reservation> findReservationsWithClientRecivedCarSate() {
    Query query = entityManager.createQuery(
      "SELECT r FROM Reservation r WHERE r.state.class = :state"
    ).setParameter("state", ClientReceivedState.class.getName());

    return query.getResultList();
  }
}
