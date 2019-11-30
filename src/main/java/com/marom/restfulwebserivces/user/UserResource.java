package com.marom.restfulwebserivces.user;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    @Operation(summary = "Get a list of all users")
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userServiceDao.findAllUsers();
    }

    @Operation(summary = "retrieve a user")
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = userServiceDao.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id = " + id);
        }

        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));
        return model;
    }

    @Operation(summary = "Create a user")
    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userServiceDao.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Delete a user")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = userServiceDao.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException("id = " + id);
        }
     }
}
