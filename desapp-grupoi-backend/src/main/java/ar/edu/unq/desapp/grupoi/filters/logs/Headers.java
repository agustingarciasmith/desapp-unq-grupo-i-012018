package ar.edu.unq.desapp.grupoi.filters.logs;

import org.springframework.http.HttpHeaders;

public interface Headers {
  String HEADER_CANAL =  "X-Mimo-Canal";
  String HEADER_PAIS = "X-Mimo-Pais";
  String HEADER_IP_ORIGEN = "X-Mimo-IpOrigen";
  String HEADER_USER_AGENT = HttpHeaders.USER_AGENT;
}
