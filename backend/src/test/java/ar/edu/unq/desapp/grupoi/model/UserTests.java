package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.EmailIsInvalid;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidTransaction;
import ar.edu.unq.desapp.grupoi.model.errors.NameLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.support.PublicationBuilder;
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

      it("cant create user with name shorter than 4 characters", () -> {
        try {
          userBuilder.get().withName("nan").build();

          failBecauseExceptionWasNotThrown(NameLengthOutOfBounds.class);
        } catch (NameLengthOutOfBounds e) {
          assertThat(e).hasMessage(NameLengthOutOfBounds.MESSAGE);
        }
      });

      it("cant create user with name longer than 50 characters", () -> {
        try {
          userBuilder.get().withName("Its an unusually long name to register on this app now").build();

          failBecauseExceptionWasNotThrown(NameLengthOutOfBounds.class);
        } catch (NameLengthOutOfBounds e) {
          assertThat(e).hasMessage(NameLengthOutOfBounds.MESSAGE);
        }
      });

      it("cant create user with invalid mail", () -> {
        try {
          userBuilder.get().withEmail("notAnEmail").build();

          failBecauseExceptionWasNotThrown(EmailIsInvalid.class);
        } catch (EmailIsInvalid e) {
          assertThat(e).hasMessage("Email notAnEmail is invalid");
        }
      });

      it("a user is considered equal to another if they have the same cuil", () -> {
        User anotherUser = new UserBuilder().build();
        User differentUser = new UserBuilder().withCuil("2222222222").build();
        assertThat(userBuilder.get().build()).isEqualTo(anotherUser);
        assertThat(userBuilder.get().build()).isNotEqualTo(differentUser);
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
        assertThat(publication.get().getOwner()).isEqualTo(publicationOwner.get());
        assertThat(publication.get().getOwner()).isNotEqualTo(anotherUser.get());
      });

      it("a user can make a reservation on another user publication", () -> {
        Transaction newTransaction = anotherUser.get().makeReservation(publication.get());

        assertThat(newTransaction.getClient()). isEqualTo(anotherUser.get());
      });

      it("a user cant make a reservation on a publication of her own", () -> {
        try {
          publicationOwner.get().makeReservation(publication.get());

          failBecauseExceptionWasNotThrown(InvalidTransaction.class);
        } catch (InvalidTransaction e) {
          assertThat(e).hasMessage("Cant apply for your own publication");
        }
      });
    });
  }
}
