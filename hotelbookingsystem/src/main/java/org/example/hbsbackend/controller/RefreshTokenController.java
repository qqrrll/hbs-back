package org.example.hbsbackend.controller;

import lombok.RequiredArgsConstructor;
import org.example.hbsbackend.dto.AuthResponseDTO;
import org.example.hbsbackend.dto.RefreshTokenRequest;
import org.example.hbsbackend.entity.RefreshToken;
import org.example.hbsbackend.security.UserDetailsImpl;
import org.example.hbsbackend.security.JwtTokenProvider;
import org.example.hbsbackend.service.RefreshTokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/refresh")
    public AuthResponseDTO refresh(@RequestBody RefreshTokenRequest request) {

        RefreshToken token =
                refreshTokenService.verify(request.getRefreshToken());

        UserDetailsImpl userDetails =
                new UserDetailsImpl(token.getUser());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        String jwt = jwtTokenProvider.generateToken(authentication);

        return new AuthResponseDTO(jwt);
    }
}
