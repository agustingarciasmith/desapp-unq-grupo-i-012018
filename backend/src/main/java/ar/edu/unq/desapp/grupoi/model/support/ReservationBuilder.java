package ar.edu.unq.desapp.grupoi.model.support;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.User;

public class ReservationBuilder {
    private UserBuilder userBuilder = new UserBuilder();
    private User client = userBuilder.withCuil("1111111111").build();
    private User owner = userBuilder.withCuil("2222222222").build();
    private Publication publication = new PublicationBuilder().withOwner(owner).build();

    public Reservation build() {
        return new Reservation(publication, client);
    }
}
