package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PublicationBuilder {
    private User owner = new UserBuilder().build();
    private Vehicle vehicle = new VehicleBuilder().buildVehicle();
    private String city = "Buenos Aires";
    private String pickUpAdress = "Quilmes 398";
    private ArrayList<String> returnAddress = this.returnAddress();

    private ArrayList<String> returnAddress() {
        ArrayList<String> returnAddress = new ArrayList<>();
        returnAddress.add("Azcuenaga 2344");
        return returnAddress;
    }

    private String contactPhone = "123456789";
    private ArrayList<LocalDate> availableDates = this.exampleDates();

    private ArrayList<LocalDate> exampleDates() {
        ArrayList<LocalDate> exampleDates = new ArrayList<>();
        exampleDates.add(LocalDate.parse("2018-05-18"));
        exampleDates.add(LocalDate.parse("2018-05-17"));
        exampleDates.add(LocalDate.parse("2018-05-15"));
        return exampleDates;
    }

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

    public PublicationBuilder withReturnAdress(ArrayList<String> returnAdress) {
        this.returnAddress = returnAdress;
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

    public PublicationBuilder withOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public PublicationBuilder withDates(ArrayList<LocalDate> dates){
        this.availableDates = dates;
        return this;
    }

    public Publication build() {
        return new Publication(owner, vehicle, city, pickUpAdress, returnAddress, contactPhone, availableDates, cost);
    }
}
