package com.antaehoo.handwriting.repository;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.antaehoo.handwriting.dto.StrokeData;

@Entity
@Table(name="consonant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Consonant {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "consonant_id")
    private Long consonantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Book 엔티티 객체로 변경

    @Column(name = "char_name", nullable = false, unique = false, length = 1)
    private char charName;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "vector_map", columnDefinition = "json")
    private List<StrokeData> vectorMap; // DB의 JSON이 자동으로 이 List 객체로 변환됩니다.

    @Column(name = "char_count", nullable = true, unique = false)
    private int charCount;
}
