package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationBuilder {
    private UserBuilder userBuilder = new UserBuilder();
    private User client = userBuilder.withCuil("1111111111").build();
    private User owner = userBuilder.withCuil("2222222222").build();
    private Publication publication = new PublicationBuilder().withOwner(owner).build();
    private ArrayList<LocalDate> selectedDates = (ArrayList<LocalDate>) publication.getAvailableDates();

    public Reservation newReservation() {
        return this.build();
    }

    public Reservation confirmedReservation(){
        Reservation reservation = newReservation().confirmReservationAsOwner();
        return reservation;
    }

    public Reservation clientInformsReceptionReservation() {
        Reservation reservation = confirmedReservation().informReceptionAsClient();
        return reservation;
    }

    public Reservation ownerInformsDeliverReservation() {
        Reservation reservation = confirmedReservation().informDeliverAsOwner();
        return reservation;
    }

    public Reservation bothConfirmRentStartedReservation() {
        Reservation reservation = ownerInformsDeliverReservation().informReceptionAsClient();
        return reservation;
    }

    public Reservation clientInformsReturningVehicleReservation() {
        Reservation reservation = bothConfirmRentStartedReservation().informDeliverAsClientAndScore(5);
        return reservation;
    }

    public Reservation ownerInformsReceptionReservation() {
        Reservation reservation = bothConfirmRentStartedReservation().informReceptionAsOwnerAndScore(5);
        return reservation;
    }

    public Reservation bothConfirmRetuningVehicleReservation() {
        Reservation reservation = ownerInformsReceptionReservation().informDeliverAsClientAndScore(5);
        return reservation;
    }
//
    public Reservation build() {
        return new Reservation(publication, client, selectedDates);
    }
}
