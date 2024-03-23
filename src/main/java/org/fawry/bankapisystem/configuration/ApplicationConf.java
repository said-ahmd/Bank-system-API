package org.fawry.bankapisystem.configuration;

import org.fawry.bankapisystem.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConf {
    private final UserRepository userRepository;

    public ApplicationConf(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
///
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {

        return conf.getAuthenticationManager();
    }
    @Bean
    UserDetailsService userDetailsService(){

        return username ->
                userRepository.findByEmail(username)
                        .orElseThrow( ()-> new UsernameNotFoundException("user not foutne"));
    }
    @Bean
    AuthenticationProvider authenticationProvider(){


        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
