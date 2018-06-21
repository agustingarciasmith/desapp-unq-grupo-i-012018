package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.VehicleType;

public class VehicleBuilder {
    private Long id = Long.valueOf(1);
    private VehicleType type = VehicleType.AUTO;
    private Integer passengers = 4;
    private String description = "This is an appropriate description";
    private String license = "AAA111";
    private User user;

    public VehicleBuilder withType(VehicleType type) {
        this.type = type;
        return this;
    }

    public VehicleBuilder withPassengers(Integer passengers) {
        this.passengers = passengers;
        return this;
    }

    public VehicleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public VehicleBuilder withLicense(String license) {
        this.license = license;
        return this;
    }

    public VehicleBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public Vehicle buildVehicle() { return new Vehicle(type, passengers, description, license, user); }
}
