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
@Table(name = "double_consonant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoubleConsonant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "double_consonant_id")
    private Long doubleConsonantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consonant_id", nullable = false)
    private Consonant consonant;

    @Column(name = "char_name", nullable = false, unique = false, length = 1)
    private char charName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "vector_map", columnDefinition = "json")
    private List<StrokeData> vectorMap; // DB의 JSON이 자동으로 이 List 객체로 변환됩니다.
}
