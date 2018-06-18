package ar.edu.unq.desapp.grupoi.services.user;

import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.CuilValidator;
import ar.edu.unq.desapp.grupoi.model.EmailFormatValidator;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.rest.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
  private List<User> users;
  private UserRepository repository;
  private Parameters parameters;

  @Autowired
  public UserServiceImpl(UserRepository repository, Parameters parameters) {
    this.repository = repository;
    this.parameters = parameters;
  }

  @Override
  public void update(User user) {
    //validate(userData);
    //User user = repository.get(userData.getId());
    //user.updateFrom(userData);
    repository.update(user);
  }

  @Override
  public User getById(Long id) {
    return repository.get(id);
  }

  @Override
  public User createIfNotExists(User user) {
    if (isNullOrEmpty(user.getEmail())) throw new InvalidRequestException(
      "User data error",
      Collections.singletonList(ErrorCode.User.EMAIL_NOT_PRESENT));
    if (!EmailFormatValidator.runValidation(user.getEmail())) throw new InvalidRequestException(
      "User email error",
      Collections.singletonList(ErrorCode.User.EMAIL_INVALID_FORMAT));

    return repository.createIfNotExists(user);
  }

  @Override
  public User login(UserInfo mayBeUserInfo) {
    return this.createIfNotExists(
      Optional.of(mayBeUserInfo)
        .map(userInfo -> new User(
          null,
          userInfo.getName(),
          null,
          userInfo.getEmail(),
          null,
          userInfo.getPicture()))
        .orElseThrow(() -> new InvalidRequestException(
          "Error in login ",
          Collections.singletonList(ErrorCode.Login.USER_INFO_NOT_PRESENT)))
    );
  }

  private void validate(UserCustomizableData user) {
    List<String> errors = new ArrayList<>();

    if (user.getId() == null) errors.add(ErrorCode.User.ID_NOT_PRESENT);
    if (isNullOrEmpty(user.getAddress())) errors.add(ErrorCode.User.ADDRESS_NOT_PRESENT);
    if (isNullOrEmpty(user.getCuil())) errors.add(ErrorCode.User.CUIL_NOT_PRESENT);
    if (user.getCuil() != null &&
      !CuilValidator.runValidation(user.getCuil())) errors.add(ErrorCode.User.CUIL_INVALID_FORMAT);

    if (isNullOrEmpty(user.getName())) errors.add(ErrorCode.User.NAME_NOT_PRESENT);
    if (user.getName() != null && (
      user.getName().length() < parameters.getMinUserNameLength() ||
        user.getName().length() > parameters.getMaxUserNameLength())) {
      errors.add(ErrorCode.User.NAME_OUT_OF_BOUNDS);
    }

    if (!errors.isEmpty()) {
      throw new InvalidRequestException("Error validating user data", errors);
    }
  }


  private Boolean isNullOrEmpty(String field) {
    return field == null || field.isEmpty();
  }
}
