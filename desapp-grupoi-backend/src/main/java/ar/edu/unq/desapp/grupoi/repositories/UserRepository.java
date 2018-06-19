package ar.edu.unq.desapp.grupoi.repositories;


import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;

public interface UserRepository {
  void update(User user);

  User createIfNotExists(User user);

  User get(Long id);
}
