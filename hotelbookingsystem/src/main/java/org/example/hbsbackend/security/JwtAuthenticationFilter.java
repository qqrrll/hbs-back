package org.example.hbsbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.startsWith("/api/auth/")
                || path.startsWith("/swagger-ui/")
                || path.startsWith("/v3/api-docs/");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1️⃣ Берём заголовок Authorization
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            // 2️⃣ Достаём JWT
            String token = header.substring(7);

            // 3️⃣ Валидируем JWT
            if (jwtTokenProvider.validateToken(token)) {

                // 4️⃣ Достаём email из JWT
                String email = jwtTokenProvider.getUsernameFromToken(token);

                // 5️⃣ Загружаем пользователя ИЗ БД (id + role)
                UserDetailsImpl userDetails =
                        (UserDetailsImpl) userDetailsService
                                .loadUserByUsername(email);

                // 6️⃣ Создаём Authentication с ролями
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                // 7️⃣ Кладём в SecurityContext
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // 8️⃣ Передаём дальше по цепочке
        filterChain.doFilter(request, response);
    }
}
