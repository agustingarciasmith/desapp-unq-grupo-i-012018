package ar.edu.unq.desapp.grupoi;

import ar.edu.unq.desapp.grupoi.model.Publication;
import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.Vehicle;
import ar.edu.unq.desapp.grupoi.model.VehicleType;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import ar.edu.unq.desapp.grupoi.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

@Service
@Transactional
public class DummyDataCreator {

  private final UserRepository userRepository;
  private final VehicleRepository vehicleRepository;

  @Autowired
  private PublicationRepository publicationRepository;

  @Autowired
  public DummyDataCreator(UserRepository userRepository, VehicleRepository vehicleRepository) {
    this.userRepository = userRepository;
    this.vehicleRepository = vehicleRepository;
  }

  public void createUsersFromCSV() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(new ClassPathResource("dummyData.csv").getInputStream()));
      String line = br.readLine();
      while (line != null) {
        String[] attributes = line.split(";");
        this.createAndSaveUser(attributes);
        line = br.readLine();
      }
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  private void createAndSaveUser(String[] metadata) {
    User user = new User(metadata[0], metadata[1], metadata[2], metadata[2], metadata[4]);
    Vehicle vehicle = new Vehicle(VehicleType.valueOf(metadata[5]), parseInt(metadata[6]), metadata[7], metadata[8]);
    Publication publication = new Publication(user, vehicle, "CABA", metadata[1], returnAddress(metadata[9]), metadata[10], datesArray(), parseInt(metadata[11]));

    userRepository.createIfNotExists(user);
    vehicleRepository.create(vehicle);
    user.addVehicle(vehicle);

    publicationRepository.save(publication);
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
