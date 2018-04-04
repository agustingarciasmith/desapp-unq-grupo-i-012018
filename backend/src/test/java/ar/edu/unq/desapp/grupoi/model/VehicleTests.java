package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.edu.unq.desapp.grupoi.model.errors.DescriptionLengthOutOfBounds;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JavaSpecRunner.class)
public class VehicleTests extends JavaSpec<TestContext> {
  @Override
  public void define() {
    describe("vehicle creation", () -> {
      String shortDescription = "Short description";
      String appropriateDescription = "This is an appropriate description";
      String longDescription = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec qua";

      it("vehicle gets created with appropriate arguments", () -> {
        Vehicle vehicle = new Vehicle(VehicleType.AUTO, 4, appropriateDescription);
        assertThat(vehicle.getType()).isEqualTo(VehicleType.AUTO);
        assertThat(vehicle.getNumberOfPassengers()).isEqualTo(4);
        assertThat(vehicle.getDescription()).isEqualTo(appropriateDescription);
      });

      it("cant create vehicle without type", () -> {
        try {
          new Vehicle(null, 4, appropriateDescription);
          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("Type field is obligatory");
        }
      });

      it("cant create vehicle without number of passengers", () -> {
        try {
          new Vehicle(VehicleType.MOTOCICLETA, null, appropriateDescription);
          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("Number of passengers field is obligatory");
        }
      });

      it("cant create vehicle without description", () -> {
        try {
          new Vehicle(VehicleType.MOTOCICLETA, 4, null);
          Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
        } catch (FieldMissing e) {
          assertThat(e).hasMessage("Description field is obligatory");
        }
      });

      it("description cant be shorter than 30 characters", ()  -> {
        try {
          new Vehicle(VehicleType.MOTOCICLETA, 4, shortDescription);
          Assertions.failBecauseExceptionWasNotThrown(DescriptionLengthOutOfBounds.class);
        } catch (DescriptionLengthOutOfBounds e) {
          assertThat(e).hasMessage(DescriptionLengthOutOfBounds.MESSAGE);
        }
      });

      it("description cant be longer than 200 characters", ()  -> {
        try {
          new Vehicle(VehicleType.MOTOCICLETA, 4, longDescription);
          Assertions.failBecauseExceptionWasNotThrown(DescriptionLengthOutOfBounds.class);
        } catch (DescriptionLengthOutOfBounds e) {
          assertThat(e).hasMessage(DescriptionLengthOutOfBounds.MESSAGE);
        }
      });
    });

  }
}
