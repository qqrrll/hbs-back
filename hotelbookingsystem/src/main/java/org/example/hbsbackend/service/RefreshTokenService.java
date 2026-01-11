package org.example.hbsbackend.service;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.entity.RefreshToken;
import org.example.hbsbackend.entity.User;
import org.example.hbsbackend.repository.RefreshTokenRepository;
import org.example.hbsbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RefreshTokenRepository repo;
    private final UserRepository userRepository;

    private final long refreshExpirationMs = 7 * 24 * 60 * 60 * 1000;

    public RefreshToken createRefreshToken(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow();

        repo.deleteByUser(user);

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(
                Instant.now().plusMillis(refreshExpirationMs)
        );

        return repo.save(token);
    }

    public RefreshToken verify(String token) {
        RefreshToken refreshToken = repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            repo.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }
        return refreshToken;
    }
}
