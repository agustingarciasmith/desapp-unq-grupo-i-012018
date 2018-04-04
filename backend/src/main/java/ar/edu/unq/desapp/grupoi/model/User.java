package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.NameLengthOutOfBounds;

public class User {

  private String cuil;
  private String name;
  private String address;
  private String email;

  public User(String name, String address, String email, String cuil) {

    if (name.length() < 4 || name.length() > 50) throw new NameLengthOutOfBounds();
    EmailFormatValidator.runValidation(email);

    this.name = name;
    this.address = address;
    this.email = email;
    this.cuil = cuil;
  }

  public String getCuil() {
    return cuil;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }
}
