package ar.edu.unq.desapp.grupoi.services.user;

import ar.edu.unq.desapp.grupoi.model.User;

public interface UserService {
  void update(UserCustomizableData user);

  User createIfNotExists(User user);
}
