package org.fawry.bankapisystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

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
            .cors(withDefaults())
            .csrf(csrf->
                csrf.disable()
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/bank/auth/**"
                ,"/bank/transaction/**"
                ,"/v3/api-docs"
                ,"/v3/api-docs/**"
                ,"/swagger-ui/index.html"
                ,"/swagger-ui/**")
                .permitAll()
                .anyRequest()
                .authenticated()
            )
           .sessionManagement(
                session-> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
           )
          .authenticationProvider(authenticationProvider)
          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
