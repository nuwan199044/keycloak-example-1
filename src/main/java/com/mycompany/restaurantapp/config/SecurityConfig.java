package com.mycompany.restaurantapp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(t -> t.disable());
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.GET, "/restaurants/public/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurants/public/menu/*").permitAll()
                        .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(t ->
                t.jwt(configurer -> configurer.jwtAuthenticationConverter(jwtAuthConverter))
//                t.opaqueToken(Customizer.withDefaults())
        );
        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
}
