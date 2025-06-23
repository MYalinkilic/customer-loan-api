package com.ing.customer.loan.initializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().disable();


        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("customerA")
                        .password(passwordEncoder().encode("custA"))
                        .roles("CUSTOMER")
                        .build(),
                User.builder()
                        .username("customerB")
                        .password(passwordEncoder().encode("custB"))
                        .roles("CUSTOMER")
                        .build(),
                User.builder()
                        .username("ADMIN")
                        .password(passwordEncoder().encode("adminpass"))
                        .roles("ADMIN")
                        .build()
        );
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
