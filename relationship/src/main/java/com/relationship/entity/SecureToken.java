package com.relationship.entity;

import com.relationship.enums.TokenPurpose;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "secure_tokens")
public class SecureToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false, updatable = false)
    private Instant expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TokenPurpose purpose;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    public boolean isExpiredToken(TokenPurpose purpose) {
//        if (this.getExpiryDate().compareTo(Instant.now()) < 0) {
//            return true;    //"Refresh token was expired. Please make a new re-sign in request";
//        }
//        return false;
//    }
}
