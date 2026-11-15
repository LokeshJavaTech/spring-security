package com.lokesh.springsecurity.config;

import com.lokesh.springsecurity.exception.CustomAccessDeniedHandler;
import com.lokesh.springsecurity.exception.CustomBasicAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

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
            .cors(corsConfig -> corsConfig.configurationSource(getCorsConfigurationSource()))
            .csrf(csrfCustomizer -> csrfCustomizer.disable())
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans", "/user", "/user1", "/user2", "/user3").authenticated()
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

    private CorsConfigurationSource getCorsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));    // Backend allowing HttpMethods requests from frontend like GET, PUT, POST ...
                corsConfiguration.setAllowCredentials(true);                            // Backend allowing user credentials or any other cookie from frontend
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));    // Backend allowing all headers from frontend
                corsConfiguration.setMaxAge(60L);
                return corsConfiguration;
            }
        };
    }
}
