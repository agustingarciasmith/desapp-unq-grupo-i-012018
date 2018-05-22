package ar.edu.unq.desapp.grupoi.model.errors.model;

public class InvalidDate extends RuntimeException {
    public static final String MESSAGE = "Date is invalid";

    public InvalidDate() {
        super(
                String.format(MESSAGE)
        );
    }
}
