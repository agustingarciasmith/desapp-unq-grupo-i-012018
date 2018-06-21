package ar.edu.unq.desapp.grupoi.rest;

public class Endpoints {

  private static final String BASE = "/backend";

  public class Users {
    public static final String BASE = Endpoints.BASE + "/users";
  }

  public class Publications {
    public static final String BASE = Endpoints.BASE + "/publication";
    public static final String CREATE = "/create";
    public static final String USER_PUBLICATIONS = "/user";
  }

  public class Vehicles {
    public static final String BASE = Endpoints.BASE + "/vehicles";
  }
}
