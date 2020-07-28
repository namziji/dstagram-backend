package com.candoit.dstagram.user;

import com.candoit.dstagram.user.model.User;
import com.candoit.dstagram.security.model.AuthUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller("UserController")
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(@RequestBody AuthUser authUser, String nickname) {
        User user = new User(0, authUser.getEmail(), passwordEncoder.encode(authUser.getPassword()),nickname, "ROLE_USER", true);
        return this.userService.signUp(user);
    }

    @RequestMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public void signIn() {

        this.userService.signIn();

    }

    @RequestMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User profile(User user) {
        return this.userService.getProfile(user);
    }



}
