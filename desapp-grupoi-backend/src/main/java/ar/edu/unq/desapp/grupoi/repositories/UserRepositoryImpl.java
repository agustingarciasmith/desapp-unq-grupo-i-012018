package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl extends CarpnbRepository<User, Long> implements UserRepository {

  @Override
  public User createIfNotExists(User user) {
    return searchByEmail(user.getEmail()).orElseGet(() -> {
      this.save(user);
      return user;
    });
  }

  @Override
  public User get(Long id) {

    return this.load(id);
  }

  private Optional<User> searchByEmail(String email) {
    TypedQuery<User> query = entityManager.createQuery(
      "SELECT u FROM User u WHERE u.email = :email",
      User.class)
      .setParameter("email", email.toLowerCase());
    return OptionalQueryResult.of(query);
  }
}
