package ar.edu.unq.desapp.grupoi.model.errors;

import java.util.List;

public class CarpnbException extends RuntimeException {

  protected ErrorCode errorCode;
  protected List<String> errores;


  public CarpnbException(Throwable e) {
    this("", e);
  }

  public CarpnbException(String mensaje) {
    this(mensaje, null);
  }

  public CarpnbException() {
    super();
  }

  public CarpnbException(String mensaje, Throwable causa) {
    super(mensaje, causa);
    this.errorCode = ErrorCode.UNEXPECTER_ERROR;
  }

  public CarpnbException(String mensaje, ErrorCode errorCode, List<String> errores) {
    super(mensaje);
    this.errorCode = errorCode;
    this.errores = errores;
  }

  public CarpnbException(String mensaje, ErrorCode errorCode, List<String> errores, Throwable causa) {
    super(mensaje, causa);
    this.errorCode = errorCode;
    this.errores = errores;
  }

  public ErrorCode codigoError() {
    return errorCode;
  }

  public List<String> errores() {
    return errores;
  }

}
