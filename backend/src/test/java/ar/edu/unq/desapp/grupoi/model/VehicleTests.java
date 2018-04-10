package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.edu.unq.desapp.grupoi.model.errors.DescriptionLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;
import ar.edu.unq.desapp.grupoi.model.support.VehicleBuilder;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JavaSpecRunner.class)
public class VehicleTests extends JavaSpec<TestContext> {
  @Override
  public void define() {
    describe("vehicle creation", () -> {
      Variable<VehicleBuilder> vehicleBuilder = Variable.create();
      String longDescription = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qua";

      beforeEach(()-> {
        vehicleBuilder.set(new VehicleBuilder());
      });

      it("vehicle gets created with appropriate arguments", () -> {
        Vehicle vehicle = vehicleBuilder.get().buildVehicle();
        assertThat(vehicle.getType()).isEqualTo(VehicleType.AUTO);
        assertThat(vehicle.getNumberOfPassengers()).isEqualTo(4);
        assertThat(vehicle.getDescription()).isEqualTo("This is an appropriate description");
      });

      it("cant build vehicle without type", () -> {
        try {
          vehicleBuilder.get().withType(null).buildVehicle();

          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("Type field is obligatory");
        }
      });

      it("cant build vehicle without number of passengers", () -> {
        try {
          vehicleBuilder.get().withPassengers(null).buildVehicle();

          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("Number of passengers field is obligatory");
        }
      });

      it("cant build vehicle without description", () -> {
        try {
          vehicleBuilder.get().withDescription(null).buildVehicle();

          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("Description field is obligatory");
        }
      });

      it("cant build vehicle without license", () -> {
        try {
          vehicleBuilder.get().withLicense(null).buildVehicle();

          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("License field is obligatory");
        }
      });

      it("description cant be shorter than 30 characters", ()  -> {
        try {
          vehicleBuilder.get().withDescription("Short description").buildVehicle();
          Assertions.failBecauseExceptionWasNotThrown(DescriptionLengthOutOfBounds.class);
        } catch (DescriptionLengthOutOfBounds e) {
          assertThat(e).hasMessage(DescriptionLengthOutOfBounds.MESSAGE);
        }
      });

      it("description cant be longer than 200 characters", ()  -> {
        try {
          vehicleBuilder.get().withDescription(longDescription).buildVehicle();

          Assertions.failBecauseExceptionWasNotThrown(DescriptionLengthOutOfBounds.class);
        } catch (DescriptionLengthOutOfBounds e) {
          assertThat(e).hasMessage(DescriptionLengthOutOfBounds.MESSAGE);
        }
      });

      describe("vehicle equality", () -> {

        Variable<Vehicle> anotherVehicule = Variable.create();
        Variable<Vehicle> differentVehicle = Variable.create();

        beforeEach(()-> {
          anotherVehicule.set(vehicleBuilder.get().buildVehicle());
        });

        it("a vehicle is considered equal to another if they have the same license", () -> {
          assertThat(vehicleBuilder.get().buildVehicle()).isEqualTo(anotherVehicule.get());
        });

        it("a vehicle is considered different to another if they have different license", () -> {
          assertThat(vehicleBuilder.get().buildVehicle()).isNotEqualTo(differentVehicle.get());
        });
      });

    });

  }
}
