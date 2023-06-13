package com.suivius.rest.controllers;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.models.User;
import com.suivius.rest.dto.Credentials;
import com.suivius.services.AuthTokenService;
import com.suivius.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("suivius/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @Autowired
    private  AuthTokenService authTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            if(user.getUsername() == null || user.getPassword() == null) {
                throw new IllegalArgumentException("UserName or Password is Empty");
            }
            UserDetails userData = userService.loadUserByUsername(user.getUsername());
            if(user.getPassword()==null || !user.getPassword().equals(userData.getPassword())){
                throw new IllegalArgumentException("UserName or Password is Empty");
            }
            String token = "";
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }



    @PostMapping
    public ResponseEntity<String> login(@RequestBody Credentials credentials) {

        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(authTokenService.generateAuthToken(authentication));
    }

}
