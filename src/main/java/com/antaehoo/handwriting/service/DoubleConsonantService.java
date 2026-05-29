package com.antaehoo.handwriting.service;

import com.antaehoo.handwriting.dto.PointData;
import com.antaehoo.handwriting.dto.StrokeData;
import com.antaehoo.handwriting.repository.Consonant;
import com.antaehoo.handwriting.repository.ConsonantRepository;
import com.antaehoo.handwriting.repository.DoubleConsonant;
import com.antaehoo.handwriting.repository.DoubleConsonantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoubleConsonantService {

    private final ConsonantRepository consonantRepository;
    private final DoubleConsonantRepository doubleConsonantRepository;

    @Transactional
    public void saveDoubleConsonant() {
        Consonant baseConsonant = consonantRepository.findByCharName('ㄱ');

        if (baseConsonant == null) {
            throw new IllegalArgumentException("ㄱ 데이터가 없습니다.");
        }

        List<StrokeData> doubleVectorMap = makeDoubleConsonant(baseConsonant);

        DoubleConsonant doubleConsonant = new DoubleConsonant(
                null,
                baseConsonant,
                'ㄲ',
                doubleVectorMap
        );

        doubleConsonantRepository.save(doubleConsonant);
    }

    @Transactional(readOnly = true)
    public List<StrokeData> getDoubleConsonant() {
        DoubleConsonant doubleConsonant =
                doubleConsonantRepository.findByCharName('ㄲ');

        if (doubleConsonant == null) {
            throw new IllegalArgumentException("ㄲ 데이터가 없습니다.");
        }

        return doubleConsonant.getVectorMap();
    }

    public List<StrokeData> makeDoubleConsonant(Consonant baseConsonant) {
        List<StrokeData> original = baseConsonant.getVectorMap();

        List<StrokeData> leftVectorMap = deepCopy(original);
        List<StrokeData> rightVectorMap = deepCopy(original);

        // 원본 비율 유지: scaleX == scaleY
        transform(leftVectorMap, 0.45, 0.45, 0.03, 0.25);
        transform(rightVectorMap, 0.45, 0.45, 0.52, 0.25);

        List<StrokeData> combined = new ArrayList<>();
        combined.addAll(leftVectorMap);
        combined.addAll(rightVectorMap);

        // 여기서 다시 normalization 하면 원본 비율이 깨짐
        return combined;
    }

    private List<StrokeData> deepCopy(List<StrokeData> original) {
        List<StrokeData> copiedStrokes = new ArrayList<>();

        for (StrokeData stroke : original) {
            List<PointData> copiedPoints = new ArrayList<>();

            for (PointData point : stroke.getPoints()) {
                PointData copiedPoint = new PointData(
                        point.getX(),
                        point.getY(),
                        point.getP()
                );

                copiedPoints.add(copiedPoint);
            }

            copiedStrokes.add(new StrokeData(copiedPoints));
        }

        return copiedStrokes;
    }

    private void transform(
            List<StrokeData> strokes,
            double scaleX,
            double scaleY,
            double offsetX,
            double offsetY
    ) {
        for (StrokeData stroke : strokes) {
            for (PointData point : stroke.getPoints()) {
                double newX = point.getX() * scaleX + offsetX;
                double newY = point.getY() * scaleY + offsetY;

                point.setX(newX);
                point.setY(newY);
            }
        }
    }
}