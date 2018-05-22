package ar.edu.unq.desapp.grupoi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.parameters")
public class Parameters {

  private Integer maxUserNameLenght;
  private Integer minUserNameLenght;

  public Integer getMaxUserNameLenght() {
    return this.maxUserNameLenght;
  }

  public Integer getMinUserNameLenght() {
    return this.minUserNameLenght;
  }

  public void setMaxUserNameLenght(Integer maxUserNameLenght) {
    this.maxUserNameLenght = maxUserNameLenght;
  }

  public void setMinUserNameLenght(Integer minUserNameLenght) {
    this.minUserNameLenght = minUserNameLenght;
  }
}
