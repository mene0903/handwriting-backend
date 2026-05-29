package com.antaehoo.handwriting.controller;

import com.antaehoo.handwriting.dto.HandwritingRequest;
import com.antaehoo.handwriting.dto.StrokeData;
import com.antaehoo.handwriting.repository.Consonant;
import com.antaehoo.handwriting.service.ConsonantService;
import com.antaehoo.handwriting.service.DoubleConsonantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/handwriting")
@CrossOrigin(origins = "*") // 플러터 웹에서 포트가 계속 바뀌어도 통신되도록 전체 개방
@RequiredArgsConstructor
public class HandwritingController {

    private final ConsonantService consonantService;
    private final DoubleConsonantService doubleConsonantService;

    @PostMapping("/save")
    public ResponseEntity<String> saveHandwriting(@RequestBody HandwritingRequest request) {
        consonantService.saveNormalization(request);

        return ResponseEntity.ok("DB 저장 성공!");
    }

    @PostMapping("/double/save")
    public ResponseEntity<String> saveDoubleConsonant() {
        doubleConsonantService.saveDoubleConsonant();
        return ResponseEntity.ok("ㄲ 저장 성공!");
    }


    @GetMapping("/latest")
    public ResponseEntity<List<StrokeData>> getLatestHandwriting() {
        Consonant latestConsonant = consonantService.getLatestConsonant();

        if (latestConsonant == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(latestConsonant.getVectorMap());
    }

    @GetMapping("/double")
    public ResponseEntity<List<StrokeData>> getDoubleConsonant() {
        List<StrokeData> doubleVectorMap = doubleConsonantService.getDoubleConsonant();

        if (doubleVectorMap == null || doubleVectorMap.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(doubleVectorMap);
    }
}