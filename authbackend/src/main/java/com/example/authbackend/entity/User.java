//package entity;
//
//import jakarta.persistence.*;
//import java.time.Instant;
//
//@Entity
//@Table(name = "users")
//public class User {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long user_id;
//
//    @Column(nullable = false, unique = true)
//    private String username;
//
//    @Column(nullable = false, unique = true)
//    private String email;
//
//    @Column(nullable = false)
//    private String password;
//
//    private Instant created_at = Instant.now();
//    private Long reset_password_expiry;
//    private String reset_password_token;
//
//    // Getters and Setters
//    public Long getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(Long user_id) {
//        this.user_id = user_id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public Instant getCreated_at() {
//        return created_at;
//    }
//
//    public void setCreated_at(Instant created_at) {
//        this.created_at = created_at;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Long getReset_password_expiry() {
//        return reset_password_expiry;
//    }
//
//    public void setReset_password_expiry(Long reset_password_expiry) {
//        this.reset_password_expiry = reset_password_expiry;
//    }
//
//    public String getReset_password_token() {
//        return reset_password_token;
//    }
//
//    public void setReset_password_token(String reset_password_token) {
//        this.reset_password_token = reset_password_token;
//    }
//}

package com.example.authbackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Instant createdAt = Instant.now();

    private String resetPasswordToken;

    private Long resetPasswordExpiry;
}



