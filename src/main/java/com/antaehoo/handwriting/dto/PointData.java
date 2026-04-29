package com.antaehoo.handwriting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointData {
    private double x;
    private double y;
    private double p; // 플러터에서 pressure를 'p'로 보냈으므로 변수명을 일치시켜야 합니다.
}