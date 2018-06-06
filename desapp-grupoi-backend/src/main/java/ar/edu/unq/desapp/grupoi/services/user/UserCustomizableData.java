package ar.edu.unq.desapp.grupoi.services.user;

public class UserCustomizableData {
  private Long id;
  private String name;
  private String address;
  private String cuil;
  private String avatar;

  public UserCustomizableData(Long id, String name, String address, String cuil, String avatar) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.cuil = cuil;
    this.avatar = avatar;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getCuil() {
    return cuil;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public Long getId() {
    return id;
  }
}
