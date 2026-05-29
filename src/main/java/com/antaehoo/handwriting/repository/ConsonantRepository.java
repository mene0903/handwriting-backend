package com.antaehoo.handwriting.repository;

import com.antaehoo.handwriting.dto.StrokeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsonantRepository extends JpaRepository<Consonant, Long> {
    Consonant findByCharName(char charName);
}
