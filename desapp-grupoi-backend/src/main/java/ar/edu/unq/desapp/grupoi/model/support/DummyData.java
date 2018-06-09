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
            withType(VehicleType.PICKUP).
            buildVehicle();

    public static Vehicle kawasaki2018 = vehicleBuilder.
            withDescription("Kawasaki Ninja 2018, red, full tank, with helmets for driver and partner").
            withLicense("222FGS").
            withPassengers(2).
            withType(VehicleType.MOTORBIKE).
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

}
