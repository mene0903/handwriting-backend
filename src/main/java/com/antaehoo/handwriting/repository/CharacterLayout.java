package com.antaehoo.handwriting.repository;

import com.antaehoo.handwriting.dto.StrokeData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "character_layout")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterLayout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "layout_id")
    private Long layoutId;

    @Column(name = "char_name", nullable = true, unique = false, length = 1)
    private char charName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "initial_position", columnDefinition = "json")
    private List<StrokeData> initialPosition; // DB의 JSON이 자동으로 이 List 객체로 변환됩니다.

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "medial_position", columnDefinition = "json")
    private List<StrokeData> medialPosition; // DB의 JSON이 자동으로 이 List 객체로 변환됩니다.

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "final_position", columnDefinition = "json")
    private List<StrokeData> finalPosition; // DB의 JSON이 자동으로 이 List 객체로 변환됩니다.
}
