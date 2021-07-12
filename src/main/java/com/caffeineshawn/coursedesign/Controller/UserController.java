package com.caffeineshawn.coursedesign.Controller;

import com.caffeineshawn.coursedesign.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    @Autowired
    private UserService userService;



//    @GetMapping
//    public Iterable findAll() {
//        return userService.findAllUser();
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/{username}")
    public List<User> findByUsername(@PathVariable String username) {
        return userService.findUserByUserName(username);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userService.userLogin(user);
    }


    @GetMapping("/{username}/group")
    public String userGroup(@PathVariable String username) {
        return userService.getUserGroup(username);
    }


}

