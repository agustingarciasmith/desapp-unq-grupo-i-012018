package ar.edu.unq.desapp.grupoi.services.user;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.rest.UserInfo;

public interface UserService {
  void update(UserCustomizableData user);

  User getById(Long id);

  User createIfNotExists(User user);

  User login(UserInfo userInfo);
}
