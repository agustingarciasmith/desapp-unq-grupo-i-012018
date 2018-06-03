package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.model.errors.ErrorCode;
import ar.edu.unq.desapp.grupoi.model.errors.InvalidRequestException;
import ar.edu.unq.desapp.grupoi.services.mail.MailClient;
import ar.edu.unq.desapp.grupoi.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class PruebaController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private UserService userService;
  private MailClient mailClient;

  @Autowired
  public PruebaController(MailClient mailClient, UserService userService) {
    this.mailClient = mailClient;
    this.userService = userService;
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

  @RequestMapping(method = POST, path = "/login")
  public User login(@RequestBody UserInfo userInfo) {
      return userService.createIfNotExists(
      Optional.of(userInfo)
        .map(realUserInfo -> new User(
          null,
          realUserInfo.getName(),
          null,
          realUserInfo.getEmail(),
          null,
          realUserInfo.getPicture()))
        .orElseThrow(() -> new InvalidRequestException(
          "Error in login ",
          Collections.singletonList(ErrorCode.Login.USER_INFO_NOT_PRESENT)))
    );
  }
}
