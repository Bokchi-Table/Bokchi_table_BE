package com.bokchitable.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())                 // Swagger에서 POST/PUT/DELETE 허용
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/error",
                    "/swagger-ui.html", "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/h2-console/**",
                    "/api/**"                              // ← 백엔드 전부 허용 (임시)
                ).permitAll()
                .anyRequest().permitAll()                 // ← 혹시 빠진 경로 있어도 전부 통과
            )
            .formLogin(f -> f.disable())
            .httpBasic(b -> b.disable());

        // H2 콘솔 쓰면 필요
        http.headers(h -> h.frameOptions(f -> f.disable()));
        return http.build();
    }
}