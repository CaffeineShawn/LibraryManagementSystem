package com.caffeineshawn.coursedesign.Repository;

import com.caffeineshawn.coursedesign.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findUserByUsername(String username);
}
