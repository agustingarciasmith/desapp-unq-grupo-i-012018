package ar.edu.unq.desapp.grupoi.model.errors.model;

public class ScoreOutOfBounds extends RuntimeException {
    public static final String MESSAGE = "Score should be from 1 to 5";

    public ScoreOutOfBounds() {
        super(
                String.format(MESSAGE)
        );
    }
}
