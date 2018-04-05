package ar.edu.unq.desapp.grupoi.model.errors;

public class NameLengthOutOfBounds extends RuntimeException {
  public static final String MESSAGE = "Name should have between 4 and 50 characters";

  public NameLengthOutOfBounds() {
    super(
        String.format(MESSAGE)
    );
  }
}
