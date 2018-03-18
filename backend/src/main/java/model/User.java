package model;

public class User {
    String cuil;
    String name;
    String address;
    String email;
    EmailFormatValidator emailFormatValidator = new EmailFormatValidator();

    public User() {
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

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public void setName(String name) {
        if (name.length() < 4) throw new RuntimeException("Name is too short!");
        if (name.length() > 50) throw new RuntimeException("Name is too long!");
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        if (!emailFormatValidator.validate(email)) throw new RuntimeException("Email address is invalid!");
        this.email = email;
    }
}
