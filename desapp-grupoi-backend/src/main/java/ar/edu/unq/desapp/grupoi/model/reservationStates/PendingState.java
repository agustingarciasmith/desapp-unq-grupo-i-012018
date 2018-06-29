package ar.edu.unq.desapp.grupoi.model.reservationStates;

import ar.edu.unq.desapp.grupoi.model.Reservation;
import ar.edu.unq.desapp.grupoi.model.ReservationState;

import javax.persistence.Entity;

@Entity(name = "Pending")
public class PendingState extends ReservationState {
    public void confirm(Reservation reservation) { reservation.setState(new ConfirmedState());
    }

    @Override
    public String createEmailMessage(Reservation reservation) {
        return "Publication N°: "+ reservation.getPublication().getId() +" has got a reservation on pending state";
    }
}
