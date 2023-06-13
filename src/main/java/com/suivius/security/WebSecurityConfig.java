package com.suivius.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {
    public static final SecretKeySpec SECRET_KEY_SPEC = new SecretKeySpec("CnYIJPRb2T6zyHcXSD1DsfAQknyM36dfbA2Mhjd2TCk=".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    @Bean
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy () {
            return new NullAuthenticatedSessionStrategy();
    }
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

                        return httpSecurity
                                // we don't need CSRF because our token is invulnerable
                                .csrf().disable()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                                .authorizeRequests(auth -> auth.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                        .antMatchers("/", "/login/**", "/suivius/api/v1/auth").permitAll()
                                        .antMatchers("/suivius/**").authenticated()
                                        .anyRequest().authenticated())
                                     //  .oauth2Login(Customizer.withDefaults())
                                //.oauth2ResourceServer(customizer -> customizer.jwt().jwtAuthenticationConverter(new JwtJwtAuthenticationConverter()))
                                .headers(headers -> headers.frameOptions().sameOrigin().cacheControl())
                                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       PasswordEncoder passwordEncoder,
                                                       UserDetailsService userDetailService) throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class)
                                .userDetailsService(userDetailService)
                                .passwordEncoder(passwordEncoder)
                                .and()
                                .build();
     }
    @Bean
    PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
    }
    @Bean
    JwtEncoder jwtEncoder() {
                return new NimbusJwtEncoder(new ImmutableSecret<>(SECRET_KEY_SPEC));
     }
   /* @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder) {
                UserDetails user = User.builder()
                                .passwordEncoder(passwordEncoder::encode)
                                .username("user")
                                .password("secret")
                                .roles("USER")
                                .build();
                return new InMemoryUserDetailsManager(user);
    }*/
    public static class JwtJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
        @Override
        public AbstractAuthenticationToken convert(Jwt source) {
            return new JwtAuthenticationToken(source, extractAuthorities(source));
        }

        private Collection<? extends GrantedAuthority> extractAuthorities(Jwt jwt) {
            return jwt.<Map<String, List<String>>>getClaim("realm_access")
                    .get("roles")
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.toUpperCase()))
                    .collect(Collectors.toSet());
        }
    }

}
