package ar.edu.unq.desapp.grupoi.services.user;

import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.CuilValidator;
import ar.edu.unq.desapp.grupoi.model.EmailFormatValidator;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
  private UserRepository repository;
  private Parameters parameters;

  @Autowired
  public UserServiceImpl(UserRepository repository, Parameters parameters) {
    this.repository = repository;
    this.parameters = parameters;
  }

  @Override
  public void create(User user) {
    validate(user);
    repository.create(user);
  }

  private void validate(User user) {
    List<String> errors = new ArrayList<>();
    if (user.getAddress() == null) errors.add(ErrorCode.User.ADDRESS_NOT_PRESENT);

    if (user.getCuil() == null) errors.add(ErrorCode.User.CUIL_NOT_PRESENT);
    if (CuilValidator.runValidation(user.getCuil())) errors.add(ErrorCode.User.CUIL_INVALID_FORMAT) ;

    if (user.getEmail() == null) errors.add(ErrorCode.User.EMAIL_NOT_PRESENT);
    if (EmailFormatValidator.runValidation(user.getEmail())) errors.add(ErrorCode.User.EMAIL_INVALID_FORMAT);

    if (user.getName() == null) errors.add(ErrorCode.User.NAME_NOT_PRESENT);
    if (user.getName().length() < parameters.getMinUserNameLenght() ||
      user.getName().length() > parameters.getMaxUserNameLenght()) {
      errors.add(ErrorCode.User.NAME_OUT_OF_BOUNDS);
    }

    if (!errors.isEmpty()) {
      throw new InvalidRequestException("Error validating user data", errors);
    }
  }
}
