package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.model.FieldMissing;

public class Publication {

    private static final String COST = "Cost";

    private AvailabilitySchedule schedule;
    private Vehicle vehicle;
    private String city;
    private String pickUpAddress;
    private String returnAddress;
    private String contactPhone;
    private Integer cost;
    private User owner;
    private Integer id;

    public Publication(Integer id, User owner, Vehicle vehicle, String city, String pickUpAddress, String returnAddress, String contactPhone, AvailabilitySchedule schedule, Integer cost)
    {
        if(cost == null) throw new FieldMissing(COST);
        this.owner = owner;
        this.vehicle = vehicle;
        this.city = city;
        this.pickUpAddress = pickUpAddress;
        this.returnAddress = returnAddress;
        this.contactPhone = contactPhone;
        this.schedule = schedule;
        this.cost = cost;
        this.id = id;
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCity() { return city; }
    public String getPickUpAddress() { return pickUpAddress; }
    public String getReturnAddress() { return returnAddress; }
    public String getContactPhone() { return contactPhone; }
    public Integer getCost() { return cost; }
    public AvailabilitySchedule getSchedule() { return schedule; }
    public User getOwner() { return owner; }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
