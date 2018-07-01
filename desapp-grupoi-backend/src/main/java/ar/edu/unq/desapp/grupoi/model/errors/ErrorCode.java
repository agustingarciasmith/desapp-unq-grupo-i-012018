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
    public static final String NOT_PRESENT = BASE + ErrorCode.NOT_PRESENT;
    public static final String TYPE_NOT_PRESENT = BASE + ".type" + ErrorCode.NOT_PRESENT;
    public static final String NUMBER_OF_PASSANGERS_INVALID = BASE + ".numberOfPassangers.invalid";
    public static final String DESCRIPTION_OUT_OF_BOUNDS = BASE + ".description" + OUT_OF_BOUNDS;
    public static final String LICENSE_NOT_PRESENT = BASE + ".license" + ErrorCode.NOT_PRESENT;
    public static final String ID_NOT_PRESENT = BASE + ".id" + ErrorCode.NOT_PRESENT;
    public static final String CANT_DELETE_PUBLICATION_ASOCIATED = BASE + ".cantDelete.publicationAsociated";
  }

  public class Publication {
    private static final String BASE = "publication";
    public static final String NOT_PRESENT = BASE + ErrorCode.NOT_PRESENT;
    public static final String USER_NOT_PRESENT = BASE + ".user" + ErrorCode.NOT_PRESENT;
    public static final String VEHICLE_NOT_PRESENT = BASE + ".vehicle" + ErrorCode.NOT_PRESENT;
    public static final String VEHICLE_DOESNT_BELONGS_TO_USER = BASE + ".vehicle.doesntBelongsToUser";
    public static final String PUBLICACION_WITH_VEHICLE_EXISTS = BASE + ".vehicle.alreadyExists";
    public static final String CITY_NOT_PRESENT = BASE + ".city" + ErrorCode.NOT_PRESENT;
    public static final String PHONE_NOT_PRESENT = BASE + ".phone" + ErrorCode.NOT_PRESENT;
    public static final String COST_NOT_PRESENT = BASE + ".cost" + ErrorCode.NOT_PRESENT;
    public static final String PICK_UP_ADDRESS_NOT_PRESENT = BASE + ".pickUpAddress" + ErrorCode.NOT_PRESENT;
    public static final String RETURN_ADDRESS_NOT_PRESENT = BASE + ".returnAddress" + ErrorCode.NOT_PRESENT;
    public static final String AVAILABLE_DATES_NOT_PRESENT = BASE + ".avaliableDates" + ErrorCode.NOT_PRESENT;
  }

  public class Reservation {
    private static final String BASE = "reservation";
    private static final String INVALID = ".invalid";
    public static final String INVALID_STATE = BASE + ".state" + INVALID + ".transition";
    public static final String USER_IS_NOT_CLIENT = BASE + ".client" + INVALID;
    public static final String CLIENT_DUPLICATED_RESERVATION = BASE + ".client.duplicated";
    public static final String CLIENT_ID_NOT_PRESENT = BASE + "client.id" + ErrorCode.NOT_PRESENT;
    public static final String USER_IS_NOT_OWNER = BASE + ".owner" + INVALID;
    public static final String ID_NOT_PRESENT = BASE + ".id" + ErrorCode.NOT_PRESENT;
    public static final String USER_ID_NOT_PRESENT = BASE + ".user.id" + ErrorCode.NOT_PRESENT;
    public static final String PUBLICATION_DUPLICATED_RESERVATION = BASE + ".publication.duplicated";
    public static final String PUBLICATION_ID_NOT_PRESENT = BASE + ".publication.id" + ErrorCode.NOT_PRESENT;
    public static final String SELECTED_DATES_NOT_PRESENT = BASE + ".selectedDates" + ErrorCode.NOT_PRESENT;
    public static final String PUBLICATION_NOT_IN_SELECTED_DATES = BASE + ".selectedDates.notInPublication";
  }
}
