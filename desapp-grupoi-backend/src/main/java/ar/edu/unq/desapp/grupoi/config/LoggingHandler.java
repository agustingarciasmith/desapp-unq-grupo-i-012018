package ar.edu.unq.desapp.grupoi.config;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
public class LoggingHandler {

  private Logger log = LoggerFactory.getLogger("CARPNB-AHDIT");

  @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
  public void controller() {
  }

  @Pointcut("execution(* *.*(..))")
  protected void allMethod() {
  }

  @Before("controller() && allMethod()")
  public void logBefore(JoinPoint joinPoint) throws IOException {

    log.info("=====> BEFORE ENDPOINT EXCECUTION <=====");
    log.info("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
    log.info("Method :  " + joinPoint.getSignature().getName());
    log.info("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
    log.info("Target class : " + joinPoint.getTarget().getClass().getName());

    HttpServletRequest request = getRequest();

    log.info("Method Type : " + request.getMethod());
    Enumeration headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String headerName = headerNames.nextElement().toString();
      String headerValue = request.getHeader(headerName);
      log.info("Header Name: " + headerName + " Header Value : " + headerValue);
    }

//    Boolean isPost = "POST".equalsIgnoreCase(request.getMethod());
//    Boolean isPut = "PUT".equalsIgnoreCase(request.getMethod());
//    if (isPost || isPut) {
//      log.info(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
//    }
  }

  @AfterReturning(pointcut = "controller() && allMethod()", returning = "result")
  public void logAfter(JoinPoint joinPoint, Object result) {
    log.info("=====> AFTER ENDPOINT EXCECUTION <=====");
    String returnValue = this.getValue(result);
    log.info("Method Return value : " + returnValue);
  }

  @AfterThrowing(pointcut = "controller() && allMethod()", throwing = "exception")
  public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
    log.error("=====> ON ERROR ENDPOINT EXCECUTION <=====");
    log.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
    log.error("Message : " + exception.getMessage());
    log.error("Cause : " + exception.getCause());
  }

  @Around("controller() && allMethod()")
  public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
    long start = System.currentTimeMillis();
    try {
      String className = joinPoint.getSignature().getDeclaringTypeName();
      String methodName = joinPoint.getSignature().getName();
      Object result = joinPoint.proceed();
      long elapsedTime = System.currentTimeMillis() - start;
      log.info("Method " + className + "." + methodName + " ()" + " execution time : "
          + elapsedTime + " ms");

      return result;
    } catch (IllegalArgumentException e) {
      log.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
          + joinPoint.getSignature().getName() + "()");
      throw e;
    }
  }

  private String getValue(Object result) {
    String returnValue = null;
    if (null != result) {
      if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
        returnValue = ReflectionToStringBuilder.toString(result);
      } else {
        returnValue = result.toString();
      }
    }
    return returnValue;
  }

  private HttpServletRequest getRequest() {
    return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
        .getRequest();
  }
}
