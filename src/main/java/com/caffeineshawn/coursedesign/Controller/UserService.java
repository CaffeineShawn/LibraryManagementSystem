package com.caffeineshawn.coursedesign.Controller;

import com.caffeineshawn.coursedesign.Entity.User;
import com.caffeineshawn.coursedesign.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findUserByUserName(String username) {
        return userRepository.findUserByUsername(username);
    }

    public boolean userExistOrNot(String username) {
        User user = findUserByUserName(username).get(0);
        return user != null ;
    }

    public Iterable findAllUser() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
         return userRepository.save(user);
    }

    public String getUserGroup(String username) {
        return userRepository.findUserByUsername(username).get(0).getRole();
    }

    public ResponseEntity<String> userLogin(@RequestBody User user) {

        User validatingUser = findUserByUserName(user.username).get(0);

        if (validatingUser == null || !validatingUser.getPassword().equals(user.getPassword()) ) {
            return new ResponseEntity<String>("Error: Could not found registered user.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("Login Success", HttpStatus.OK);
        }
    }

}
