package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class PublicationRepositoryImpl extends CarpnbRepository<Publication, Long> implements PublicationRepository {
    @Override
    public List<Publication> getUserPublications(User user) {
        Query query = entityManager.createQuery(
                "SELECT u FROM Publication u WHERE u.owner = :user")
                .setParameter("user", user);
        return query.getResultList();
    }
}
