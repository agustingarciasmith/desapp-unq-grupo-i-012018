package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.services.mail.MailClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class PruebaController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private MailClient mailClient;

  @Autowired
  public PruebaController(MailClient mailClient) {
    this.mailClient = mailClient;
  }

  @RequestMapping("/holis")
  public String holis() {
    return "Holis lalis";
  }

  @RequestMapping(method = GET, path = "/mandala")
  public String mandala() {
    logger.info("mandando");
    this.mailClient.prepareAndSend("garciasmithagustin@gmail.com", "Holiiiiis");
    return "mandadin";
  }
}
