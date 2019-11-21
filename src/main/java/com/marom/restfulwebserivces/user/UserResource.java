package com.marom.restfulwebserivces.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
        User user = userServiceDao.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id = " + id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userServiceDao.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userServiceDao.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException("id = " + id);
        }
     }
}
