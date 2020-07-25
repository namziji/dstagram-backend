package com.candoit.dstagram.service;

import com.candoit.dstagram.model.User;
import com.candoit.dstagram.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Transactional
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(String email, String password, String nickname) {

        if(userRepository.existByEmail(email) == true) {
            throw HttpClientErrorException.BadRequest;
        }

        User user = new User(email, password, nickname);
        userRepository.save(user);


        return user;
    }

    public void signIn() {

    }

    public User getProfile(User user) {
        return user;
    }
}
