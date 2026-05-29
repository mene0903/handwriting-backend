package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.dto.HandwritingRequest;
import com.antaehoo.handwriting.dto.StrokeData;
import com.antaehoo.handwriting.repository.Consonant;
import com.antaehoo.handwriting.repository.ConsonantRepository;
import com.antaehoo.handwriting.repository.User;
import com.antaehoo.handwriting.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsonantService {

    private final ConsonantRepository consonantRepository;
    private final NormalizationService normalizationService;
    private final UserRepository userRepository;

    @Transactional
    public void registerConsonant(Consonant consonant) {
        //테스트 용. 예외처리 나중에
        consonantRepository.save(consonant);
    }

    @Transactional(readOnly = true)
    public Consonant getLatestConsonant() {
        List<Consonant> list = consonantRepository.findAll();
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Transactional
    public void saveNormalization(HandwritingRequest request) {
        Optional<User> byId = userRepository.findById(1L); //테스트용, 나중에 리펙토링
        User user = byId.get();

        List<StrokeData> normalization = normalizationService.normalization(request);

        Consonant consonant = new Consonant();
        consonant.setUser(user);
        consonant.setCharName(request.getCharName().charAt(0));
        consonant.setVectorMap(normalization);
        consonant.setCharCount(1);

        registerConsonant(consonant);
    }

}