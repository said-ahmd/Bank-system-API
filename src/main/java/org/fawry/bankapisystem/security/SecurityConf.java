package org.fawry.bankapisystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConf {
    private final AuthenticationProvider authenticationProvider;
    private final JWTFilter jwtAuthFilter;

    public SecurityConf(AuthenticationProvider authenticationProvider, JWTFilter jwtAuthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf->
                csrf.disable()
            );
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/bank/auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            );
        http
           .sessionManagement(
                session-> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           );
        http
          .authenticationProvider(authenticationProvider)
          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
