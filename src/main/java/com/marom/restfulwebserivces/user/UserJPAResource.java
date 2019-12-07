package com.marom.restfulwebserivces.user;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    private UserServiceDao userServiceDao;
    private UserRepository userRepository;

    public UserJPAResource(UserServiceDao userServiceDao, UserRepository userRepository) {
        this.userServiceDao = userServiceDao;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Get a list of all users")
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Operation(summary = "retrieve a user")
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (!user.isPresent()) {
            throw new UserNotFoundException("id = " + id);
        }

        EntityModel<User> model = new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));
        return model;
    }

    @Operation(summary = "Create a user")
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(summary = "Delete a user")
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
     }
}
