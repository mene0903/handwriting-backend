package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.dto.FastApiPredictResponse;
import com.antaehoo.handwriting.dto.HandwritingRequest;
import com.antaehoo.handwriting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HandwritingVerificationService {


    private final VowelService vowelService;
    private final ConsonantService consonantService;
    private final NormalizationService normalizationService;
    private final UserRepository userRepository;
    private final RestClient fastApiRestClient;

    @Value("${handwriting.similarity-threshold:0.6}")
    private double similarityThreshold;           // ← 여기 추가


    private static final List<Character> VOWELS = List.of(
            'ㅏ','ㅐ','ㅑ','ㅒ','ㅓ','ㅔ','ㅕ','ㅖ','ㅗ','ㅘ','ㅙ','ㅚ','ㅛ',
            'ㅜ','ㅝ','ㅞ','ㅟ','ㅠ','ㅡ','ㅢ','ㅣ'
    );

    public boolean isVowel(char c) {
        return VOWELS.contains(c);
    }

    @Transactional
    public boolean verifyAndSave(HandwritingRequest request) {

        boolean tf = true;

        FastApiPredictResponse prediction = fastApiRestClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(FastApiPredictResponse.class);

        String charName = request.getCharName();
        String predictedChar = prediction.getPredictedChar();
        Double similarity = prediction.getSimilarity();

        if(!charName.equals(predictedChar) || similarity < similarityThreshold) {
            tf = false;
        }


        if (tf) {
                if (isVowel(charName.charAt(0))) {
                vowelService.saveNormalization(request);
            } else {
                consonantService.saveNormalization(request);
            }
        }

        return tf;
    }

}
