package com.suivius.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.suivius.security.WebSecurityConfig.SECRET_KEY_SPEC;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Extract the JWT token from the request (e.g., from Authorization header)
        String token = extractTokenFromRequest(request);

        // Validate and parse the JWT token
        if (token != null && validateToken(token)) {
            // Create an authentication object based on the token

            Authentication authentication = createAuthentication(token);

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Exclude the "Bearer " prefix
        }
        return null;
    }

    private boolean validateToken(String token) {
        try {
            // Parse and verify the token using a JWT library or custom logic
            // Here's an example using the jjwt library:
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY_SPEC).parseClaimsJws(token);

            // Perform additional validation checks if needed
            // For example, check the token expiration:
            Date expirationDate = claimsJws.getBody().getExpiration();
            Date currentDate = new Date();
            if (expirationDate != null && expirationDate.before(currentDate)) {
                return false; // Token has expired
            }

            // Token is valid
            return true;
        } catch (Exception e) {
            // Token validation failed
            return false;
        }
    }
    private Authentication createAuthentication(String token) {
        // Extract the necessary information from the token to create an authenticated user
        String username = extractUsernameFromToken(token);
        List<GrantedAuthority> authorities = extractAuthoritiesFromToken(token);
        // Create an Authentication object with the extracted information
        return new UsernamePasswordAuthenticationToken(username, null, authorities);
    }
    private String extractUsernameFromToken(String token) {
        try {
            // Parse the token to retrieve the claims
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY_SPEC).parseClaimsJws(token);

            // Extract the username from the "sub" claim
            return claimsJws.getBody().getSubject();
        } catch ( Exception   e) {
            // Token validation failed
            return null;
        }
    }
    private List<GrantedAuthority> extractAuthoritiesFromToken(String token) {
        try {
            // Parse the token to retrieve the claims
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY_SPEC).parseClaimsJws(token);

            // Extract the authorities from the "roles" claim
            List<String> roles = List.of("USER");//claimsJws.getBody().get("roles", List.class);

            // Create a list of GrantedAuthority objects from the extracted roles
            return roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        } catch ( Exception   e) {
            // Token validation failed
            return Collections.emptyList();
        }
    }


}
