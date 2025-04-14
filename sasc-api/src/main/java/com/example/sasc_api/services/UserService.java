package com.example.sasc_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.sasc_api.domain.user.User;
import com.example.sasc_api.dto.AuthenticationRegister;
import com.example.sasc_api.dto.AuthenticationResponse;
import com.example.sasc_api.infra.security.TokenService;
import com.example.sasc_api.infra.security.WebSecurityConfig;
import com.example.sasc_api.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
     @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebSecurityConfig webSecurityConfig;
    @Autowired
    private TokenService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Transactional
    public User save(AuthenticationRegister users) throws Exception {
        if (userRepository.existsByEmail(users.getEmail())) {
            throw new Exception(String.format("Email '%s' já cadastrado", users.getEmail()));
        }
        try {
            var user = new User(users.getEmail(), users.getPassword(), users.getRole());
            user.setPassword(webSecurityConfig.passwordEncoder().encode(users.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            throw new Exception("Erro to save user", e);
        }
    }

    public String login(AuthenticationResponse user) throws Exception {
        if(user == null){
            throw new NullPointerException("User cannot be null");
        }
        if (!userRepository.existsByEmail(user.getEmail())) {
            throw new Exception(String.format("Email '%s' não cadastrado", user.getEmail()));
        }
        try {
            System.out.println("User seller encontrado: "+user.getEmail());
            var usernamePassword = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            var auth = authenticationManager.authenticate(usernamePassword);

            if (auth.getPrincipal() instanceof UserDetails) {
                var principal = (UserDetails) auth.getPrincipal();
                return jwtService.generateToken(principal);
            } else {
                throw new Exception("Erro ao autenticar usuário");
            }

        } catch (Exception e) {
            throw new Exception("Erro ao fazer login do usuário", e);
        }
    }

    public User findByEmail(String email) {
        try {
            return userRepository.findByEmail(email).get();
        } catch (Exception e) {
            throw new RuntimeException("User doesn't exist", e);
        }
    }
}
