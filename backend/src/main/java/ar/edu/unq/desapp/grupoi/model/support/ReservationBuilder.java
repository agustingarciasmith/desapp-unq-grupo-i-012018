package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;

public class ReservationBuilder {
    private UserBuilder userBuilder = new UserBuilder();
    private User client = userBuilder.withCuil("1111111111").build();
    private User owner = userBuilder.withCuil("2222222222").build();
    private Publication publication = new PublicationBuilder().withOwner(owner).build();

    public Reservation newReservation() {
        return this.build();
    }

    public Reservation confirmedReservation(){
        Reservation reservation = owner.confirmReservationAsOwner(newReservation());
        return reservation;
    }

    public Reservation clientInformsReceptionReservation() {
        Reservation reservation = client.informReceptionAsClient(confirmedReservation());
        return reservation;
    }

    public Reservation ownerInformsDeliverReservation() {
        Reservation reservation = owner.informDeliverAsOwner(confirmedReservation());
        return reservation;
    }

    public Reservation bothConfirmsRentStartedReservation() {
        Reservation reservation = client.informReceptionAsClient(ownerInformsDeliverReservation());
        return reservation;
    }

    public Reservation clientInformsReturningVehicleReservation() {
        Reservation reservation = client.informDeliverAsClient(bothConfirmsRentStartedReservation());
        return reservation;
    }

    public Reservation ownerInformsReceptionReservation() {
        Reservation reservation = owner.informReceptionAsOwner(bothConfirmsRentStartedReservation());
        return reservation;
    }

    public Reservation bothConfirmRetuningVehicleReservation() {
        Reservation reservation = client.informDeliverAsClient(ownerInformsReceptionReservation());
        return reservation;
    }

    public Reservation build() {
        return new Reservation(publication, client);
    }
}
