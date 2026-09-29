package com.pdvsystem.api.infra.security;

import com.pdvsystem.api.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var token = this.recoverToken(request);

        if (token != null) {

            // Valida token
            var subject = tokenService.validateToken(token);

            UserDetails user = userRepository.findByEmail(subject);

            if (user != null) {
                System.out.println("AUTHORITIES: " + user.getAuthorities());
            }

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        System.out.println("======================================");
        System.out.println("METHOD: " + request.getMethod());
        System.out.println("URI: " + request.getRequestURI());

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            System.out.println("AUTHENTICATED: " + authentication.isAuthenticated());
            System.out.println("USER: " + authentication.getName());
            System.out.println("AUTHORITIES: " + authentication.getAuthorities());
        } else {
            System.out.println("AUTHENTICATION: NULL");
        }

        System.out.println("======================================");

        filterChain.doFilter(request, response);

    }

    // Recebe o token e converte ele manipulando somente a parte necessaria
    private String recoverToken(HttpServletRequest request) {

        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null) {
            return null;
        }
        return authorizationHeader.replace("Bearer ", "");
    }
}
