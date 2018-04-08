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

  public Publication createPublication(Publication publication) {
    return publication;
  }

  public Reservation makeReservation(Publication publication) {
    return new Reservation(publication, this);
  }

  public void confirmReservation(Reservation reservation) {
    reservation.confirm();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    User user = (User) o;

    return cuil != null ? cuil.equals(user.cuil) : user.cuil == null;
  }

  @Override
  public int hashCode() {
    return cuil != null ? cuil.hashCode() : 0;
  }
}
