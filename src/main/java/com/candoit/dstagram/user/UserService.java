package com.candoit.dstagram.user;

import com.candoit.dstagram.user.model.User;
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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUp(User user) {

        return userRepository.save(user);
    }

    public void signIn() {

    }

    public User getProfile(User user) {
        return user;
    }
}
