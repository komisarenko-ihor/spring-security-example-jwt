package com.example.springsecurityexamplejwt.config;

import com.example.springsecurityexamplejwt.filter.CustomAuthenticationFilter;
import com.example.springsecurityexamplejwt.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final AuthenticationManagerBuilder auth;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        return http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilter(new CustomAuthenticationFilter(auth.getOrBuild()))
                .authorizeRequests(
                        authorize -> {
                            authorize
                                    .antMatchers("/h2-console/**", "/token/refresh").permitAll()
                                    .antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN", "ROLE_MANAGER")
                                    .antMatchers(HttpMethod.POST, "/api/users").hasAnyAuthority("ROLE_ADMIN")
                                    .antMatchers(HttpMethod.PUT, "/api/users").hasAnyAuthority("ROLE_ADMIN");
                        }
                )
                .authorizeRequests().anyRequest().authenticated().and()
                .headers()
                .frameOptions()
                .sameOrigin().and()
                .build();
    }
}
