package ar.edu.unq.desapp.grupoi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.parameters")
public class Parameters {

  private Integer maxUserNameLength;
  private Integer minUserNameLength;
  private Integer minVehicleDescriptionLength;
  private Integer maxVehicleDescriptionLength;

  public Integer getMaxUserNameLength() {
    return this.maxUserNameLength;
  }

  public Integer getMinUserNameLength() {
    return this.minUserNameLength;
  }

  public void setMaxUserNameLength(Integer maxUserNameLenght) {
    this.maxUserNameLength = maxUserNameLenght;
  }

  public void setMinUserNameLength(Integer minUserNameLenght) {
    this.minUserNameLength = minUserNameLenght;
  }

  public Integer getMinVehicleDescriptionLength() {
    return this.minVehicleDescriptionLength;
  }

  public void setMinVehicleDescriptionLength(Integer minVehicleDescriptionLength) {
    this.minVehicleDescriptionLength = minVehicleDescriptionLength;
  }

  public Integer getMaxVehicleDescriptionLength() {
    return this.maxVehicleDescriptionLength;
  }

  public void setMaxVehicleDescriptionLength(Integer maxVehicleDescriptionLength) {
    this.maxVehicleDescriptionLength = maxVehicleDescriptionLength;
  }
}
