package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.ActionNotAllowed;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;
import ar.edu.unq.desapp.grupoi.model.reservationStates.*;
import ar.edu.unq.desapp.grupoi.model.support.ReservationBuilder;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JavaSpecRunner.class)
public class ReservationTests extends JavaSpec<TestContext> {

    @Override
    public void define() {

        Variable<Reservation> reservation = Variable.create();
        Variable<User> owner = Variable.create();
        Variable<User> client =Variable.create();

        describe("reservation creation", () -> {

            beforeEach(()-> {
                reservation.set(new ReservationBuilder().build());
                owner.set(reservation.get().getPublication().getOwner());
                client.set(reservation.get().getClient());
            });

            it("when a reservation is created its on pending state", () -> {
                assertTrue(reservation.get().getState() instanceof PendingState);
            });

            it("the owner can confirm a reservation pending", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                assertTrue(reservation.get().getState() instanceof ConfirmedState);
            });

            it("an action on an incorrect stage cant be made", () -> {
                try {
                    owner.get().informReceptionAsOwner(reservation.get());

                    Assertions.failBecauseExceptionWasNotThrown(ActionNotAllowed.class);
                } catch (ActionNotAllowed e) {
                    assertThat(e).hasMessage(ActionNotAllowed.MESSAGE);
                }

            });

            it("the client informs that receives the vehicle", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                client.get().informReceptionAsClient(reservation.get());
                assertTrue(reservation.get().getState() instanceof ClientReceivedState);
            });

            it("the owner informs that delivered the vehicle", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                owner.get().informDeliverAsOwner(reservation.get());
                assertTrue(reservation.get().getState() instanceof OwnerDeliveredState);
            });

            it("when both confirm rent starts", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                client.get().informReceptionAsClient(reservation.get());
                owner.get().informDeliverAsOwner(reservation.get());
                assertTrue(reservation.get().getState() instanceof RentStartedState);
            });

            it("the client informs returning the vehicle", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                client.get().informReceptionAsClient(reservation.get());
                owner.get().informDeliverAsOwner(reservation.get());
                client.get().informDeliverAsClient(reservation.get());
                assertTrue(reservation.get().getState() instanceof ClientDeliveredState);
            });

            it("the owner informs vehicle has been returned", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                client.get().informReceptionAsClient(reservation.get());
                owner.get().informDeliverAsOwner(reservation.get());
                owner.get().informReceptionAsOwner(reservation.get());
                assertTrue(reservation.get().getState() instanceof OwnerReceivedState);
            });

            it("when both confirm rent finishes", () -> {
                owner.get().confirmReservationAsOwner(reservation.get());
                client.get().informReceptionAsClient(reservation.get());
                owner.get().informDeliverAsOwner(reservation.get());
                owner.get().informReceptionAsOwner(reservation.get());
                client.get().informDeliverAsClient(reservation.get());
                assertTrue(reservation.get().getState() instanceof RentFinishedState);
            });
        });
    }
}


