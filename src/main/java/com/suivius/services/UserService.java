package com.suivius.services;

import com.suivius.exceptions.ResourceNotFoundException;
import com.suivius.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder =passwordEncoder;
    }

    public void saveUser(com.suivius.models.User  user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<com.suivius.models.User> optUser = userRepository.findByUsername(username);
        if(!optUser.isPresent()){
            throw new ResourceNotFoundException("Invalid user name");
        }
        com.suivius.models.User applicationUser = optUser.get();
       return  User.builder().password(applicationUser.getPassword())
               .username(applicationUser.getUsername())
               .roles("USER")
               .passwordEncoder(passwordEncoder::encode)
               .build();
        // return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
    public com.suivius.models.User getUserByNameAndPassword(String name, String password) throws ResourceNotFoundException {
        Optional<com.suivius.models.User> optUser = userRepository.findByUsername(name);
        if(!optUser.isPresent()){
            throw new ResourceNotFoundException("Invalid user name");
        }else if(!optUser.get().getPassword().equals(password)){
            throw new IllegalArgumentException("Invalid password");
        }
        return optUser.get();
    }
}
