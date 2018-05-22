package ar.edu.unq.desapp.grupoi.model.errors.model;

public class FieldMissing extends RuntimeException {
  public FieldMissing(String propertyMissing) {
    super(
        String.format(
            "%s field is obligatory", propertyMissing)
    );
  }
}
