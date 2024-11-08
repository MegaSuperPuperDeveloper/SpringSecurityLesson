package com.example.springsecurityexample1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(source -> {
            Map<String, Object> resourceAccess = source.getClaim("realm_access");
            List<String> roles = (List<String>) resourceAccess.get("roles");

            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        });

        return http
                .authorizeHttpRequests(rights -> rights
                .requestMatchers("/api/resource/admin/**").hasAuthority("admin")
                .requestMatchers("/api/resource/user/**").hasAnyAuthority("user", "admin")
                .requestMatchers("/api/resource/auth/**").authenticated()
                .requestMatchers("/api/resource").permitAll()
                .anyRequest().denyAll()
        )
//        .formLogin(Customizer.withDefaults())
        .oauth2ResourceServer(configurer -> configurer
                .jwt(jwtConfigurer -> jwtConfigurer
                        .jwtAuthenticationConverter(converter)
                )
        )
        .build();
    }

}