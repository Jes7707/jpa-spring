package com.jesdev.jpaspring.service;

import com.github.javafaker.Faker;
import com.jesdev.jpaspring.models.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
public class UserService {

    @Autowired
    private Faker faker;

    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init(){

        for (int i=0; i < 50; i++){
            users.add(new User(faker.funnyName().name(),
                    faker.name().username(),
                    faker.dragonBall().character()));
        }
    }

    public List<User> getUsers() {
        log.info("Getting all users");
        return users;
    }

    public User getUserByUsername(String username){
        log.info("Searching username");
        User validatedUser = users.stream().filter(user -> user.getUsername().equals(username)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User %s not found",username)));
        log.info("User found");
        return validatedUser;
    }

    public User createUser(User user){
        log.info("Creating user");
        if (users.stream().anyMatch(u -> u.equals(user.getUsername()))){
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("User %s already exists",user.getUsername()));
        }
        users.add(user);
        log.info("User created");
        return user;
    }

    public User updateUser(User user, String username){
        log.info("Updating user");
        User userToUpdated = getUserByUsername(username);
        userToUpdated.setNickName(user.getNickName());
        userToUpdated.setPassword(user.getPassword());
        log.info("User updated");
        return userToUpdated;
    }

    public void deleteUser(String username){
        log.info("Deleting");
        User userToBeDeleted = getUserByUsername(username);
        users.remove(userToBeDeleted);
        log.info("User deleted");
    }

}
