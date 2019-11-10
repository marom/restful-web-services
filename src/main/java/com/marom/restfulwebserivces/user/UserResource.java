package com.marom.restfulwebserivces.user;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserResource {

    private UserServiceDao userServiceDao;

    public UserResource(UserServiceDao userServiceDao) {
        this.userServiceDao = userServiceDao;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userServiceDao.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        return userServiceDao.findOne(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        User savedUser = userServiceDao.save(user);
    }
}
