package ar.edu.unq.desapp.grupoi.model.errors;

public enum ErrorCode {
  INVALID_REQUEST, UNEXPECTER_ERROR;

  public class User {
    private static final String BASE = "user.";
    private static final String NOT_PRESENT = "notPresent";
    private static final String INVALID_FORMAT = "invalidFormat";

    private static final String NAME = "name.";
    public static final String NAME_NOT_PRESENT = BASE + NAME + NOT_PRESENT;
    public static final String NAME_OUT_OF_BOUNDS = BASE + NAME + "outOfBounds";

    private static final String EMAIL = "email.";
    public static final String EMAIL_NOT_PRESENT = BASE + EMAIL + NOT_PRESENT;
    public static final String EMAIL_INVALID_FORMAT = BASE + EMAIL + INVALID_FORMAT;

    private static final String CUIL = "cuil.";
    public static final String CUIL_INVALID_FORMAT = BASE + CUIL + INVALID_FORMAT;
    public static final String CUIL_NOT_PRESENT = BASE + CUIL + NOT_PRESENT;

    public static final String ADDRESS_NOT_PRESENT = BASE + "address." + NOT_PRESENT;
  }
}
