package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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

  @Column(name = "TOTAL_SCORE", nullable = false, columnDefinition = "int default 0")
  private Integer totalScore;

  @Column(name = "VOTES_NUMBER")
  private Integer votesNumber;

  public User() {
  }

  public User(Long id, String name, String address, String email, String cuil, String avatar) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.email = email;
    this.cuil = cuil;
    this.avatar = avatar;
    this.totalScore = 0;
    this.votesNumber = 0;
  }

  @Override
  public String toString() {
    return this.id.toString();
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

  public Integer getScore() {
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

  public void setTestingScore(){
    this.votesNumber = 1;
    this.totalScore = ThreadLocalRandom.current().nextInt(1, 5 + 1);
  }

  public void reciveScore(Integer score) {
      this.totalScore += score;
      this.votesNumber ++;
  }
}
