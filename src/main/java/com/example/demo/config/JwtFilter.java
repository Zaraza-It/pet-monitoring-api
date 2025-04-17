package com.example.demo.config;

import com.example.demo.model.ShelterDetails;
import com.example.demo.service.JWTService;
import com.example.demo.service.ShelterDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

         if (token == null || !token.startsWith("Bearer ")) {
             filterChain.doFilter(request, response);
             return;
         }


        if (isTokenValid(token)) {
            String username = jwtService.extractUserName(token.substring(7));
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                authenticateUser (username, token, request);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUser (String username, String token, HttpServletRequest request) {
        ShelterDetails userDetails = (ShelterDetails) context.getBean(ShelterDetailsService.class).loadUserByUsername(username);

        if (jwtService.validateToken(token.substring(7), userDetails)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
            logger.info("User authenticated successfully " + username);
        } else {
            logger.warn("Invalid JWT token for user " + username);
        }
    }

    private boolean isTokenValid(String token) {
        return token != null && token.startsWith("Bearer ");
    }
}

