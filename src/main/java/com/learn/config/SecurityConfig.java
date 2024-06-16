package com.learn.config;

import com.learn.security.ResquestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.addFilterBefore(new ResquestValidationFilter(), BasicAuthenticationFilter.class);
        http.authorizeHttpRequests(c -> c
                .requestMatchers(new AntPathRequestMatcher("/users/**"))
                    .authenticated()
                .requestMatchers(new AntPathRequestMatcher("/greetings/**"))
                    .permitAll());

        return http.build();
    }
}
