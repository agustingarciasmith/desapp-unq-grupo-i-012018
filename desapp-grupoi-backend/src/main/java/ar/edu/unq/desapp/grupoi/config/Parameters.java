package ar.edu.unq.desapp.grupoi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.parameters")
public class Parameters {

}
