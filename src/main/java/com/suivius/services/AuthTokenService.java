package com.suivius.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
 import java.time.Clock;
import java.time.temporal.ChronoUnit;
 import static org.springframework.security.core.authority.AuthorityUtils.authorityListToSet;

 @Service
public class AuthTokenService {

	 @Autowired
    private  JwtEncoder jwtEncoder;
	 @Autowired
    private  Clock clock;
     public String generateAuthToken(Authentication authentication) {
		
				        var issuedAt = clock.instant();
		
				        var jwsHeader = JwsHeader.with(() -> "HS256").build();
		
				        var claims = JwtClaimsSet.builder()
				                .issuer("creator")
				                .issuedAt(issuedAt)
				                .expiresAt(issuedAt.plus(30, ChronoUnit.MINUTES))
				                .subject(authentication.getName())
				                .claim("scope", String.join(" ", authorityListToSet(authentication.getAuthorities())))
				                .build();
		
				        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
		    }

}