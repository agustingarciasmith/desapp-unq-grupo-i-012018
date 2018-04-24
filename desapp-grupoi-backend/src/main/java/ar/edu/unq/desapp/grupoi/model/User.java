package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.NameLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.ScoreOutOfBounds;

import java.util.ArrayList;

public class User {

  private String cuil;
  private String name;
  private String address;
  private String email;
  private ArrayList<Integer> score = new ArrayList<>();

  public User(String name, String address, String email, String cuil) {
    if (name.length() < 4 || name.length() > 50) throw new NameLengthOutOfBounds();
    EmailFormatValidator.runValidation(email);

    this.name = name;
    this.address = address;
    this.email = email;
    this.cuil = cuil;
  }

  public Publication createPublication(Publication publication) {
    return publication;
  }

  public Reservation makeReservationAsClient(Publication publication) {
    return new Reservation(publication, this);
  }

  public Reservation confirmReservationAsOwner(Reservation reservation){
    reservation.confirm();
    return reservation;
  }

  public Reservation informReceptionAsClient(Reservation reservation){
    reservation.vehicleReceivedByClient();
    return reservation;
  }

  public Reservation informDeliverAsOwner(Reservation reservation){
    reservation.vehicleDeliveredByOwner();
    return reservation;
  }

  public Reservation informDeliverAsClientAndScore(Reservation reservation, Integer score){
    reservation.vehicleDeliveredByClient();
    reservation.getOwner().addScore(score);
    return reservation;
  }

  public Reservation informReceptionAsOwnerAndScore(Reservation reservation, Integer score){
    reservation.vehicleReceivedByOwner();
    reservation.getClient().addScore(score);
    return reservation;
  }

  public void addScore(Integer score) {
    if (1 > score || 5 < score) throw new ScoreOutOfBounds();
    this.score.add(score);
  }

  public Double getScore(){
    Double score = null;
    if (this.score.size() > 0) {
      score = (double) this.score.stream().mapToInt(Integer::intValue).sum() / this.score.size();
    }
    return score;
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
