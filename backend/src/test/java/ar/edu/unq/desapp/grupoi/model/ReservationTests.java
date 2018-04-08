package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.edu.unq.desapp.grupoi.model.support.ReservationBuilder;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

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
                assertThat(reservation.get().getState()).isEqualTo(ReservationState.PENDING);
            });

            it("the owner can confirm a reservation pending", () -> {
                owner.get().confirmReservation(reservation.get());
                assertThat(reservation.get().getState()).isEqualTo(ReservationState.CONFIRMED);
            });

            it("the client informs that accepts the vehicle", () -> {
                owner.get().confirmReservation(reservation.get());
                client.get().informsAcceptance(reservation.get());
                assertThat(reservation.get().getState()).isEqualTo(ReservationState.ACCEPTANCE_PENDING);
            });

            it("the owner then confirms acceptance", () -> {
                owner.get().confirmReservation(reservation.get());
                client.get().informsAcceptance(reservation.get());
                owner.get().confirmAcceptance(reservation.get());
                assertThat(reservation.get().getState()).isEqualTo(ReservationState.RENT_STARTED);
            });
        });




    }
}


