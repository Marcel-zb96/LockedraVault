package com.vault.lockedravault.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

public class AuthTokenFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String userName = jwtUtils.getUserNameForJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication", e);
        }
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String token = null;
        for (Cookie cookie : request.getCookies()) {
            System.out.println(cookie);
            if (cookie.getName().equals("accessToken")) {
                token = cookie.getValue();
            }
        }
        return token;
    }
}
