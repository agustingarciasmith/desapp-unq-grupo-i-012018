package ar.edu.unq.desapp.grupoi.services.user;

import ar.edu.unq.desapp.grupoi.model.User;

public interface UserService {
  User create(User userRequest);

  User update(User user);
}
