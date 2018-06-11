package ar.edu.unq.desapp.grupoi.services.user;

import ar.edu.unq.desapp.grupoi.model.User;

public interface UserService {
  void update(UserCustomizableData user);

  User getById(Long id);

  User createIfNotExists(User user);
}
