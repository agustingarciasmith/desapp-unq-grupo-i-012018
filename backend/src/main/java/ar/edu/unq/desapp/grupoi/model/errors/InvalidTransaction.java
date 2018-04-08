package ar.edu.unq.desapp.grupoi.model.errors;

public class InvalidTransaction extends RuntimeException {
    public static final String MESSAGE = "Cant apply for your own publication";

    public InvalidTransaction() {
        super(
                String.format(MESSAGE)
        );
    }
}
