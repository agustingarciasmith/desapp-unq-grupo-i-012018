package ar.edu.unq.desapp.grupoi.model.errors;

public class InvalidDate extends RuntimeException {
    public static final String MESSAGE = "Date is invalid";

    public InvalidDate() {
        super(
                String.format(MESSAGE)
        );
    }
}
