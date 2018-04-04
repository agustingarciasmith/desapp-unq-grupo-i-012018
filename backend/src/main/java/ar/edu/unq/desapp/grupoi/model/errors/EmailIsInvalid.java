package ar.edu.unq.desapp.grupoi.model.errors;

public class EmailIsInvalid extends RuntimeException {
  public EmailIsInvalid(String email) {
    super(
        String.format(
            "Email %s is invalid", email
            )
    );
  }
}
