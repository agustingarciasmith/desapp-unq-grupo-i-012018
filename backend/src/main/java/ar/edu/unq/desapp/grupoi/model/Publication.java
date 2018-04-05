package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;

public class Publication {

    private static final String COST = "Cost";
    private Vehicle vehicle;
    private String city;
    private String pickUpAdress;
    private String returnAdress;
    private String contactPhone;
    private String hoursDays;
    private Integer cost;

    public Publication(Vehicle vehicle, String city, String pickUpAdress, String returnAdress, String contactPhone, String hoursDays, Integer cost) 
    {
        if(cost == null) throw new FieldMissing(COST);
        this.vehicle = vehicle;
        this.city = city;
        this.pickUpAdress = pickUpAdress;
        this.returnAdress = returnAdress;
        this.contactPhone = contactPhone;
        this.hoursDays = hoursDays;
        this.cost = cost;
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCity() { return city; }
    public String getPickUpAdress() { return pickUpAdress; }
    public String getReturnAdress() { return returnAdress; }
    public String getContactPhone() { return contactPhone; }
    public String getHoursDays() { return hoursDays; }
    public Integer getCost() { return cost; }
}
