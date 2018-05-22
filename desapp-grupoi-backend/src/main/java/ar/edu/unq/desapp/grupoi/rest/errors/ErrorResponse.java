package ar.edu.unq.desapp.grupoi.rest.errors;

import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {

  private ErrorCode errorCode;
  private String message;
  private String simpleName;
  private List<String> errores;

  public ErrorResponse() {
  }

  public ErrorResponse(ErrorCode errorCode, String message, String simpleName) {
    this.errorCode = errorCode;
    this.message = message;
    this.simpleName = simpleName;
    this.errores = Collections.emptyList();
  }

  public ErrorResponse(ErrorCode errorCode, List<String> errores, String message, String simpleName) {
    this.errorCode = errorCode;
    this.errores = errores;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public List<String> getErrores() {
    return errores;
  }

  public String getMessage() {
    return message;
  }

  public String getSimpleName() {
    return simpleName;
  }
}
