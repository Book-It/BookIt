package com.theironyard.users_core.rest;

import com.theironyard.entity_repositories.BandRepository;
import com.theironyard.entity_repositories.UserRepository;
import com.theironyard.users_core.model.User;
import com.theironyard.users_core.service.UserService;
import com.theironyard.utils.PasswordHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by ahanger on 4/28/2016.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private UserRepository usersRepository;
    private BandRepository bandsRepository;

    @Autowired
    public UserController(UserService userService, UserRepository usersRepository, BandRepository bandsRepository) {
        this.userService = userService;
        this.usersRepository = usersRepository;
        this.bandsRepository = bandsRepository;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public void login(HttpSession session,
                      @RequestBody User params) throws Exception {
        LOG.info("POST request to /users/login");

        User user = userService.getUser(params.getUsername());

        if (user == null) {
            LOG.info("User does not exists");
            throw new Exception("User does not exists.");
        } else if (!PasswordHash.validatePassword(params.getPassword(), user.getPassword())) {
            LOG.info("Wrong password.");
            throw new Exception("Wrong password.");
        }

        session.setAttribute("username", params.getUsername());
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) {
        LOG.info("POST request to /users/logout");

        session.invalidate();
    }

    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    public User getUser(HttpSession session) throws Exception {
        LOG.info("GET request to /users/get-user");

        String username = (String) session.getAttribute("username");
        User user = userService.getUser(username);

        if (user == null) {
            throw new Exception("Not logged in.");
        }

        return user;
    }

    @RequestMapping(path = "/create-account", method = RequestMethod.POST)
    public void createAccount(HttpSession session,
                              @RequestBody User user) throws Exception {
        LOG.info("POST request to /users/create-account");

        userService.createUser(user);
    }

    @RequestMapping(path = "/edit-account", method = RequestMethod.POST)
    public void editAccount(HttpSession session,
                            @RequestBody User user) throws Exception {
        LOG.info("POST request to /users/edit-account");

        String username = (String) session.getAttribute("username");
        User userCheck = userService.getUser(username);

        userService.modifyUser(userCheck);
    }

    @RequestMapping(path = "/delete-account", method = RequestMethod.DELETE)
    public void deleteAccount(HttpSession session,
                              @RequestBody User user) throws Exception {
        LOG.info("DELETE request to /users/delete-account");

        String username = (String) session.getAttribute("username");
        User userCheck = usersRepository.findOneByUsername(username);

        if (!PasswordHash.validatePassword(user.getPassword(), userCheck.getPassword())) {
            throw new Exception("Incorrect password.");
        }

        userService.removeUser(userCheck);
    }
}
