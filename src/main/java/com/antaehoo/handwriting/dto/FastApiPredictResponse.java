package com.antaehoo.handwriting.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FastApiPredictResponse {
    private String selectedChar;
    private String predictedChar;
    private Double confidence;
    private Double similarity;
    private String error;

    public boolean hasError() {
        return error != null && !error.isBlank();
    }
}