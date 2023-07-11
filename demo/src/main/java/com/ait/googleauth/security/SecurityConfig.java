package com.ait.googleauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/user/**").hasAuthority("ROLE_USER")
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")// Дозволяємо доступ до головної сторінки
                        .anyRequest().authenticated() // Вимагаємо авторизацію для всіх інших запитів
                ).oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/login") // Вказуємо власну сторінку логіну
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("ROLE_USER")
                .and()
                .withUser("adimn")
                .password(passwordEncoder().encode("password"))
                .roles("ROLE_ADMIN");
    }
}
