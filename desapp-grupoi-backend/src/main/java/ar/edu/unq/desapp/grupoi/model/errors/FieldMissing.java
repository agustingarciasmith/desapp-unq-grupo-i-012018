package ar.edu.unq.desapp.grupoi.model.errors;

public class FieldMissing extends RuntimeException {
  public FieldMissing(String propertyMissing) {
    super(
        String.format(
            "%s field is obligatory", propertyMissing)
    );
  }
}
