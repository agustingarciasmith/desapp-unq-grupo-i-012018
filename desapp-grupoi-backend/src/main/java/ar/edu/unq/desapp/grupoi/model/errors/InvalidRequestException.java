package ar.edu.unq.desapp.grupoi.model.errors;


import java.util.List;

public class InvalidRequestException extends CarpnbException {

    public InvalidRequestException(String mensaje, List<String> errores) {
        super(mensaje, ErrorCode.INVALID_REQUEST, errores);
    }

    public InvalidRequestException(String mensaje, List<String> errores, Throwable causa) {
        super(mensaje, ErrorCode.INVALID_REQUEST, errores, causa);
    }
}
