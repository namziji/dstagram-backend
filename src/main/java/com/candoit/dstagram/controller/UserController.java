package com.candoit.dstagram.controller;

import com.candoit.dstagram.model.User;
import com.candoit.dstagram.repository.UserRepository;
import com.candoit.dstagram.service.UserService;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Controller("UserController")
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private UserRepository userRepository;


    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RequestMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(String email, String password, String nickname) {
        String email = email;
        String password = password;
        String nickname = nickname;





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
