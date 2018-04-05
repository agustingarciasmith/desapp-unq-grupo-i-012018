package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.edu.unq.desapp.grupoi.model.errors.EmailIsInvalid;
import ar.edu.unq.desapp.grupoi.model.errors.NameLengthOutOfBounds;
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
          String tooShortName = "nan";
          new User(tooShortName, "address", "mail@mail.com", "cuil");

          failBecauseExceptionWasNotThrown(NameLengthOutOfBounds.class);
        } catch (NameLengthOutOfBounds e) {
          assertThat(e).hasMessage(NameLengthOutOfBounds.MESSAGE);
        }
      });

      it("cant create with namte long than 50m characters", () -> {
        try {
          String tooLongName = "The name and lastname are too long for registration";
          new User(tooLongName, "address", "mail@mail.com", "cuil");

          failBecauseExceptionWasNotThrown(NameLengthOutOfBounds.class);
        } catch (NameLengthOutOfBounds e) {
          assertThat(e).hasMessage(NameLengthOutOfBounds.MESSAGE);
        }
      });

      it("cant create with invalid mail", () -> {
        try {
          String notAMail = "wrongemailadress";
          new User("name", "address", notAMail, "cuil");

          failBecauseExceptionWasNotThrown(EmailIsInvalid.class);
        } catch (EmailIsInvalid e) {
          assertThat(e).hasMessage("Email wrongemailadress is invalid");
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
