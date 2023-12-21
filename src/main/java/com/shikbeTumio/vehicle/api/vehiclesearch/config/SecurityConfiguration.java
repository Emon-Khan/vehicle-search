package com.shikbeTumio.vehicle.api.vehiclesearch.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.shikbeTumio.vehicle.api.vehiclesearch.entity.Permissions.*;
import static com.shikbeTumio.vehicle.api.vehiclesearch.entity.Permissions.USER_CREATE;
import static com.shikbeTumio.vehicle.api.vehiclesearch.entity.Roles.ADMIN;
import static com.shikbeTumio.vehicle.api.vehiclesearch.entity.Roles.USER;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/v1/auth/**")
                        .permitAll()
                        /*.requestMatchers("/api/v1/manufacturers/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers(GET, "/api/v1/manufacturers/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/api/v1/manufacturers/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                        .requestMatchers(PUT, "/api/v1/manufacturers/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
//                        .requestMatchers(DELETE, "/api/v1/manufacturers/**").hasAnyAuthority(ADMIN_DELETE.name(), USER_DELETE.name())

                        .requestMatchers("/api/v1/model-trim/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers(GET, "/api/v1/model-trim/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                        .requestMatchers(POST, "/api/v1/model-trim/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                        .requestMatchers(PUT, "/api/v1/model-trim/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
                        //.requestMatchers(DELETE, "/api/v1/model-trim/**").hasAnyAuthority(ADMIN_DELETE.name(), USER_DELETE.name())

                        .requestMatchers("/api/v1/vehicle-details/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers(GET, "/api/v1/vehicle-details/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())

                        .requestMatchers("/api/v1/vehicle-market-price/**").hasAnyRole(ADMIN.name(), USER.name())

                        .requestMatchers(POST, "/api/v1/vehicle-market-price/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())

                        .requestMatchers("/api/v1/**").hasRole(ADMIN.name())

                        .requestMatchers(DELETE, "/api/v1/**").hasAuthority(ADMIN_DELETE.name())*/
                        .anyRequest()
                        .authenticated())
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
