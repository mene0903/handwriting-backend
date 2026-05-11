package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.repository.Consonant;
import com.antaehoo.handwriting.repository.ConsonantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsonantService {

    private final ConsonantRepository consonantRepository;

    @Autowired
    ConsonantService(ConsonantRepository consonantRepository) {
        this.consonantRepository = consonantRepository;
    }

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

}
