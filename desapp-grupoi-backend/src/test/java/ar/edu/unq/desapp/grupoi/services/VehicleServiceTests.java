package ar.edu.unq.desapp.grupoi.services;

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
import ar.edu.unq.desapp.grupoi.rest.requests.VehicleDTO;
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
      this.existingVehicle = new Vehicle(VehicleType.AUTO, 1, "description", "license", existentUser);
      this.existingVehicle.setId(1L);
      this.anotherExistingVehicle = new Vehicle(VehicleType.AUTO, 1, "description", "license", this.existentUser);
      this.anotherExistingVehicle.setId(2L);

      Mockito.when(this.userRepo.get((long)1)).thenReturn(existentUser);
    });

    describe("create a vehicle", () -> {
      it("successfully", () -> {
        Vehicle vehicle = new Vehicle(VehicleType.AUTO, 2, "description", "license", this.existentUser);
        service.create(dtoFrom(vehicle));

        verify(vehicleRepo, times(1)).create(any(Vehicle.class));
      });
    });

    describe("can't create vehicle", () -> {
      it("without a vehicle data", () -> {
        try {
          service.create(null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });

      it("without a type", () -> {
        Vehicle vehicle = new Vehicle(null, 2, "description", "license", existentUser);
        try {
          service.create(dtoFrom(vehicle));
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.TYPE_NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });

      describe("with invalid number of passangers", () -> {
        Consumer<Integer> assertInvalidNumberOfPassangers = (numberOfPassangers) -> {
          Vehicle vehicle = new Vehicle(VehicleType.AUTO, numberOfPassangers, "description", "license", existentUser);
          try {
            service.create(dtoFrom(vehicle));
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
          Vehicle vehicle = new Vehicle(VehicleType.AUTO, 1, description, "license", existentUser);
          try {
            service.create(dtoFrom(vehicle));
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
        Vehicle vehicle = new Vehicle(VehicleType.AUTO, 2, "description", null, existentUser);
        try {
          service.create(dtoFrom(vehicle));
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.LICENSE_NOT_PRESENT);
          verify(vehicleRepo, times(0)).create(any(Vehicle.class));
        }
      });
    });

    describe("delete a vehicle", () -> {
      beforeEach(() -> {
        service.create(dtoFrom(existingVehicle));
      });

      it("succesfully", () -> {
        service.delete(1L);
        verify(vehicleRepo, times(1)).delete(any(Long.class));
      });
    });

    describe("can't delete a vehicle", () -> {
      beforeEach(() -> {
        service.create(dtoFrom(existingVehicle));
      });

      it("without vehicle", () -> {
        try {
          service.delete(null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.NOT_PRESENT);
          verify(vehicleRepo, times(0)).delete(any(Long.class));
        }
      });

      it( "without vehicle id", () -> {
        VehicleDTO dto = dtoFrom(existingVehicle);
        dto.setVehicleId(null);

        try {
          service.delete(null);
          failBecauseExceptionWasNotThrown(InvalidRequestException.class);
        } catch (InvalidRequestException e) {
          assertThat(e.errors()).contains(ErrorCode.Vehicle.ID_NOT_PRESENT);
          verify(vehicleRepo, times(0)).delete(any(Long.class));
        }
      });
    });

    describe("get vehicles from user", () -> {
      beforeEach(() -> {
         service.create(dtoFrom(existingVehicle));
         service.create(dtoFrom(anotherExistingVehicle));
      });

      it("succesfully", () -> {
        service.getFromUser(existentUser.getId());

        verify(vehicleRepo, times(1)).getUserVehicles(existentUser);
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

  private VehicleDTO dtoFrom(Vehicle vehicle) {
    VehicleDTO dto = new VehicleDTO();
    dto.setVehicleId(vehicle.getId());
    dto.setUserId(vehicle.getOwner().getId());
    dto.setDescription(vehicle.getDescription());
    dto.setLicense(vehicle.getLicense());
    dto.setType(vehicle.getType());
    dto.setNumberOfPassengers(vehicle.getNumberOfPassengers());

    return dto;
  }
}
