package ar.edu.unq.desapp.grupoi.model.errors.model;

public class InvalidReservation extends RuntimeException {
    public static final String MESSAGE = "Cant apply for your own publication";

    public InvalidReservation() {
        super(
                String.format(MESSAGE)
        );
    }
}
