package ar.edu.unq.desapp.grupoi.config;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.VehicleType;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class DummyDataCreator {

    public void createUsersFromCSV(String fileName) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                this.createAndSaveUser(attributes);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void createAndSaveUser(String[] metadata){
        User user = new User(metadata[0], metadata[1] +", "+ metadata[2] +", " + metadata[3], metadata[4], metadata[5], metadata[6]);
        Vehicle vehicle = new Vehicle(VehicleType.valueOf(metadata[7]), parseInt(metadata[8]), metadata[9], metadata[10]);
        Publication publication = new Publication(user, vehicle, "CABA", metadata[1] +", "+ metadata[2] +", " + metadata[3], returnAddress(metadata[11] +", "+ metadata[12] +", " + metadata[13]), metadata[14], datesArray(), parseInt(metadata[15]));
        user.addVehicle(vehicle);
    }

    private ArrayList<String> returnAddress(String address) {
        ArrayList<String> addressArray = new ArrayList<>();
        addressArray.add(address);
        return addressArray;
    }

    private ArrayList<LocalDate> datesArray() {
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            LocalDate randomDate = createRandomDate(2018, 2019);
            dates.add(randomDate);
        }
        return dates;
    }

    private int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    private LocalDate createRandomDate(int startYear, int endYear) {
        int day = createRandomIntBetween(1, 28);
        int month = createRandomIntBetween(1, 12);
        int year = createRandomIntBetween(startYear, endYear);
        return LocalDate.of(year, month, day);
    }
}
