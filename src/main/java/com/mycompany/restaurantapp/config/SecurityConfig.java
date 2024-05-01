package com.mycompany.restaurantapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(t -> t.disable());
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.GET, "/restaurants/public/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurants/public/menu/*").permitAll()
                        .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(t ->
                t.opaqueToken(Customizer.withDefaults())
        );
        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }
}
