package ar.edu.unq.desapp.grupoi.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {

  @RequestMapping("/holis")
  public String holis() {
    return "Holis lalis";
  }
}
