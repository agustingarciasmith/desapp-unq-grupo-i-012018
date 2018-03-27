package ar.edu.unq.desapp.grupoi.model;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class TestUser {

  @Test
  public void testNewSystemUserIsRegisteredWithItsObligatoryProperties() {
    User newUser = new User(
        "carlos perez",
        "Viamonte 2506",
        "lalopsb@hotmail.com",
        "20111111112");

    assertThat(newUser.getCuil()).isEqualTo("20111111112");
    assertThat(newUser.getName()).isEqualTo("carlos perez");
    assertThat(newUser.getAddress()).isEqualTo("Viamonte 2506");
    assertThat(newUser.getEmail()).isEqualTo("lalopsb@hotmail.com");
  }

  @Test
  public void testAUserCantBeCreatedWithNameShorterThan4letters() {
    try {
      String toShortName = "nan";
      new User(toShortName, "address", "mail@mail.com", "cuil");

      failBecauseExceptionWasNotThrown(RuntimeException.class);
    } catch (RuntimeException e) {
      assertThat(e).hasMessage("Name is too short!");
    }
  }

  @Test
  public void testAUserCantBeCreatedWithNameLongerThan50letters() {
    try {
      String toLongName = "The name and lastname are too long for registration";
      new User(toLongName, "address", "mail@mail.com", "cuil");

      failBecauseExceptionWasNotThrown(RuntimeException.class);
    } catch (RuntimeException e) {
      assertThat(e).hasMessage("Name is too long!");
    }
  }

  @Test
  public void testAUserCantBeCreatedWithAnInvalidEmail() {
    try {
      String notAMail = "wrongemailadress";
      new User("name", "address", notAMail, "cuil");

      failBecauseExceptionWasNotThrown(RuntimeException.class);
    } catch (RuntimeException e) {
      assertThat(e).hasMessage("Email address is invalid!");
    }
  }
}
