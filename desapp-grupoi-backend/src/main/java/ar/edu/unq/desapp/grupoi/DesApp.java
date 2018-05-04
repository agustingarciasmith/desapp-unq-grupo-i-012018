package ar.edu.unq.desapp.grupoi;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({"ar.edu.unq.desapp.grupoi"})
public class DesApp {
  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(DesApp.class, args);

    System.out.println("Spring Boot instantiated beans:");

    String[] beanNames = ctx.getBeanDefinitionNames();

    Arrays.sort(beanNames);
    for (String beanName: beanNames){
      System.out.println(beanName);
    }
  }
}
