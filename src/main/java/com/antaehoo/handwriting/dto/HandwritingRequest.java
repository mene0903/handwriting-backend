package com.antaehoo.handwriting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HandwritingRequest {

    // 플러터에서 보내는 글자 이름 (예: "가")
    private String charName;

    // 플러터에서 보내는 전체 획 데이터 리스트
    private List<StrokeData> strokes;

}

/*
DTO 구조

HandWritingRequest
-> List<StrokeData> strokes
-> List<PointData> points
-> point Data = double x, y, p
 */