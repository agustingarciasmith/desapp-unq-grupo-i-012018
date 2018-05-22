package ar.edu.unq.desapp.grupoi.repositories;

import ar.edu.unq.desapp.grupoi.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl extends CarpnbRepository<User, Long> implements UserRepository {
}
