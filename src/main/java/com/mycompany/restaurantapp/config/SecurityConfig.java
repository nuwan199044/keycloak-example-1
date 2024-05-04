package com.mycompany.restaurantapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(t -> t.disable());
        http.authorizeHttpRequests(requests ->
                requests.requestMatchers(HttpMethod.GET, "/restaurants/public/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/restaurants/public/menu/*").permitAll()
                        .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(t ->
                t.jwt(Customizer.withDefaults())
        );
        http.sessionManagement(
                t -> t.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        return http.build();
    }

    @Bean
    public DefaultMethodSecurityExpressionHandler msecurity() {
        DefaultMethodSecurityExpressionHandler dmseh = new DefaultMethodSecurityExpressionHandler();
        dmseh.setDefaultRolePrefix("");
        return dmseh;
    }

    @Bean
    public JwtAuthenticationConverter converter() {
        JwtAuthenticationConverter authConverter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter grantAuthConverter = new JwtGrantedAuthoritiesConverter();
        grantAuthConverter.setAuthorityPrefix("");
        grantAuthConverter.setAuthoritiesClaimName("roles");
        authConverter.setJwtGrantedAuthoritiesConverter(grantAuthConverter);
        return authConverter;
    }

}
