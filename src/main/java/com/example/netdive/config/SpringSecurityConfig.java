package com.example.netdive.config;

import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/api/v1/oauth2").permitAll()
                        .requestMatchers("/api/v1/board").hasAnyAuthority("USER")
                        .anyRequest().authenticated()
                )
                //.addFilterBefore(new CustomFilter(), BasicAuthenticationFilter.class)
                // addFilter를 넣는 건 이것에 대한 filtering을 하겠다는 의미가 아니다. 이 부분은 내가 잘못 이해함.
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web
                    .ignoring()
                    .requestMatchers("/api/v1/oauth2/**");
        };
    }


}
