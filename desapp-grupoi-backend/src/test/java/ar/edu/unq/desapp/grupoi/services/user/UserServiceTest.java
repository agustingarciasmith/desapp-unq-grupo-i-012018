package ar.edu.unq.desapp.grupoi.services.user;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepositoryImpl;
import org.junit.runner.RunWith;

import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JavaSpecRunner.class)
public class UserServiceTest extends JavaSpec<TestContext> {

  private UserService service;
  private UserRepository mockRepository;
  private Parameters parameters;

  @Override
  public void define() {
    describe("User service", () -> {
      beforeEach(() -> {
        parameters = new Parameters();
        parameters.setMinUserNameLenght(1);
        parameters.setMaxUserNameLenght(1000);

        this.mockRepository = mock(UserRepositoryImpl.class);
        this.service = new UserServiceImpl(mockRepository, parameters);

      });
      afterEach(() -> reset(this.mockRepository));

      describe("Create a user", () -> {
        describe("successfull user creation", () -> {
          it("creates a user", () -> {
            User validUser = new User(null, "name", "address", "mail@test.com", "10-12345678-1");
            service.create(validUser);
            verify(mockRepository, times(1)).save(validUser);
          });
        });
        describe("can't create a user", () -> {
          validationCases((user -> service.create(user)));
        });
      });
      describe("Update user data", () -> {
        describe("successfull user daata update", () -> {
          it("creates a user", () -> {
            User validUser = new User(Long.valueOf(1), "name", "address", "mail@test.com", "10-12345678-1");
            service.update(validUser);
            verify(mockRepository, times(1)).update(validUser);
          });
        });
        describe("can't update user data", () -> {
          it("wihtout id", () -> {
            try {
              service.update(new User(null, "name", "address", "email", "cuil"));
              failBecauseExceptionWasNotThrown(InvalidRequestException.class);
            } catch (InvalidRequestException e) {
              assertThat(e.errores()).contains(ErrorCode.User.ID_NOT_PRESENT);
            }
          });
          validationCases((user) -> service.update(user));
        });
      });
    });
  }

  private void validationCases(Consumer<User> consumer) {
    it("without a name", () -> {
      try {
        consumer.accept(new User(null, null, "address", "email", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.NAME_NOT_PRESENT);
      }
    });

    it("with blank name", () -> {
      try {
        consumer.accept(new User(null, "", "address", "email", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.NAME_NOT_PRESENT);
      }
    });

    it("with name bigger than 20", () -> {
      try {
        parameters.setMaxUserNameLenght(20);
        consumer.accept(new User(null, "132456789132465798123456789", "address", "email", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.NAME_OUT_OF_BOUNDS);
      }
    });

    it("with name shorter than 4", () -> {
      try {
        parameters.setMinUserNameLenght(4);
        consumer.accept(new User(null, "123", "address", "email", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.NAME_OUT_OF_BOUNDS);
      }
    });

    it("without mail", () -> {
      try {
        consumer.accept(new User(null, "", "address", null, "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.EMAIL_NOT_PRESENT);
      }
    });

    it("with blank mail", () -> {
      try {
        consumer.accept(new User(null, "name", "address", "", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.EMAIL_NOT_PRESENT);
      }
    });

    it("with invalid mail", () -> {
      try {
        consumer.accept(new User(null, "name", "address", "invalid mail", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.EMAIL_INVALID_FORMAT);
      }
    });

    it("without address", () -> {
      try {
        consumer.accept(new User(null, "name", null, "email", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.ADDRESS_NOT_PRESENT);
      }
    });

    it("with blank address", () -> {
      try {
        consumer.accept(new User(null, "name", "", "email", "cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.ADDRESS_NOT_PRESENT);
      }
    });

    it("without cuil", () -> {
      try {
        consumer.accept(new User(null, "name", "address", "email", null));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.CUIL_NOT_PRESENT);
      }
    });

    it("with blank cuil", () -> {
      try {
        consumer.accept(new User(null, "name", "address", "email", ""));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.CUIL_NOT_PRESENT);
      }
    });

    it("with blank cuil", () -> {
      try {
        consumer.accept(new User(null, "name", "address", "email", "invalid cuil"));
        failBecauseExceptionWasNotThrown(InvalidRequestException.class);
      } catch (InvalidRequestException e) {
        assertThat(e.errores()).contains(ErrorCode.User.CUIL_INVALID_FORMAT);
      }
    });
  }
}