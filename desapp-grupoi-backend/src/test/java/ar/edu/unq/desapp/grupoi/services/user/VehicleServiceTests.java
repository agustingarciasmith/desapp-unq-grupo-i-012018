package ar.edu.unq.desapp.grupoi.services.user;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.contexts.TestContext;
import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.VehicleType;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepositoryImpl;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepository;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepositoryImpl;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleService;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(JavaSpecRunner.class)
public class VehicleServiceTests extends JavaSpec<TestContext> {
  private UserRepository userRepo;
  private VehicleRepository vehicleRepo;
  private Parameters parameters;
  private VehicleService service;
  private User existentUser;

  @Override
  public void define() {
    beforeEach(() -> {
      this.userRepo = mock(UserRepositoryImpl.class);
      this.vehicleRepo = mock(VehicleRepositoryImpl.class);
      this.parameters = new Parameters();
      this.service = new VehicleServiceImpl(userRepo, vehicleRepo, parameters);
      this.existentUser = new User(Long.valueOf(1), "name", "address", "email", "cuil", "avatar");
    });

    describe("create a vehicle", () -> {
      beforeEach(() -> {
        Mockito.when(this.userRepo.get(1)).thenReturn(existentUser);
      });

      it("creates successfully a user", () -> {
        Vehicle vehicle = new Vehicle(VehicleType.AUTO, 2, "descriotion", "license");
        vehicleRepo.create(vehicle);

        verify(vehicleRepo, times(1)).create(vehicle);
      });
    });
  }

}
