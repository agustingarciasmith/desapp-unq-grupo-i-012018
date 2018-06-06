package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.contexts.TestContext;
import ar.com.dgarcia.javaspec.api.variable.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.model.ActionNotAllowed;
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

//        Variable<ReservationBuilder> builder = Variable.create();
//        Variable<Reservation> reservation = Variable.create();
//
//        describe("reservation transition", () -> {
//
//            beforeEach(()-> {
//                builder.set(new ReservationBuilder());
//            });
//
//            it("when a reservation is created its on pending state", () -> {
//                assertTrue(builder.get().newReservation().getState() instanceof PendingState);
//            });
//
//            it("the owner can confirm a reservation pending", () -> {
//                assertTrue(builder.get().confirmedReservation().getState() instanceof ConfirmedState);
//            });
//
//            it("an action on an incorrect stage cant be made", () -> {
//                reservation.set(builder.get().newReservation());
//                try {
//                    reservation.get().getOwner().informReceptionAsOwnerAndScore(reservation.get(), 5);
//
//                    Assertions.failBecauseExceptionWasNotThrown(ActionNotAllowed.class);
//                } catch (ActionNotAllowed e) {
//                    assertThat(e).hasMessage(ActionNotAllowed.MESSAGE);
//                }
//
//            });
//
//            it("the client informs that receives the vehicle", () -> {
//                assertTrue(builder.get().clientInformsReceptionReservation().getState() instanceof ClientReceivedState);
//            });
//
//            it("the owner informs that the vehicle is delivered", () -> {
//                assertTrue(builder.get().ownerInformsDeliverReservation().getState() instanceof OwnerDeliveredState);
//            });
//
//            it("both confirm rent starts", () -> {
//                Reservation rentStartedReservation = builder.get().bothConfirmRentStartedReservation();
//                assertTrue(rentStartedReservation.getState() instanceof RentStartedState);
//            });
//
//            it("the client informs returning the vehicle", () -> {
//                assertTrue(builder.get().clientInformsReturningVehicleReservation().getState() instanceof ClientDeliveredState);
//            });
//
//            it("the owner informs vehicle has been returned", () -> {
//                assertTrue(builder.get().ownerInformsReceptionReservation().getState() instanceof OwnerReceivedState);
//            });
//
//            it("when both confirm rent finishes", () -> {
//                assertTrue(builder.get().bothConfirmRetuningVehicleReservation().getState() instanceof RentFinishedState);
//            });
//        });
//
//        describe("reservation changes depending time passed", () -> {
//
//            beforeEach(()-> {
//                builder.set(new ReservationBuilder());
//            });
//
//            it("if 30 minutes pass after client confirms receiving vehicle reservation is rejected", () -> {
//
//                reservation.set(builder.get().clientInformsReceptionReservation());
//
//            });
//        });
    }
}


