package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JavaSpecRunner.class)
public class VehicleTests extends JavaSpec<TestContext> {
  @Override
  public void define() {
    describe("vehicle creation", () -> {
      it("cant create vehicle without type", () -> {
        try {
          new Vehicle(null);
          Assertions.failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
          assertThat(e).hasMessage(Vehicle.TYPE_NOT_PRESENT);
        }
      });
    });

  }
}
