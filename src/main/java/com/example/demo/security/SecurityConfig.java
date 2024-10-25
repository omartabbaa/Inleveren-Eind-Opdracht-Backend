package com.example.demo.security;  // Ensure the correct package name

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity  // Enables Spring Security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors()  // Enable CORS
            .and()
            .csrf().disable()  // Disable CSRF for APIs
            .authorizeHttpRequests((requests) -> requests
                .anyRequest().permitAll()  // Allow all requests without authentication
            );

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        // Define CORS configuration
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:5173"));  // Allow requests from the frontend's origin
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));  // Allow these HTTP methods
        corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));  // Allow these headers
        corsConfig.setAllowCredentials(true);  // Allow cookies/auth headers

        // Apply CORS configuration to all paths
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsFilter(source);
    }
}
