package com.example.myBoard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean   // 이거슨! 클래스 인스턴스다!
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((request) -> request
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll() // permitAll / hasRole
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/user/login")
                        .loginProcessingUrl("/login") // 얘는 Security가 관리하는애! GetMapping 필요읍뜸
                        .defaultSuccessUrl("/"))
                .logout((out) -> out
                        .logoutSuccessUrl("/")
                        .logoutUrl("/logout")   // 얘는 Security가 관리하는애! GetMapping 필요읍뜸
                );
        return http.build();
    }
}