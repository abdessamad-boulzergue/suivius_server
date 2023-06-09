package com.suivius.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


public class SecurityConfiguration {


    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/suivius/api/v1/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring();
    }

/*
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder()
                .passwordEncoder(p-> passwordEncoder().encode(p))
                .username("user")
                .password("password")
                .roles("USER")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);

        return users;
    }
*/

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}