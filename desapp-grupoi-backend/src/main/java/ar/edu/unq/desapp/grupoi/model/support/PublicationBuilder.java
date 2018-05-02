package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.AvailabilitySchedule;
import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;

public class PublicationBuilder {
    private User owner = new UserBuilder().build();
    private Vehicle vehicle = new VehicleBuilder().buildVehicle();
    private String city = "Buenos Aires";
    private String pickUpAdress = "pick up address";
    private String returnAdress = "return address";
    private String contactPhone = "123456789";
    private AvailabilitySchedule schedule = new AvailabilitySchedule();
    private Integer cost = 400;
    private int id = 1;

    public PublicationBuilder withId(int id) {
        this.id = id;
        return this;
    }

    public PublicationBuilder withVehicule(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public PublicationBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public PublicationBuilder withPickUpAdress(String pickUpAdress) {
        this.pickUpAdress = pickUpAdress;
        return this;
    }

    public PublicationBuilder withReturnAdress(String returnAdress) {
        this.returnAdress = returnAdress;
        return this;
    }

    public PublicationBuilder withContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
        return this;
    }

    public PublicationBuilder withCost(Integer cost) {
        this.cost = cost;
        return this;
    }

    public PublicationBuilder withSchedule(AvailabilitySchedule schedule) {
        this.schedule = schedule;
        return this;
    }

    public PublicationBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public Publication build() {
        return new Publication(id, owner, vehicle, city, pickUpAdress, returnAdress, contactPhone, schedule, cost);
    }
}
