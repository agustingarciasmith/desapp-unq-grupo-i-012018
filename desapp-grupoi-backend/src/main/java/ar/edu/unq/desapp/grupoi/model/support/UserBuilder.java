package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.User;

public class UserBuilder {
    private String cuil = "12345678901";
    private String name = "this is the name";
    private String address = "this is the address";
    private String email = "email@this.is";

    public UserBuilder withCuil(String cuil) {
        this.cuil = cuil;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public User build() { return new User(null, name, address, email, cuil); }
}
