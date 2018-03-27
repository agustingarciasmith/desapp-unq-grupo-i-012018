package ar.edu.unq.desapp.grupoi.model;

public class User {
  private String cuil;
  private String name;
  private String address;
  private String email;

  public User(String name, String address, String email, String cuil) {

    if (name.length() < 4) throw new RuntimeException("Name is too short!");
    if (name.length() > 50) throw new RuntimeException("Name is too long!");
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
