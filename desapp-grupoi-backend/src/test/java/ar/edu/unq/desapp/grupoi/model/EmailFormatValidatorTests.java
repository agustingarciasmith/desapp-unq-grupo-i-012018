package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.InvalidMail;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

@RunWith(Parameterized.class)
public class EmailFormatValidatorTests {

  private String email;
  private static EmailFormatValidator emailFormatValidator;
  private Boolean expectedValidation;

  public EmailFormatValidatorTests(String email, Boolean expectedValidation) {
    this.email = email;
    this.expectedValidation = expectedValidation;
  }

  @BeforeClass
  public static void initialize() {
    emailFormatValidator = new EmailFormatValidator();
  }

  @Parameters
  public static Collection<Object[]> emailPatternsToTest() {
    Object[][] emailPatternsToTest = new Object[][]{
        {"newuser@gmail.com.2u", false},               // number in the second level
        {"newuser@user@carpnd.com", false},           // @ twice in the address
        {"newuser!!!@carpnd.com", false},             // special character '!' in the address
        {"newuser@.com", false},                      // tld cannot start with a dot
        {"newuser.com", false},                       // must contain a @ character and a tld
        {".newuser.com@carpnd.com", false},           // the address cannot start with a dot
        {"newuser..newuser@carpnd.com", false},       // you cannot have double dots in your address
        {"newuser@gmail.com", true},
        {"new+user@gmail.com", true},
        {"new.user-900@gmail-list.com", true},
        {"newuser123@carpnd.com.gr", true}};

    return Arrays.asList(emailPatternsToTest);
  }

  @Test
  public void testValidatingEmail() {
    try {
      emailFormatValidator.validate(this.email);

      if (!this.expectedValidation) {
        failBecauseExceptionWasNotThrown(InvalidMail.class);
      }
    } catch (InvalidMail e){
      assertThat(e).hasMessage("Email %s is invalid", this.email);
    }
  }

}