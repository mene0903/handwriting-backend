package com.antaehoo.handwriting.repository;

import com.antaehoo.handwriting.dto.PointData;
import com.antaehoo.handwriting.dto.StrokeData;
import com.antaehoo.handwriting.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class ConsonantTest {

    @Autowired
    private ConsonantRepository consonantRepository;

    @Autowired
    private UserRepository userRepository;

    private User createTestUser(String id) {
        return new User(null, "name1", id, "password1", "email1");
    }

    @Test
    @DisplayName("json 저장 test")
    void saveSuccess() {
        //given
        User testUser = createTestUser("1");
        userRepository.save(testUser);

        PointData point1 = new PointData(0.1234, 0.5678, 0.9);
        PointData point2 = new PointData(0.1250, 0.5700, 0.8);
        PointData point3 = new PointData(0.1300, 0.5800, 0.7);

        List<PointData> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        points.add(point3);

        StrokeData stroke1 = new StrokeData(points);

        List<StrokeData> vectorMap = new ArrayList<>();
        vectorMap.add(stroke1);

        //when
        Consonant consonant = new Consonant();
        consonant.setUser(testUser);         // 방금 만든 유저 매핑
        consonant.setCharName('가');      // char 타입인 경우 작은따옴표('') 사용
        consonant.setVectorMap(vectorMap); // 자바 객체(List) 그대로 삽입!
        consonant.setCharCount(1);
        Consonant savedConsonant = consonantRepository.save(consonant);
        //then
        Assertions.assertThat(savedConsonant.getUser()).isEqualTo(testUser);
        System.out.println("저장된 Consonant ID: " + savedConsonant.getConsonantId());
        System.out.println("저장된 User ID: " + savedConsonant.getUser().getUserId());

    }

}
