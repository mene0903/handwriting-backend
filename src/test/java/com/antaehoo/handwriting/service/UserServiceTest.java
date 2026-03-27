package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.repository.User;
import com.antaehoo.handwriting.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User createTestUSer(String id) {
        return new User(null, "name1", id, "password1", "email1");
    }

    @Test
    @DisplayName("회원가입 성공")
    void userRegisterSuccess() {
        //given
        User testUser = createTestUSer("test1");
        //when
        userService.registerUser(testUser);
        Optional<User> test1 = userRepository.findByUserLoginId("test1");
        User user = test1.get();
        //then
        Assertions.assertThat(user.getUserId()).isEqualTo(testUser.getUserId());
    }

    @Test
    @DisplayName("기존 아이디 존재 -> 회원가입 실패")
    void userRegisterFail() {
        //given
        User testUser1 = createTestUSer("test1");
        User testUSer2 = createTestUSer("test1");
        //when
        userService.registerUser(testUser1);
        //then
        Assertions.assertThatThrownBy(() -> userService.registerUser(testUSer2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아이디 중복입니다.");
    }
}
