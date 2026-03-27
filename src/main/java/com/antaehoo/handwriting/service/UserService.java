package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.repository.User;
import com.antaehoo.handwriting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registerUser(User user) {

        if(userRepository.existsByUserLoginId(user.getUserLoginId())) {
            throw new IllegalArgumentException("아이디 중복입니다.");
        }

        userRepository.save(user);
    }
}
