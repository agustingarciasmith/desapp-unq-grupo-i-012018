package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.*;

import java.util.ArrayList;

public class DummyData {
    private static UserBuilder userBuilder = new UserBuilder();
    private static VehicleBuilder vehicleBuilder = new VehicleBuilder();
    private static PublicationBuilder publicationBuilder = new PublicationBuilder();

    public DummyData(){}

    public static Vehicle gol2016 = vehicleBuilder.
            withDescription("VW Gol 2016 in great condition, green with airbags").
            withLicense("OPP122").
            withPassengers(4).
            withType(VehicleType.AUTO).
            buildVehicle();

    public static Vehicle punto2017 = vehicleBuilder.
            withDescription("Fiat Punto 2017, white, available for travel outside the city").
            withLicense("QAT835").
            withPassengers(4).
            withType(VehicleType.AUTO).
            buildVehicle();

    public static Vehicle ranger2014 = vehicleBuilder.
            withDescription("Ford Ranger 2014, 4x4, black, with leather seats").
            withLicense("NUY340").
            withPassengers(2).
            withType(VehicleType.CAMIONETA).
            buildVehicle();

    public static Vehicle kawasaki2018 = vehicleBuilder.
            withDescription("Kawasaki Ninja 2018, red, full tank, with helmets for driver and partner").
            withLicense("222FGS").
            withPassengers(2).
            withType(VehicleType.MOTOCICLETA).
            buildVehicle();

    public static User jorgeLopez = userBuilder.
            withCuil("20202002002").
            withName("Jorge Lopez").
            withEmail("jorge@gmail.com").
            withAddress("Galicia 333").
            build();

    public static User marisaGomez = userBuilder.
            withCuil("27202002002").
            withName("Marisa Gomez").
            withEmail("marisa@gmail.com").
            withAddress("Junin 1288").
            build();

    public static Publication publicationOne = publicationBuilder.
            withId(0).
            withCost(300).
            withOwner(jorgeLopez).
            withCity("Buenos Aires").
            withContactPhone("4444444").
            withPickUpAdress("Azcuenaga 600").
            withReturnAdress("Av. Alem 1400").
            withSchedule(new AvailabilitySchedule()).withVehicule(gol2016).
            build();

    public static Publication publicationTwo = publicationBuilder.
            withId(1).
            withCost(500).
            withOwner(marisaGomez).
            withCity("Buenos Aires").
            withContactPhone("4556677").
            withPickUpAdress("Paraguay 2500").
            withReturnAdress("Av. Cordoba 1400").
            withSchedule(new AvailabilitySchedule()).withVehicule(ranger2014).
            build();

    public static ArrayList<Publication> setOfPublications() {
        ArrayList<Publication> publications = new ArrayList<>();
        publications.add(publicationOne);
        publications.add(publicationTwo);
        return publications;
    }
}
