package com.theironyard.users_core.service;

import com.theironyard.bands_core.model.Band;
import com.theironyard.entity_repositories.BandRepository;
import com.theironyard.entity_repositories.UserRepository;
import com.theironyard.users_core.model.User;
import com.theironyard.utils.PasswordHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

/**
 * Created by ahanger on 4/28/2016.
 */
@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private BandRepository bandRepository;

    @Autowired
    public UserService(UserRepository userRepository, BandRepository bandRepository) {
        this.userRepository = userRepository;
        this.bandRepository = bandRepository;
    }

    public User getUserByUsername(String username) {
        LOG.info("Looking for user with username: " + username);
        User user = userRepository.findOneByUsername(username);

        if(user == null) {
            LOG.info("User not found");
        }

        return user;
    }

    public User getUserById(String id) {
        LOG.info("Looking for user with id: " + id);
        User user = userRepository.findOneById(id);

        if(user == null) {
            LOG.info("User not found");
        }

        return user;
    }

    public User createUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        LOG.info("Searching for account: " + user.getId());
        User userCheck = userRepository.findOneById(user.getId());

        if (userCheck == null) {
            LOG.info("Creating user");
            user.setPassword(PasswordHash.createHash(user.getPassword()));
            userRepository.save(user);
        }

        return user;
    }

    public User modifyUser(User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        User userCheck = userRepository.findOneById(user.getId());
        userCheck.setUsername(user.getUsername());
        userCheck.setPassword(PasswordHash.createHash(user.getPassword()));
        userCheck.setFirstName(user.getFirstName());
        userCheck.setLastName(user.getLastName());
        userCheck.setCity(user.getCity());
        userCheck.setState(user.getState());
        userCheck.setEmail(user.getEmail());
        userCheck.setPhoneNum(user.getPhoneNum());

        LOG.info("Account " + user.getId() + " account has been updated");
        userRepository.save(userCheck);

        return userRepository.findOneByUsername(userCheck.getUsername());
    }

    public void removeUser(User user) {
        User userCheck = userRepository.findOneByUsername(user.getUsername());
        List<Band> bands = bandRepository.findAllByUserId(user.getId());
        bandRepository.delete(bands);
        userRepository.delete(userCheck);

        LOG.info("Account " + user.getId() + " has been deleted from the data store");
    }
}
