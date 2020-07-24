package com.candoit.dstagram.service;

import com.candoit.dstagram.model.User;
import com.candoit.dstagram.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signUp(User user) {
        return this.userRepository.save(user);
    }

    public void signIn() {

    }

    public User getProfile(User user) {
        return user;
    }
}
