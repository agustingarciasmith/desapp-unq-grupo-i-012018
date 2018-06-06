package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;
import ar.edu.unq.desapp.grupoi.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Users.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private UserService service;

  @Autowired
  public UsersController(UserService service) {
    this.service = service;
  }

  @PutMapping("update/{id}")
  public void update(@RequestBody UserCustomizableData user) {
    logger.info(String.format("Updating user => %s %s %s", user.getId().toString(), user.getName(), user.getCuil()));
    service.update(user);
  }

  @GetMapping("login")
  public User login(@RequestBody User user) {
    return this.service.createIfNotExists(user);
  }
}
