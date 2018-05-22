package ar.edu.unq.desapp.grupoi.model;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.TestContext;
import ar.edu.unq.desapp.grupoi.model.errors.model.InvalidDate;
import org.assertj.core.api.Assertions;
import org.junit.runner.RunWith;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JavaSpecRunner.class)
public class AvailabilityScheduleTests extends JavaSpec<TestContext> {

    @Override
    public void define() {
        describe("date available for renting", () -> {
            LocalDate aRentingDate = LocalDate.of(2018, 10, 7);
            LocalDate aNotRentingDate = LocalDate.of(2018, 11, 8);
            AvailabilitySchedule aSchedule = new AvailabilitySchedule();
            aSchedule.setAsRentingDate(aRentingDate);

            it("a date cannot be set as available on an availability schedule twice", () -> {
                try {
                    aSchedule.setAsRentingDate(aRentingDate);
                    Assertions.failBecauseExceptionWasNotThrown(InvalidDate.class);
                } catch (InvalidDate e) {
                    assertThat(e).hasMessage(InvalidDate.MESSAGE);
                }
            });

            it("a date in the schedule is recognised as available for renting", () -> {
                assertTrue(aSchedule.isAvailableForRentOn(aRentingDate));
            });

            it("a date not in the schedule isnt recognise as available for renting", () -> {
                assertFalse(aSchedule.isAvailableForRentOn(aNotRentingDate));
            });

            describe("removing a date from renting schedule", () -> {
                it("if the date is available it removes it", () -> {
                    aSchedule.removeFromAvailability(aRentingDate);
                    assertFalse(aSchedule.isAvailableForRentOn(aRentingDate));
                });

                it("if the date was not available it returns an error", () -> {
                    try {
                        aSchedule.removeFromAvailability(aNotRentingDate);
                        Assertions.failBecauseExceptionWasNotThrown(InvalidDate.class);
                    } catch (InvalidDate e) {
                        assertThat(e).hasMessage(InvalidDate.MESSAGE);
                    }
                });
            });
        });
    }
}
