package ar.edu.unq.desapp.grupoi.model;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class CuilValidatorTests {

  private static CuilValidator cuilValidator;
  private String cuil;
  private Boolean expectedValidation;

  public CuilValidatorTests(String cuil, Boolean expectedValidation) {
    this.cuil = cuil;
    this.expectedValidation = expectedValidation;
  }

  @BeforeClass
  public static void initialize() {
    cuilValidator = new CuilValidator();
  }

  @Parameters
  public static Collection<Object[]> emailPatternsToTest() {
    Object[][] emailPatternsToTest = new Object[][]{
      {"10123456781", false},
      {"10_12345678_10", false},
      {"10.12345678.1", false},
      {"ab-12345678-1", false},                       // must contain a @ character and a tld
      {"10-12345678-a", false},                       // must contain a @ character and a tld
      {"10-qwertyui-1", false},                       // must contain a @ character and a tld
      {"sadfghhfdfsdfgddfgsfs", false},                       // must contain a @ character and a tld
      {"asda", false},                       // must contain a @ character and a tld
      {"", false},                       // must contain a @ character and a tld
      {"101-12345678-1", false},                       // must contain a @ character and a tld
      {"10-12345678-112", false},                       // must contain a @ character and a tld
      {"10-12345678234-1", false},                       // must contain a @ character and a tld
      {"1-12345678234-1", false},                       // must contain a @ character and a tld
      {"10-12345678", false},                       // must contain a @ character and a tld
      {"10-12345678-", false},                       // must contain a @ character and a tld
      {"10-1234567-1", false},                       // must contain a @ character and a tld
      {"10-13245678-1", true}
    };

    return Arrays.asList(emailPatternsToTest);
  }

  @Test
  public void testValidatingCuil() {
    assertThat(cuilValidator.validate(this.cuil)).isEqualTo(this.expectedValidation);
  }

}