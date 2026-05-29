package com.antaehoo.handwriting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DoubleConsonantRepository extends JpaRepository<DoubleConsonant, Long> {
    DoubleConsonant findByCharName(char charName);
}
