package com.example.apartmentmanagement.repository.permission;

import com.example.apartmentmanagement.entities.permission.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM refresh_tokens WHERE user_id = :userId", nativeQuery = true)
    void deleteTokenByUserId(@Param("userId") Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM refresh_tokens WHERE token = :refreshToken", nativeQuery = true)
    void deleteTokenByRefreshToken(@Param("refreshToken") String refreshToken);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM refresh_tokens WHERE user_id = :userId and expiry_date < NOW()", nativeQuery = true)
    void deleteLastTokenByUserId(@Param("userId") Long userId);
}