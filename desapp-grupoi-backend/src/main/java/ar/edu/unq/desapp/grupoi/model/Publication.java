package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;

public class Publication {

    private static final String COST = "Cost";

    private AvailabilitySchedule schedule;
    private Vehicle vehicle;
    private String city;
    private String pickUpAdress;
    private String returnAdress;
    private String contactPhone;
    private Integer cost;
    private User owner;

    public Publication(User owner, Vehicle vehicle, String city, String pickUpAdress, String returnAdress, String contactPhone, AvailabilitySchedule schedule, Integer cost)
    {
        if(cost == null) throw new FieldMissing(COST);
        this.owner = owner;
        this.vehicle = vehicle;
        this.city = city;
        this.pickUpAdress = pickUpAdress;
        this.returnAdress = returnAdress;
        this.contactPhone = contactPhone;
        this.schedule = schedule;
        this.cost = cost;
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCity() { return city; }
    public String getPickUpAdress() { return pickUpAdress; }
    public String getReturnAdress() { return returnAdress; }
    public String getContactPhone() { return contactPhone; }
    public Integer getCost() { return cost; }
    public AvailabilitySchedule getSchedule() { return schedule; }
    public User getOwner() { return owner; }
}
