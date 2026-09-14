package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.dto.HandwritingRequest;
import com.antaehoo.handwriting.dto.StrokeData;
import com.antaehoo.handwriting.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VowelService {

    private final VowelRepository vowelRepository;
    private final NormalizationService normalizationService;
    private final UserRepository userRepository;

    @Transactional
    public void registerVowel(Vowel vowel) {
        //테스트 용. 예외처리 나중에
        vowelRepository.save(vowel);
    }

    @Transactional(readOnly = true)
    public Vowel getLatestVowel() {
        List<Vowel> list = vowelRepository.findAll();
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    @Transactional
    public void saveNormalization(HandwritingRequest request) {
        Optional<User> byId = userRepository.findById(1L); //테스트용, 나중에 리펙토링
        User user = byId.get();

        List<StrokeData> normalization = normalizationService.normalization(request);

        Vowel vowel = new Vowel();
        vowel.setUser(user);
        vowel.setCharName(request.getCharName().charAt(0));
        vowel.setVectorMap(normalization);
        vowel.setCharCount(1);

        registerVowel(vowel);
    }

}
