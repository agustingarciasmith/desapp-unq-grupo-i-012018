package ar.edu.unq.desapp.grupoi.rest.errors;

import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.Collections;
import java.util.List;

import static ar.edu.unq.desapp.grupoi.model.errors.ErrorCode.UNEXPECTER_ERROR;
import static ar.edu.unq.desapp.grupoi.model.errors.ErrorCode.INVALID_REQUEST;


@ControllerAdvice
public class ErrorHandler {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ErrorResponse unauthorized(AccessDeniedException e) {
    return createError(ErrorCode.UNAUTHORIZED, Collections.singletonList(e.getMessage()), e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse invalidRequest(InvalidRequestException e) {
    return createError(e.codigoError(), e.errores(), e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse methodNotSupported(HttpRequestMethodNotSupportedException e) {
    return createError(INVALID_REQUEST, e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse mediaTypeError(HttpMediaTypeException e) {
    return createError(INVALID_REQUEST, e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse mesaggeParseError(HttpMessageConversionException e) {
    return createError(INVALID_REQUEST, e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse multipartError(MultipartException e) {
    return createError(INVALID_REQUEST, e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ErrorResponse multipartMissing(MissingServletRequestPartException e) {
    return createError(INVALID_REQUEST, e);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ErrorResponse unexpectedError(Throwable e) {
    logger.error("Error inesperado procesando request REST", e);
    return createError(UNEXPECTER_ERROR, e);
  }

  private ErrorResponse createError(ErrorCode codigo, List<String> errores, Throwable e) {
    return new ErrorResponse(codigo, errores, e.getMessage(), e.getClass().getSimpleName());
  }

  private ErrorResponse createError(ErrorCode codigo, Throwable e) {
    return new ErrorResponse(codigo, e.getMessage(), e.getClass().getSimpleName());
  }

}
