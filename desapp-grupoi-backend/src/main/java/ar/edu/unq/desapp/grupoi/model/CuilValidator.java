package ar.edu.unq.desapp.grupoi.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CuilValidator {

  public static final String EMAIL_ADDRESS_IS_INVALID = "Email address is invalid!";
  private static final String CUIL_PATTERN = "^\\d{2}\\-\\d{8}\\-\\d{1}$";
  private Pattern pattern;
  private Matcher matcher;

  public CuilValidator() {
    pattern = Pattern.compile(CUIL_PATTERN);
  }

  public static Boolean runValidation(String cuil) {
    return new CuilValidator().validate(cuil);
  }

  public Boolean validate(final String cuil) {
    matcher = pattern.matcher(cuil);
    return matcher.matches();
  }
}
