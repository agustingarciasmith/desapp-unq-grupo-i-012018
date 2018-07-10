package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.services.user.UserCustomizableData;
import ar.edu.unq.desapp.grupoi.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(Endpoints.Users.BASE)
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
  private UserService service;

  @Autowired
  public UsersController(UserService service) {
    this.service = service;
  }

  @PutMapping("/update")
  public User update(@RequestBody UserCustomizableData user, HttpServletRequest request) {
    return service.update(user);
  }

  @RequestMapping(method = GET, path = "/{id}")
  public User getUser(@PathVariable Long id, HttpServletRequest request) {
    return service.getById(id);
  }

  @RequestMapping(method = POST, path = "/login")
  public User login(@RequestBody UserInfo userInfo, HttpServletRequest request) {
    return service.login(userInfo);
  }

}
