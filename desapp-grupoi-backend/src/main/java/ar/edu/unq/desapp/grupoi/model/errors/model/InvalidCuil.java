package ar.edu.unq.desapp.grupoi.model.errors.model;

public class InvalidCuil extends RuntimeException {
    public InvalidCuil(String cuil) {
        super(String.format("El cuil %s no es valido", cuil));
    }
}
