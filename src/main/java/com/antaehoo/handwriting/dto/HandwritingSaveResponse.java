package com.antaehoo.handwriting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HandwritingSaveResponse {
    private boolean pass;
    private String selectedChar;
    private String predictedChar;
    private double confidence;
    private double similarity;
    private String message;
}