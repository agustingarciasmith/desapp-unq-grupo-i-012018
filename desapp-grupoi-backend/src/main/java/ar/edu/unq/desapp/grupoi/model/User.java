package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.NameLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.model.ScoreOutOfBounds;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "CUIL", unique = true, nullable = false)
    private String cuil;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ADRESS", nullable = false)
    private String address;

    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    private ArrayList<Integer> score = new ArrayList<>();

    public User() {

    }

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

    public Reservation confirmReservationAsOwner(Reservation reservation) {
        reservation.confirm();
        return reservation;
    }

    public Reservation informReceptionAsClient(Reservation reservation) {
        reservation.vehicleReceivedByClient();
        return reservation;
    }

    public Reservation informDeliverAsOwner(Reservation reservation) {
        reservation.vehicleDeliveredByOwner();
        return reservation;
    }

    public Reservation informDeliverAsClientAndScore(Reservation reservation, Integer score) {
        reservation.vehicleDeliveredByClient();
        reservation.getOwner().addScore(score);
        return reservation;
    }

    public Reservation informReceptionAsOwnerAndScore(Reservation reservation, Integer score) {
        reservation.vehicleReceivedByOwner();
        reservation.getClient().addScore(score);
        return reservation;
    }

    public void addScore(Integer score) {
        if (1 > score || 5 < score) throw new ScoreOutOfBounds();
        this.score.add(score);
    }

    public Double getScore() {
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
}
