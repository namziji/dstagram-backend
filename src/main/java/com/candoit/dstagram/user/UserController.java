package com.candoit.dstagram.user;

import com.candoit.dstagram.security.JwtAuthenticationProvider;
import com.candoit.dstagram.security.UserAuthService;
import com.candoit.dstagram.security.model.SecurityUser;
import com.candoit.dstagram.security.utils.TokenUtils;
import com.candoit.dstagram.user.model.User;
import com.candoit.dstagram.security.model.AuthUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller("UserController")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserAuthService userAuthService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public User signUp(@RequestBody AuthUser authUser, String nickname) throws Exception {
        User user = new User(authUser.getEmail(), passwordEncoder.encode(authUser.getPassword()),nickname, "ROLE_USER");
        return this.userService.signUp(user);
    }

    @RequestMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthUser authUser, String email, String password) throws Exception {

        jwtAuthenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final AuthUser user = (AuthUser) userAuthService.loadUserByUsername(authUser.getEmail());
        final String token = tokenUtils.generateJwtToken(user);

       return ResponseEntity.ok(token);
    }

    @RequestMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User profile(Long userId) throws Exception {
        return this.userService.getProfile(userId);
    }



}
