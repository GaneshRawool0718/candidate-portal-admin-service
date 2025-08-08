package com.example.adminService.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.adminService.security.JwtAuthFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    /*
     * This configuration class sets up the security for the application.
     * It configures the security filter chain, authentication manager, and password encoder.
     */
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // This method configures the security filter chain for the application
        // It disables CSRF protection, allows public access to signup and login endpoints,
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/signup", "/auth/login").permitAll() // public
                .requestMatchers("/api/candidates/register").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/candidates/*/score").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/candidates/*/status").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
        // This method provides the AuthenticationManager bean
        // It uses the AuthenticationConfiguration to create an instance of AuthenticationManager
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // This method provides the PasswordEncoder bean
        // It uses BCryptPasswordEncoder to encode passwords securely
        return new BCryptPasswordEncoder();
    }
}
