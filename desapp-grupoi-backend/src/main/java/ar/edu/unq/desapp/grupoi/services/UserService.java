package ar.edu.unq.desapp.grupoi.services;

import ar.edu.unq.desapp.grupoi.model.User;

import java.util.List;

public interface UserService {
    void create(User userRequest);

    List<User> getAll();
}
