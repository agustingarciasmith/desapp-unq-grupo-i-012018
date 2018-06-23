package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ReservationRepositoryImpl extends CarpnbRepository<Reservation, Long> implements ReservationRepository {
    @Override
    public List<Reservation> getAllAsOwner(Long id) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "SELECT r FROM Reservation r WHERE r.owner.id = :id",
                Reservation.class)
                .setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Reservation> getAllAsClient(Long id) {
        TypedQuery<Reservation> query = entityManager.createQuery(
                "SELECT r FROM Reservation r WHERE r.client.id = :id",
                Reservation.class)
                .setParameter("id", id);
        return query.getResultList();
    }


}
