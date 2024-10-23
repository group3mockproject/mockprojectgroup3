package com.example.apartmentmanagement.config;

import com.example.apartmentmanagement.entities.permission.RefreshToken;
import com.example.apartmentmanagement.service.permission.impl.JwtService;
import com.example.apartmentmanagement.service.permission.impl.RefreshTokenService;
import com.example.apartmentmanagement.service.permission.impl.UserInforDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

/**
 * JWT authentication filter to validate JWT tokens from incoming requests.
 * This filter intercepts requests, extracts and validates JWT tokens,
 * and sets up Spring Security context if the token is valid.
 * <p>
 * Author: KhangDV
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    UserInforDetailService userInforDetailService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String id = request.getHeader("Userid");

        String jwt = null;
        String rft = null;
        final String email;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                }
                if ("rft".equals(cookie.getName())) {
                    rft = cookie.getValue();
                }
            }
        }

        if (rft == null) {
            if (id != null) {
                Long userId = Long.parseLong(id);
                refreshTokenService.removeLastRefreshTokenByUserId(userId);
            }
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(rft);
            if (refreshToken.isEmpty()) {
                filterChain.doFilter(request, response);
                return;
            }

            RefreshToken refresh = refreshToken.get();
            email = refresh.getUser().getEmail();
            String oauth2Code = refresh.getUser().getUserCode();

            if ((email != null || oauth2Code != null) && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

                if (jwtService.isTokenValid(jwt)) {
                    setAuthentication(request, userDetails);
                } else {
                    if (refreshTokenService.checkAndDeleteExpiredToken(refreshToken.get())) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    } else {
                        // Tạo mới access token
                        System.out.println("remake");
                        String newAccessToken = jwtService.generateToken(userDetails);
                        refresh.setExpiryDate(Instant.now().plusMillis(86400000));
                        refreshTokenService.updateRefreshToken(refresh);

                        ResponseCookie newAccessTokenCookie = ResponseCookie.from("token", newAccessToken)
                                .httpOnly(true)
                                .secure(true)
                                .sameSite("None")
                                .path("/")
                                .maxAge(2 * 60 * 60)
                                .build(); // Thời gian tồn tại của cookie (0)

                        ResponseCookie newRefreshTokenCookie = ResponseCookie.from("rft", refresh.getToken())
                                .httpOnly(true)
                                .secure(true)
                                .sameSite("None")
                                .path("/")
                                .maxAge(24 * 60 * 60)
                                .build();

                        response.addHeader("Set-Cookie", newAccessTokenCookie.toString());
                        response.addHeader("Set-Cookie", newRefreshTokenCookie.toString());
                        setAuthentication(request, userDetails);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
