package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;

  @Column(name = "CUIL", unique = true)
  private String cuil;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "ADRESS")
  private String address;

  @Column(name = "EMAIL", unique = true, nullable = false)
  private String email;

  @Column(name = "AVATAR")
  private String avatar;

  @OneToMany
  @JoinColumn(name = "VEHICLE_ID")
  private List<Vehicle> vehicles;

  @Column(name = "TOTAL_SCORE", nullable = false, columnDefinition = "int default 0")
  private Integer totalScore;

  @Column(name = "VOTES_NUMBER")
  private Integer votesNumber;

  public User() {
    this.vehicles = new ArrayList<>();
  }

  public User(Long id, String name, String address, String email, String cuil, String avatar) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.email = email;
    this.cuil = cuil;
    this.avatar = avatar;
    this.vehicles = new ArrayList<>();
    this.totalScore = 0;
    this.votesNumber = 0;
  }

  public void addVehicle(Vehicle vehicle) {
    this.vehicles.add(vehicle);
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

  public Long getId() {
    return id;
  }

  public String getAvatar() {
    return avatar;
  }

  public List<Vehicle> getVehicles() {
    return vehicles;
  }

  public Integer getTotalScore() {
    if (votesNumber > 0) {
      return this.totalScore / this.votesNumber;
    } else {
      return 0;
    }
  }

  public void updateFrom(UserCustomizableData userData) {
    this.name = userData.getName();
    this.cuil = userData.getCuil();
    this.address = userData.getAddress();
    this.avatar = userData.getAvatar();
  }

  public void removeVehicle(Vehicle vehicle) {
    this.vehicles.remove(vehicle);
  }
}
