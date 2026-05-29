/*
정규화 과정
 */

package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.dto.HandwritingRequest;
import com.antaehoo.handwriting.dto.PointData;
import com.antaehoo.handwriting.dto.StrokeData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NormalizationService {

    public List<StrokeData> normalization(HandwritingRequest request) {

        List<StrokeData> strokes = request.getStrokes();

        if(strokes == null || strokes.isEmpty()) {
            throw new IllegalArgumentException("글자 데이터가 입력되지 않았습니다.");
        }

        double minX = Double.POSITIVE_INFINITY; //최소
        double maxX = Double.NEGATIVE_INFINITY; //최대
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for(int i=0; i<strokes.size(); i++) {
            StrokeData strokeData = strokes.get(i);
            List<PointData> points = strokeData.getPoints();

            for(int z = 0; z<points.size(); z++) {
                PointData pointData = points.get(z);

                double x = pointData.getX();
                double y = pointData.getY();

                if(minX > x) minX = x;
                if(maxX < x) maxX = x;
                if(minY > y) minY = y;
                if(maxY < y) maxY = y;
            }
        }

        double width = (maxX - minX) == 0 ? 1 : (maxX - minX);
        double height = (maxY - minY) == 0 ? 1 : (maxY - minY);

        List<StrokeData> normalizationStrokes = new ArrayList<>();

        for(int i=0; i<strokes.size(); i++) {
            StrokeData strokeData = strokes.get(i);
            List<PointData> points = strokeData.getPoints();

            List<PointData> normalizationPointData = new ArrayList<>();

            for(int z=0; z<points.size(); z++) {
                PointData pointData = points.get(z);

                double normalizedX = (pointData.getX() - minX) / width;
                double normalizedY = (pointData.getY() - minY) / height;

                PointData normalizedPoint = new PointData(normalizedX, normalizedY, pointData.getP());

                normalizationPointData.add(normalizedPoint);
            }

            StrokeData normalizedStroke = new StrokeData(normalizationPointData);
            normalizationStrokes.add(normalizedStroke);
        }
        return normalizationStrokes;
    }
}