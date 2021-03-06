package ar.edu.unq.desapp.grupoi.model.errors.model;

public class InvalidMail extends RuntimeException {
    public InvalidMail(String email) {
        super(
                String.format(
                        "Email %s is invalid", email
                )
        );
    }
}
