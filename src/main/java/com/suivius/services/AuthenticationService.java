package com.suivius.services;

import org.springframework.security.core.userdetails.User;
import com.suivius.security.JwtTokenUtil;
import com.suivius.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AuthenticationService implements UserDetailsService{


	@Autowired
	  private JwtTokenUtil jwtTokenUtil;
	
	  private static final String ROLE_PREFIX   = "ROLE_";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("testuser".equals(username)) {
			return new User("testuser", "$2a$10$slYQmyNdGOF5b.bjN0icEO7r2Y3PvlASesM02IYB6GvjEG8m8kiy2",
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
