package ar.edu.unq.desapp.grupoi.services.user;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.contexts.TestContext;
import ar.edu.unq.desapp.grupoi.config.Parameters;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.VehicleType;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepositoryImpl;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepository;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepositoryImpl;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleService;
import ar.edu.unq.desapp.grupoi.services.vehicle.VehicleServiceImpl;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.Mockito.*;


@RunWith(JavaSpecRunner.class)
public class VehicleServiceTests extends JavaSpec<TestContext> {
  private UserRepository userRepo;
  private VehicleRepository vehicleRepo;
  private Parameters parameters;
  private VehicleService service;
  private User existentUser;
  private Vehicle existingVehicle;
  private Vehicle anotherExistingVehicle;

  @Override
  public void define() {
    beforeEach(() -> {
      this.userRepo = mock(UserRepositoryImpl.class);
      this.vehicleRepo = mock(VehicleRepositoryImpl.class);

      this.parameters = new Parameters();
      this.parameters.setMinVehicleDescriptionLength(1);
      this.parameters.setMaxVehicleDescriptionLength(1000);

      this.service = new VehicleServiceImpl(userRepo, vehicleRepo, parameters);
      this.existentUser = new User(1L, "name", "address", "email", "cuil", "avatar");
      this.existingVehicle = new Vehicle(1L, VehicleType.AUTO, 1, "description", "license");
      this.anotherExistingVehicle = new Vehicle(2L, VehicleType.AUTO, 1, "description", "license");

      Mockito.when(this.userRepo.get(1)).thenReturn(existentUser);
    });

    describe("create a vehicle", () -> {
      it("successfully", () -> {
        Vehicle vehicle = new Vehicle(Long.valueOf(1), VehicleType.AUTO, 2, "description", "license");
        service.create(existentUser.getId(), vehicle);

        verify(vehicleRepo, times(1)).create(vehicle);
        assertThat(existentUser.getVehicles()).contains(vehicle);
      });
    });

    describe("can't create vehicle", () -> {
      it("without a user id", () -> {
        try {
          service.create(null, null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.User.ID_NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });

      it("without a vehicle", () -> {
        try {
          service.create(Long.valueOf(1), null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });

      it("without a type", () -> {
        Vehicle vehicle = new Vehicle(Long.valueOf(1), null, 2, "description", "license");
        try {
          service.create(Long.valueOf(1), vehicle);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.TYPE_NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });

      describe("with invalid number of passangers", () -> {
        Consumer<Integer> assertInvalidNumberOfPassangers = (numberOfPassangers) -> {
          Vehicle vehicle = new Vehicle(Long.valueOf(1), VehicleType.AUTO, numberOfPassangers, "description", "license");
          try {
            service.create(Long.valueOf(1), vehicle);
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
          } catch (InvalidRequestException e) {
            assertThat(e.errors()).contains(ErrorCode.Vehicle.NUMBER_OF_PASSANGERS_INVALID);
            verify(vehicleRepo, times(0)).create(any(Vehicle.class));
          }
        };

        it("null number of passangers", () -> {
          assertInvalidNumberOfPassangers.accept(null);
        });

        it("0 number of passangers", () -> {
          assertInvalidNumberOfPassangers.accept(0);
        });

        it("negative number of passangers", () -> {
          assertInvalidNumberOfPassangers.accept(-1);
        });
      });

      describe("with invalid description", () -> {
        Consumer<String> assertInvalidDescription = (description) -> {
          Vehicle vehicle = new Vehicle(Long.valueOf(1), VehicleType.AUTO, 1, description, "license");
          try {
            service.create(Long.valueOf(1), vehicle);
            failBecauseExceptionWasNotThrown(InvalidRequestException.class);
          } catch (InvalidRequestException e) {
            assertThat(e.errors()).contains(ErrorCode.Vehicle.DESCRIPTION_OUT_OF_BOUNDS);
            verify(vehicleRepo, times(0)).create(any(Vehicle.class));
          }
        };

        it("null description", () -> {
          assertInvalidDescription.accept(null);
        });

        it("shorter description than permited", () -> {
          parameters.setMinVehicleDescriptionLength(5);
          assertInvalidDescription.accept("1234");
        });

        it("larger description than permited", () -> {
          parameters.setMaxVehicleDescriptionLength(5);
          assertInvalidDescription.accept("123456");
        });
      });

      it("without a license", () -> {
        Vehicle vehicle = new Vehicle(Long.valueOf(1), VehicleType.AUTO, 2, "description", null);
        try {
          service.create(Long.valueOf(1), vehicle);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.LICENSE_NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });
    });

    describe("delete a vehicle", () -> {
      beforeEach(() -> {
        service.create(existentUser.getId(), existingVehicle);
      });

      it("succesfully", () -> {
        service.delete(existentUser.getId(), existingVehicle);
        assertThat(existentUser.getVehicles()).isEmpty();
      });
    });

    describe("can't delete a vehicle", () -> {
      beforeEach(() -> {
        service.create(existentUser.getId(), existingVehicle);
      });

      it("without user id", () -> {
        try {
          service.delete(null, existingVehicle);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.User.ID_NOT_PRESENT);
          assertThat(existentUser.getVehicles()).contains(existingVehicle);
          verify(vehicleRepo, times(0)).delete(existingVehicle);
        }
      });

      it("without vehicle", () -> {
        try {
          service.delete(Long.valueOf(1), null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.NOT_PRESENT);
          assertThat(existentUser.getVehicles()).contains(existingVehicle);
          verify(vehicleRepo, times(0)).delete(existingVehicle);
        }
      });

      it( "without vehicle id", () -> {
        Vehicle vehicle = new Vehicle(null, null, null, null, null);

        try {
          service.delete(1L, vehicle);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.ID_NOT_PRESENT);
          assertThat(existentUser.getVehicles()).contains(existingVehicle);
          verify(vehicleRepo, times(0)).delete(existingVehicle);
        }
      });
    });

    describe("get vehicles from user", () -> {
      beforeEach(() -> {
         service.create(existentUser.getId(), existingVehicle);
         service.create(existentUser.getId(), anotherExistingVehicle);
      });

      it("succesfully", () -> {
        List<Vehicle> vehicles = service.getFromUser(existentUser.getId());

        assertThat(vehicles).hasSize(2);
        assertThat(vehicles).contains(existingVehicle);
        assertThat(vehicles).contains(anotherExistingVehicle);
      });
    });

    describe("can't get vehicles from user", () -> {
      it("without user id", () -> {
        try {
          service.getFromUser(null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.User.ID_NOT_PRESENT);
        }
      });
    });
  }
}
