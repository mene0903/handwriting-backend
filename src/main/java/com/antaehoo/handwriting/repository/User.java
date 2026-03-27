package com.antaehoo.handwriting.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name", nullable = false, unique = false, length = 255)
    private String userName;

    @Column(name = "user_login_id", nullable = false, unique = true, length = 255)
    private String userLoginId;

    @Column(name = "user_password", nullable = false, unique = true, length = 255)
    private String userPassword;

    @Column(name = "user_email", nullable = false, unique = true, length = 255)
    private String userEmail;
}
