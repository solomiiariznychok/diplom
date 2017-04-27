package com.riznuchok.resources;

import com.riznuchok.entity.User;
import com.riznuchok.service.UserService;
import com.riznuchok.service.exception.UpdatedUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by ala on 9.5.16.
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    public static final String OWNER = "authentication.name == #userName";
    public static final String ADMIN = "hasRole('ADMIN')";

    private UserService userService;

    @PreAuthorize(ADMIN + " or " + OWNER)
    @RequestMapping(path = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable String userName) {

        Optional<User> userOptional = userService.findByUserName(userName);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(
                userOptional.get()
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> getUsers() {

        return ResponseEntity.ok(
                        userService.findAll()
        );

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@Validated @RequestBody CreateUserDto userDto) {

        if (userService.findByUserName(userDto.getUserName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User savedUser = userService.save(userDto);

        return ResponseEntity.created(
                linkTo(methodOn(UserRestController.class).getUser(savedUser.getEmail()))
                        .toUri()
        ).body(
                savedUser
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path = "/{userName}", method = RequestMethod.PUT)
    public ResponseEntity<User> createUser(@PathVariable String userName,
                                                   @Validated @RequestBody ModifyUserDto userDto) {

        User savedUser;
        try {
            savedUser = userService.save(userDto, userName);
        } catch (UpdatedUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(
                savedUser
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path = "/{userName}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {

        Optional<User> userOptional = userService.findByUserName(userName);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        userService.delete(userOptional.get());

        return ResponseEntity.ok().build();
    }

    @Autowired
    protected void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
