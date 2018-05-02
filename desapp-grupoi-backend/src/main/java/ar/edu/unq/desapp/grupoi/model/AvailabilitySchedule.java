package ar.edu.unq.desapp.grupoi.model;

import ar.edu.unq.desapp.grupoi.model.errors.InvalidDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvailabilitySchedule {

    private List<LocalDate> rentingDates = new ArrayList<>();

    public void setAsRentingDate(LocalDate aRentingDate) {
        if (isAvailableForRentOn(aRentingDate)) throw new InvalidDate();
        rentingDates.add(aRentingDate);
    }

    public boolean isAvailableForRentOn(LocalDate aRentingDate) {
        return rentingDates.contains(aRentingDate);
    }

    public void removeFromAvailability(LocalDate aRentingDate) {
        if (!isAvailableForRentOn(aRentingDate)) throw new InvalidDate();
        rentingDates.remove(aRentingDate);
    }

    public List<LocalDate> getRentingDates() {
        return rentingDates;
    }
}
