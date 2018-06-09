package ar.edu.unq.desapp.grupoi;

import ar.edu.unq.desapp.grupoi.config.DummyDataCreator;
import ar.edu.unq.desapp.grupoi.repositories.PublicationRepository;
import ar.edu.unq.desapp.grupoi.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ar.edu.unq.desapp.grupoi"})

public class DesApp {

  public static void main(String[] args) {

    ApplicationContext ctx = SpringApplication.run(DesApp.class, args);
    DummyDataCreator dummyDataCreator = new DummyDataCreator();
    dummyDataCreator.createUsersFromCSV("C:\\Users\\lalop\\desapp-unq-grupo-i-012018\\desapp-grupoi-backend\\src\\main\\java\\ar\\edu\\unq\\desapp\\grupoi\\dummyData.csv");
  }
}
