package com.ezycart.Config;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class appConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http)
    throws Exception {
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeHttpRequests(Authorize ->
        Authorize
          .requestMatchers("/api/**")
          .authenticated()
          .anyRequest()
          .permitAll()
      )
      .addFilterBefore(null, null)
      .csrf()
      .disable()
      .cors()
      .configurationSource(
        new CorsConfigurationSource() {
          @Override
          public CorsConfiguration getCorsConfiguration(
            HttpServletRequest request
          ) {
            CorsConfiguration cfg = new CorsConfiguration();

            cfg.setAllowedOrigins(
              Arrays.asList("http://localhost:3000", "http://localhost:4200")
            );
            cfg.setAllowedMethods(Collections.singletonList("*"));
            cfg.setAllowCredentials(true);
            cfg.setAllowedHeaders(Collections.singletonList("*"));
            cfg.setMaxAge(3600L);
            return cfg;
          }
        }
      )
      .and()
      .httpBasic()
      .and()
      .formLogin();

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
