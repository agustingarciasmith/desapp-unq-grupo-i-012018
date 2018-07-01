package ar.edu.unq.desapp.grupoi.model.errors.model;

import ar.edu.unq.desapp.grupoi.model.errors.CarpnbException;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;

import java.util.Collections;

public class ActionNotAllowed extends CarpnbException {
    private static final String MESSAGE = "Action not allowed at this stage";

    public ActionNotAllowed() {
      super(MESSAGE, ErrorCode.INVALID_REQUEST, Collections.singletonList(ErrorCode.Reservation.INVALID_STATE));
    }
}
