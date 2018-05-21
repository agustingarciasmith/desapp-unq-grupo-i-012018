package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PUBLICATIONS")
public class Publication {

    private static final String COST = "Cost";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column
    @ElementCollection(targetClass=LocalDate.class)
    private List<LocalDate> availableDates;

    @OneToOne
    private Vehicle vehicle;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "PICKUP_ADDRESS", nullable = false)
    private String pickUpAddress;

    @Column
    @ElementCollection(targetClass=String.class)
    private List<String> returnAddress;

    @Column(name = "PHONE", nullable = false)
    private String contactPhone;

    @Column(name = "COST_X_HOUR", nullable = false)
    private Integer cost;

    @OneToOne
    private User owner;

    public Publication(){}

    public Publication(User owner, Vehicle vehicle, String city, String pickUpAddress, ArrayList<String> returnAddress, String contactPhone, ArrayList<LocalDate> availableDates, Integer cost)
    {
        if(cost == null) throw new FieldMissing(COST);
        this.owner = owner;
        this.vehicle = vehicle;
        this.city = city;
        this.pickUpAddress = pickUpAddress;
        this.returnAddress = returnAddress;
        this.contactPhone = contactPhone;
        this.availableDates = availableDates;
        this.cost = cost;
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getCity() { return city; }
    public String getPickUpAddress() { return pickUpAddress; }
    public List<String> getReturnAddress() { return returnAddress; }
    public String getContactPhone() { return contactPhone; }
    public Integer getCost() { return cost; }
    public List<LocalDate> getAvailableDates() { return availableDates; }
    public User getOwner() { return owner; }
    public Long getId() {
        return id;
    }
}
