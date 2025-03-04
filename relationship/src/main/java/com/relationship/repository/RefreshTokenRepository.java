package com.relationship.repository;

import com.relationship.entity.SecureToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<SecureToken, UUID> {
    Optional<SecureToken> findByIdAndExpiresAtAfter(UUID id, Instant expiresAt);
}
