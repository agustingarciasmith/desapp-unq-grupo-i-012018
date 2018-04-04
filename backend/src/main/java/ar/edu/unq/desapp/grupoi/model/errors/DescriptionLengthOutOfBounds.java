package ar.edu.unq.desapp.grupoi.model.errors;

public class DescriptionLengthOutOfBounds extends RuntimeException {
  public static final String MESSAGE = "Description should have between 30 and 200 characters";

  public DescriptionLengthOutOfBounds() {
    super(
        String.format(MESSAGE)
    );
  }
}
