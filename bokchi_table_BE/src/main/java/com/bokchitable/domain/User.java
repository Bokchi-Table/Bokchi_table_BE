package com.bokchitable.domain;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users",
    uniqueConstraints = {
        @UniqueConstraint(name="uk_users_email", columnNames="email"),
        @UniqueConstraint(name="uk_users_phone", columnNames="phone")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(nullable=false, length=255)
    private String email;

    @Column(name="password_hash", nullable=false, length=255)
    private String passwordHash;

    @Column(length=32, unique=true)
    private String phone;

    @Column(name="created_at", nullable=false, updatable=false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    public User() {}

    // getters/setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}