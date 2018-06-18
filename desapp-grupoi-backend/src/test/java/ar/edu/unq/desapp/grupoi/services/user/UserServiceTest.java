package ar.edu.unq.desapp.grupoi.services.user;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.contexts.TestContext;
import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepositoryImpl;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(JavaSpecRunner.class)
public class UserServiceTest extends JavaSpec<TestContext> {

  private UserService service;
  private UserRepository mockRepository;
  private Parameters parameters;
  private User existentUser;

  @Override
  public void define() {
    describe("User service", () -> {
      beforeEach(() -> {
        parameters = new Parameters();
        parameters.setMinUserNameLength(1);
        parameters.setMaxUserNameLength(1000);

        this.mockRepository = mock(UserRepositoryImpl.class);
        this.service = new UserServiceImpl(mockRepository, parameters);

      });
      afterEach(() -> reset(this.mockRepository));

      describe("Create a user", () -> {
        describe("successfull user creation", () -> {
          it("creates a user", () -> {
            User validUser = new User(null, "name", "address", "mail@test.com", "10-12345678-1", "lala");
            service.createIfNotExists(validUser);
            verify(mockRepository, times(1)).createIfNotExists(validUser);
          });
        });
        describe("can't create a user", () -> {
          it("without mail", () -> {
            try {
              service.createIfNotExists(new User(null, "", "address", null, "cuil", "laal"));
              failBecauseExceptionWasNotThrown(InvalidRequestException.class);
            } catch (InvalidRequestException e) {
              assertThat(e.errors()).contains(ErrorCode.User.EMAIL_NOT_PRESENT);
            }
          });

          it("with blank mail", () -> {
            try {
              service.createIfNotExists(new User(null, "name", "address", "", "cuil", "lala"));
              failBecauseExceptionWasNotThrown(InvalidRequestException.class);
            } catch (InvalidRequestException e) {
              assertThat(e.errors()).contains(ErrorCode.User.EMAIL_NOT_PRESENT);
            }
          });

          it("with invalid mail", () -> {
            try {
              service.createIfNotExists(new User(null, "name", "address", "invalid mail", "cuil", "lala"));
              failBecauseExceptionWasNotThrown(InvalidRequestException.class);
            } catch (InvalidRequestException e) {
              assertThat(e.errors()).contains(ErrorCode.User.EMAIL_INVALID_FORMAT);
            }
          });
        });

        describe("Update user data", () -> {
          describe("successfull user data update", () -> {
            beforeEach(() -> {
              this.existentUser = new User(
                1L,
                "name",
                "address",
                "prueba@prueba.prueba",
                "10-12345678-1",
                "avatar");

              Mockito.when(this.mockRepository.get(1)).thenReturn(this.existentUser);
            });

            it("update user data", () -> {
              UserCustomizableData userData = new UserCustomizableData(
                this.existentUser.getId(),
                this.existentUser.getName(),
                this.existentUser.getAddress(),
                this.existentUser.getCuil(),
                this.existentUser.getAvatar());

              service.update(userData);

              verify(mockRepository, times(1)).update(this.existentUser);
            });
          });

          describe("can't update user data", () -> {
            it("wihtout id", () -> {
              try {
                service.update(new UserCustomizableData(null, "name", "address", "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.ID_NOT_PRESENT);
              }
            });
            it("without a name", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), null, "address", "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.NAME_NOT_PRESENT);
              }
            });

            it("with blank name", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), "", "address", "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.NAME_NOT_PRESENT);
              }
            });

            it("with name bigger than 20", () -> {
              try {
                parameters.setMaxUserNameLength(20);
                service.update(new UserCustomizableData(Long.valueOf(1), "132456789132465798123456789", "address", "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.NAME_OUT_OF_BOUNDS);
              }
            });

            it("with name shorter than 4", () -> {
              try {
                parameters.setMinUserNameLength(4);
                service.update(new UserCustomizableData(Long.valueOf(1), "123", "address", "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.NAME_OUT_OF_BOUNDS);
              }
            });

            it("without address", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), "name", null, "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.ADDRESS_NOT_PRESENT);
              }
            });

            it("with blank address", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), "name", "", "cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.ADDRESS_NOT_PRESENT);
              }
            });

            it("without cuil", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), "name", "address", null, "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.CUIL_NOT_PRESENT);
              }
            });

            it("with blank cuil", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), "name", "address", "", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.CUIL_NOT_PRESENT);
              }
            });

            it("with blank cuil", () -> {
              try {
                service.update(new UserCustomizableData(Long.valueOf(1), "name", "address", "invalid cuil", "lala"));
                failBecauseExceptionWasNotThrown(InvalidRequestException.class);
              } catch (InvalidRequestException e) {
                assertThat(e.errors()).contains(ErrorCode.User.CUIL_INVALID_FORMAT);
              }
            });

          });
        });
      });
    });
  }
}