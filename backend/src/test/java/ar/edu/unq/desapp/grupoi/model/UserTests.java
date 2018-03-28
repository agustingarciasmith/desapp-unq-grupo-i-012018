package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

@RunWith(JavaSpecRunner.class)
public class UserTests extends JavaSpec<TestContext> {

  @Override
  public void define() {
    describe("user creation", () -> {

      it("cant create with name shorter than 4 characters", () -> {
        try {
          String toShortName = "nan";
          new User(toShortName, "address", "mail@mail.com", "cuil");

          failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
          assertThat(e).hasMessage(User.NAME_IS_TOO_SHORT);
        }
      });

      it("cant create with namte long than 50m characters", () -> {
        try {
          String toLongName = "The name and lastname are too long for registration";
          new User(toLongName, "address", "mail@mail.com", "cuil");

          failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
          assertThat(e).hasMessage(User.NAME_IS_TOO_LONG);
        }
      });

      it("cant create with invalid mail", () -> {
        try {
          String notAMail = "wrongemailadress";
          new User("name", "address", notAMail, "cuil");

          failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
          assertThat(e).hasMessage(EmailFormatValidator.EMAIL_ADDRESS_IS_INVALID);
        }
      });
    });

//    describe("vehicle publication", () -> {
//      it("add a publication", () -> {
//        User user = new User("pepe", "calle de pepe", "mail@pepe.com", "2134567");
//        user.createPublication(defaultVehicle());
//        assertThat(user.getPublications()).hasSize(1);
//      });
//    });
  }
}
