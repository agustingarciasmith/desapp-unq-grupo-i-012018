package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.edu.unq.desapp.grupoi.model.errors.FieldMissing;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JavaSpecRunner.class)
public class PublicationTests extends JavaSpec<TestContext>{

    @Override
    public void define() {
        describe("publication creation", () -> {
            String appropriateDescription = "This is an appropriate description";
            Vehicle vehicle = new Vehicle(VehicleType.AUTO, 4, appropriateDescription);
            String city = "Buenos Aires";
            String pickUpAdress = "Adress 1";
            String returnAdress = "Adress 2";
            String contactPhone = "123456789";
            String hoursDays = "mondays";
            Integer cost = 123;

            it("publication gets created with appropriate arguments", () -> {
                Publication newPublication = new Publication(vehicle, city, pickUpAdress, returnAdress, contactPhone, hoursDays, cost);

                assertThat(newPublication.getVehicle()).isEqualTo(vehicle);
                assertThat(newPublication.getCity()).isEqualTo(city);
                assertThat(newPublication.getPickUpAdress()).isEqualTo(pickUpAdress);
                assertThat(newPublication.getReturnAdress()).isEqualTo(returnAdress);
                assertThat(newPublication.getContactPhone()).isEqualTo(contactPhone);
                assertThat(newPublication.getHoursDays()).isEqualTo(hoursDays);
                assertThat(newPublication.getCost()).isEqualTo(cost);
            });

            it("cant create publication without cost", () -> {
                try {
                    new Publication(vehicle, city, pickUpAdress, returnAdress, contactPhone, hoursDays, null);
                    Assertions.failBecauseExceptionWasNotThrown(FieldMissing.class);
                } catch (FieldMissing e) {
                    assertThat(e).hasMessage("Cost field is obligatory");
                }
            });
        });
    }
}
