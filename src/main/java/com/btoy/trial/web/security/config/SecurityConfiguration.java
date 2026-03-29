package com.btoy.trial.web.security.config;

import com.btoy.trial.persistence.dao.user.UserRepository;
import com.btoy.trial.web.config.propeties.endpoints.AuthenticationEndpointConfiguration;
import com.btoy.trial.web.security.filter.JwtAuthenticationPerRequestFilter;
import com.btoy.trial.web.security.filterexception.PreAccessDeniedHandler;
import com.btoy.trial.web.security.filterexception.PreAuthenticationEntryPoint;
import com.btoy.trial.web.security.userdetails.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private static final String PASSWORD_ENCODER = "passwordEncoder";

    private final AuthenticationEndpointConfiguration endpointConfiguration;
    private final UserDetailsService userDetailService;
    private final JwtAuthenticationPerRequestFilter filter;
    private final PreAuthenticationEntryPoint entryPoint;
    private final PreAccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authManager -> authManager
                        .requestMatchers(endpointConfiguration.getLogin()).permitAll()
                        .requestMatchers(endpointConfiguration.getRegister()).permitAll()
                        .anyRequest().authenticated())
                .addFilterAfter(filter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(configurer -> configurer
                        .authenticationEntryPoint(entryPoint)
                        .accessDeniedHandler(accessDeniedHandler))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailService).passwordEncoder(encoder());
        return authenticationManagerBuilder.build();
    }

    @Bean(value = PASSWORD_ENCODER)
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

