package ar.edu.unq.desapp.grupoi.repositories;


import ar.edu.unq.desapp.grupoi.model.User;

public interface UserRepository {
  void save(User user);

  void update(User user);

  User createIfNotExists(User user);
}
