package ar.edu.unq.desapp.grupoi.model.errors;

public enum ErrorCode {
  INVALID_REQUEST, UNEXPECTER_ERROR, UNAUTHORIZED;

  private static final String NOT_PRESENT = ".notPresent";
  public static final String OUT_OF_BOUNDS = ".outOfBounds";

  public class User {
    private static final String BASE = "user";
    private static final String INVALID_FORMAT = ".invalidFormat";

    private static final String NAME = ".name";
    public static final String NAME_NOT_PRESENT = BASE + NAME + NOT_PRESENT;
    public static final String NAME_OUT_OF_BOUNDS = BASE + NAME + OUT_OF_BOUNDS;

    private static final String EMAIL = ".email";
    public static final String EMAIL_NOT_PRESENT = BASE + EMAIL + NOT_PRESENT;
    public static final String EMAIL_INVALID_FORMAT = BASE + EMAIL + INVALID_FORMAT;

    private static final String CUIL = ".cuil";
    public static final String CUIL_INVALID_FORMAT = BASE + CUIL + INVALID_FORMAT;
    public static final String CUIL_NOT_PRESENT = BASE + CUIL + NOT_PRESENT;

    public static final String ADDRESS_NOT_PRESENT = BASE + ".address" + NOT_PRESENT;

    public static final String ID_NOT_PRESENT = BASE + ".id" + NOT_PRESENT;
  }

  public class Login {
    public static final String USER_INFO_NOT_PRESENT = ".userInfo" + NOT_PRESENT;
  }

  public class Vehicle {
    private static final String BASE = "vehicle";
    public static final String TYPE_NOT_PRESENT = BASE + ".type" + NOT_PRESENT;
    public static final String NUMBER_OF_PASSANGERS_INVALID = BASE + ".numberOfPassangers.invalid";
    public static final String DESCRIPTION_OUT_OF_BOUNDS = BASE + ".description" + OUT_OF_BOUNDS;
    public static final String LICENSE_NOT_PRESENT = BASE + ".license" + OUT_OF_BOUNDS;
    public static final String ID_NOT_PRESENT = BASE + ".id" + NOT_PRESENT;
  }
}
