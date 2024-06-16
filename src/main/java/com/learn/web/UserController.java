package com.learn.web;

import com.learn.models.SecurityUser;
import com.learn.models.User;
import com.learn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<User> getByUsername(@PathVariable("username")String username){
        if(Objects.isNull(username) || username.isBlank()){
            return ResponseEntity.badRequest().build();
        }

        try {
            final User user = ((SecurityUser) userService.loadUserByUsername(username)).getUser();
            user.setPassword("**********");
            return ResponseEntity.ok(user);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.notFound().build();
        }

    }
}
