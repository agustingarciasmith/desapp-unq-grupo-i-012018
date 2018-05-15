package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.InvalidCuil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CuilValidator {

  public static final String EMAIL_ADDRESS_IS_INVALID = "Email address is invalid!";
  private Pattern pattern;
  private Matcher matcher;

  private static final String CUIL_PATTERN = "^\\d{2}\\-\\d{8}\\-\\d{1}$";

  public static void runValidation(String cuil) {
    new CuilValidator().validate(cuil);
  }

  public CuilValidator() {
    pattern = Pattern.compile(CUIL_PATTERN);
  }

  public void validate(final String cuil) {
    matcher = pattern.matcher(cuil);
    if (!matcher.matches()) {
      throw new InvalidCuil(cuil);
    }
  }
}
