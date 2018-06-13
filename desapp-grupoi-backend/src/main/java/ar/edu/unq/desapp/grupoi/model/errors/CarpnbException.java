package ar.edu.unq.desapp.grupoi.model.errors;

import java.util.List;

public class CarpnbException extends RuntimeException {

  protected ErrorCode errorCode;
  protected List<String> errors;


  public CarpnbException(Throwable e) {
    this("", e);
  }

  public CarpnbException(String mensaje) {
    this(mensaje, null);
  }

  public CarpnbException() {
    super();
  }

  public CarpnbException(String mensaje, Throwable cause) {
    super(mensaje, cause);
    this.errorCode = ErrorCode.UNEXPECTER_ERROR;
  }

  public CarpnbException(String mensaje, ErrorCode errorCode, List<String> errors) {
    super(mensaje);
    this.errorCode = errorCode;
    this.errors = errors;
  }

  public CarpnbException(String mensaje, ErrorCode errorCode, List<String> errors, Throwable causa) {
    super(mensaje, causa);
    this.errorCode = errorCode;
    this.errors = errors;
  }

  public ErrorCode errorCode() {
    return errorCode;
  }

  public List<String> errors() {
    return errors;
  }

}
