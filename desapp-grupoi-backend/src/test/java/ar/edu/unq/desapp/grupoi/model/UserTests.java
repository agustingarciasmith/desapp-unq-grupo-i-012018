package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.model.InvalidMail;
import ar.edu.unq.desapp.grupoi.model.errors.model.InvalidReservation;
import ar.edu.unq.desapp.grupoi.model.errors.model.NameLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.model.ScoreOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
import ar.edu.unq.desapp.grupoi.model.support.ReservationBuilder;
import ar.edu.unq.desapp.grupoi.model.support.UserBuilder;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

@RunWith(JavaSpecRunner.class)
public class UserTests extends JavaSpec<TestContext> {

  @Override
  public void define() {

    Variable<UserBuilder> userBuilder = Variable.create();

    beforeEach(()-> {
      userBuilder.set(new UserBuilder());
    });

    describe("user creation", () -> {

      it("cant build user with name shorter than 4 characters", () -> {
        try {
          userBuilder.get().withName("nan").build();

          failBecauseExceptionWasNotThrown(NameLengthOutOfBounds.class);
        } catch (NameLengthOutOfBounds e) {
          assertThat(e).hasMessage(NameLengthOutOfBounds.MESSAGE);
        }
      });

      it("cant build user with name longer than 50 characters", () -> {
        try {
          userBuilder.get().withName("Its an unusually long name to register on this app now").build();

          failBecauseExceptionWasNotThrown(NameLengthOutOfBounds.class);
        } catch (NameLengthOutOfBounds e) {
          assertThat(e).hasMessage(NameLengthOutOfBounds.MESSAGE);
        }
      });

      it("cant build user with invalid mail", () -> {
        try {
          userBuilder.get().withEmail("notAnEmail").build();

          failBecauseExceptionWasNotThrown(InvalidMail.class);
        } catch (InvalidMail e) {
          assertThat(e).hasMessage("Email notAnEmail is invalid");
        }
      });
    });

    describe("publication", () -> {
      Variable<User> publicationOwner = Variable.create();
      Variable<User> anotherUser = Variable.create();
      Variable<Publication> publication = Variable.create();

      beforeEach(()-> {
        publicationOwner.set(userBuilder.get().build());
        anotherUser.set(new UserBuilder().withCuil("2222222222").build());
        publication.set(new PublicationBuilder().build());
        publicationOwner.get().createPublication(publication.get());
      });

      it("a user who creates a publication becomes its owner", () -> {
        assertThat(publication.get().getOwner().getCuil()).isEqualTo(publicationOwner.get().getCuil());
        assertThat(publication.get().getOwner().getCuil()).isNotEqualTo(anotherUser.get().getCuil());
      });

      it("a user can make a reservation on another user publication", () -> {
        Reservation newReservation = anotherUser.get().makeReservationAsClient(publication.get());

        assertThat(newReservation.getClient().getCuil()). isEqualTo(anotherUser.get().getCuil());
      });

      it("a user cant make a reservation on a publication of her own", () -> {
        try {
          publicationOwner.get().makeReservationAsClient(publication.get());

          failBecauseExceptionWasNotThrown(InvalidReservation.class);
        } catch (InvalidReservation e) {
          assertThat(e).hasMessage("Cant apply for your own publication");
        }
      });
    });

    describe("user scoring", () -> {
      Variable<Reservation> reservation = Variable.create();
      Variable<User> owner = Variable.create();
      Variable<User> client = Variable.create();

      beforeEach(()-> {
        reservation.set(new ReservationBuilder().bothConfirmRentStartedReservation());
        owner.set(reservation.get().getOwner());
        client.set(reservation.get().getClient());
      });

      it("the owner has to score the client when informing receiveing the vehicle", () -> {
        owner.get().informReceptionAsOwnerAndScore(reservation.get(), 5);
        assertThat(client.get().getScore()).isEqualTo(5);
      });

      it("the client has to score the owner when informing delivering the vehicle", () -> {
        client.get().informDeliverAsClientAndScore(reservation.get(), 5);
        assertThat(owner.get().getScore()).isEqualTo(5);
      });

      it("the score should be over 0", () -> {
        try {
          client.get().informDeliverAsClientAndScore(reservation.get(), 0);
          failBecauseExceptionWasNotThrown(ScoreOutOfBounds.class);
        } catch (ScoreOutOfBounds e) {
          assertThat(e).hasMessage(ScoreOutOfBounds.MESSAGE);
        }
      });

      it("the score should be less than 6", () -> {
        try {
          client.get().informDeliverAsClientAndScore(reservation.get(), 6);
          failBecauseExceptionWasNotThrown(ScoreOutOfBounds.class);
        } catch (ScoreOutOfBounds e) {
          assertThat(e).hasMessage(ScoreOutOfBounds.MESSAGE);
        }
      });

    });
  }
}
