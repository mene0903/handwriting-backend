package com.antaehoo.handwriting.controller;

import com.antaehoo.handwriting.dto.HandwritingRequest;
import com.antaehoo.handwriting.dto.StrokeData;
import com.antaehoo.handwriting.repository.Consonant;
import com.antaehoo.handwriting.repository.User;
import com.antaehoo.handwriting.repository.UserRepository;
import com.antaehoo.handwriting.service.ConsonantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/handwriting")
@CrossOrigin(origins = "*") // 플러터 웹에서 포트가 계속 바뀌어도 통신되도록 전체 개방
public class HandwritingController {

    private final ConsonantService consonantService;
    private final UserRepository userRepository;

    @Autowired
    public HandwritingController(ConsonantService consonantService, UserRepository userRepository) {
        this.consonantService = consonantService;
        this.userRepository = userRepository;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveHandwriting(@RequestBody HandwritingRequest request) {
        Optional<User> test = userRepository.findById(1L);
        User testUser = test.get();

        Consonant consonant = new Consonant();
        consonant.setUser(testUser);
        consonant.setCharName(request.getCharName().charAt(0));
        consonant.setVectorMap(request.getStrokes());
        consonant.setCharCount(1);

        consonantService.registerConsonant(consonant);

        return ResponseEntity.ok("DB 저장 성공!");
    }

    @GetMapping("/latest")
    public ResponseEntity<List<StrokeData>> getLatestHandwriting() {
        Consonant latestConsonant = consonantService.getLatestConsonant();

        if (latestConsonant == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(latestConsonant.getVectorMap());
    }
}