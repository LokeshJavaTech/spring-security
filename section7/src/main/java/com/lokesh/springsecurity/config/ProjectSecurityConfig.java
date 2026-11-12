package com.lokesh.springsecurity.config;

import com.lokesh.springsecurity.exception.CustomAccessDeniedHandler;
import com.lokesh.springsecurity.exception.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
    {
        /*
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        */
        http
            .csrf(csrfCustomizer -> csrfCustomizer.disable())
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans", "/user1", "/user2", "/user3").authenticated()
                    .requestMatchers("/contact", "/notices", "/error", "/register").permitAll()
            );
        http.formLogin(withDefaults());

        // Supports 401 exception handling only when user is trying to log in using HTTP Basic Authentication flow
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));

        // Supports 401 exception handling globally throughout the project whenever 401 is thrown
        // Supports 403 exception handling
        http.exceptionHandling(ehc -> ehc
                                                                             .accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
