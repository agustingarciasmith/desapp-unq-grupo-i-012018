package ar.edu.unq.desapp.grupoi.model.errors.model;

public class ActionNotAllowed extends RuntimeException {
    public static final String MESSAGE = "Action not allowed at this stage";

    public ActionNotAllowed() {
      super(
              String.format(MESSAGE)
      );
    }
}
