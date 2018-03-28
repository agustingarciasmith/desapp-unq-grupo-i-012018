package ar.edu.unq.desapp.grupoi.model;

public class User {
  public static final String NAME_IS_TOO_SHORT = "Name is too short!";
  public static final String NAME_IS_TOO_LONG = "Name is too long!";

  private String cuil;
  private String name;
  private String address;
  private String email;

  public User(String name, String address, String email, String cuil) {

    if (name.length() < 4) throw new RuntimeException(NAME_IS_TOO_SHORT);
    if (name.length() > 50) throw new RuntimeException(NAME_IS_TOO_LONG);
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
