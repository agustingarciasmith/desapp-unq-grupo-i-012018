package ar.edu.unq.desapp.grupoi.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatValidator {

  private Pattern pattern;
  private Matcher matcher;

  private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
      + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

  public static void runValidation(String email) {
    new EmailFormatValidator().validate(email);
  }

  public EmailFormatValidator() {
    pattern = Pattern.compile(EMAIL_PATTERN);
  }

  public void validate(final String email) {
    matcher = pattern.matcher(email);
    if (!matcher.matches()) {
      throw new RuntimeException("Email address is invalid!");
    }
  }
}
