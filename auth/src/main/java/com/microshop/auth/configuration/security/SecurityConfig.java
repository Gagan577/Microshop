package com.microshop.auth.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MicroshopAuthenticationProvider microshopAuthenticationProvider;

    public SecurityConfig(final MicroshopAuthenticationProvider microshopAuthenticationProvider) {
        this.microshopAuthenticationProvider = microshopAuthenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder
                .authenticationProvider(microshopAuthenticationProvider);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().disable()
                .formLogin().disable()
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
