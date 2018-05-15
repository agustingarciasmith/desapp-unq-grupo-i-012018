package ar.edu.unq.desapp.grupoi.rest;

import ar.edu.unq.desapp.grupoi.model.User;
import ar.edu.unq.desapp.grupoi.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(method = POST, path = Endpoints.Users.CREAR)
    public void create(@RequestBody User user) {
        logger.info(String.format("Creando usuario => %s %s %s", user.getName(), user.getEmail(), user.getCuil()));
        service.create(user);
    }
}
