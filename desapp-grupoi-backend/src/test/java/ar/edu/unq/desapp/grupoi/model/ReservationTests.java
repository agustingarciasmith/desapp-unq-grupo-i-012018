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

    Variable<ReservationBuilder> builder = Variable.create();
    Variable<Reservation> reservation = Variable.create();

    describe("reservation transition", () -> {

      beforeEach(() -> {
        builder.set(new ReservationBuilder());
      });

      it("when a reservation is created its on pending state", () -> {
        assertTrue(builder.get().newReservation().getState() instanceof PendingState);
      });

      it("the owner can confirm a reservation pending", () -> {
        assertTrue(builder.get().confirmedReservation().getState() instanceof ConfirmedState);
      });

      it("the client informs that receives the vehicle", () -> {
        assertTrue(builder.get().clientInformsReceptionReservation().getState() instanceof ClientReceivedState);
      });
    });

    describe("reservation changes depending time passed", () -> {

      beforeEach(() -> {
        builder.set(new ReservationBuilder());
      });

      it("if 30 minutes pass after client confirms receiving vehicle reservation is rejected", () -> {

        reservation.set(builder.get().clientInformsReceptionReservation());

      });
    });
  }
}


