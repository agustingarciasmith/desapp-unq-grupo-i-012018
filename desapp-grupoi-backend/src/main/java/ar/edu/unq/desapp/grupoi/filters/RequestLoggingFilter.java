package ar.edu.unq.desapp.grupoi.filters;

import ar.edu.unq.desapp.grupoi.filters.logs.RequestWrapper;
import ar.edu.unq.desapp.grupoi.filters.logs.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger("APP_MESSAGES");

  private static final String REQUEST_PREFIX = "Request: ";
  private static final String RESPONSE_PREFIX = "Response: ";
  private static final String MDC_KEY = "CAPNB";

  private AtomicLong id = new AtomicLong(1);

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    final FilterChain filterChain) throws ServletException, IOException {

    long requestId = id.incrementAndGet();
    MDC.put(MDC_KEY, String.format("[#%d]", requestId));
    request = new RequestWrapper(requestId, request);
    response = new ResponseWrapper(requestId, response);

    try {
      logRequest(request);
      filterChain.doFilter(request, response);
    } finally {
      logResponse((ResponseWrapper) response);
      MDC.remove(MDC_KEY);
    }

  }

  private void logRequest(final HttpServletRequest request) {
    StringBuilder msg = new StringBuilder();

    msg.append(REQUEST_PREFIX);
    HttpSession session = request.getSession(false);

    if (request.getMethod() != null) {
      msg.append(request.getMethod()).append(" ");
    }

    msg.append(request.getRequestURI());

    if (request.getQueryString() != null && request.getQueryString().trim().length() > 0) {
      msg.append('?').append(request.getQueryString());
    }
    msg.append(' ');

    if (session != null) {
      msg.append("session=").append(session.getId()).append(" ");
    }

    if (request.getContentType() != null) {
      msg.append("contenttype=").append(request.getContentType()).append(" ");
    }

    String auth = request.getHeader("Authorization");
    if (auth != null && !auth.isEmpty()) {
      msg.append("Auth:").append(auth).append(" ");
    }
    logger.info(msg.toString());
  }

  private void logResponse(final ResponseWrapper response) {
    StringBuilder msg = new StringBuilder();
    msg.append(RESPONSE_PREFIX);
    msg.append(response.getStatus()).append(' ');
    logger.info(msg.toString());

    if (response.hasError()) {
      StringBuilder body = new StringBuilder();
      try {
        body.append("body=").append(new String(response.toByteArray(), response.getCharacterEncoding()));
      } catch (UnsupportedEncodingException e) {
        logger.warn("Failed to parse response payload", e);
      }
      logger.debug(body.toString());
    }

  }


}