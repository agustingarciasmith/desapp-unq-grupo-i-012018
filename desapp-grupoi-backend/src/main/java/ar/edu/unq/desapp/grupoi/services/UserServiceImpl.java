package ar.edu.unq.desapp.grupoi.services;

import ar.edu.unq.desapp.grupoi.model.CuilValidator;
import ar.edu.unq.desapp.grupoi.model.EmailFormatValidator;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.NameLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public void create(User user) {
        validate(user);
        repository.create(user);
    }

    private void validate(User user) {
        if(user.getName() == null) throw new RuntimeException("Name must not be null");
        if(user.getAddress() == null) throw new RuntimeException("Address must not be null");
        if(user.getCuil() == null) throw new RuntimeException("Cuil must not be null");
        if(user.getEmail() == null) throw new RuntimeException("Email must not be null");

        if (user.getName().length() < 4 || user.getName().length() > 50) throw new NameLengthOutOfBounds();
        EmailFormatValidator.runValidation(user.getEmail());
        CuilValidator.runValidation(user.getCuil());
    }
}
