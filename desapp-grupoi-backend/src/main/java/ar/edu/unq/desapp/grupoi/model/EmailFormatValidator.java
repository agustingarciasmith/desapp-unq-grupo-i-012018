package ar.edu.unq.desapp.grupoi.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatValidator {

  public static final String EMAIL_ADDRESS_IS_INVALID = "Email address is invalid!";
  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  private Pattern pattern;
  private Matcher matcher;

  public EmailFormatValidator() {
    pattern = Pattern.compile(EMAIL_PATTERN);
  }

  public static Boolean runValidation(String email) {
    return new EmailFormatValidator().validate(email);
  }

  public Boolean validate(final String email) {
    matcher = pattern.matcher(email);
    return matcher.matches();
  }
}
