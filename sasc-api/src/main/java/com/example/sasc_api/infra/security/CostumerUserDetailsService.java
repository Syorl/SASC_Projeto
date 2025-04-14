package com.example.sasc_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.sasc_api.domain.Admin;
import com.example.sasc_api.domain.Funcionario;
import com.example.sasc_api.domain.Enum.AcessLevels;
import com.example.sasc_api.domain.user.User;
import com.example.sasc_api.repositories.AdminRepository;
import com.example.sasc_api.repositories.FuncionarioRepository;

@Service
public class CostumerUserDetailsService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Funcionario " + username + " not found."));
        if (funcionario != null){
            return createUsers(funcionario.getEmail(), funcionario.getPassword(), funcionario.getAcessLevels());
        }
        Admin admin = adminRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Admin " + username + " not found."));
        if (admin != null){
            return createUsers(admin.getEmail(), admin.getPassword(), admin.getAcessLevels());
        }
        throw new UsernameNotFoundException("User not found with email: " + username);
    }

    private User createUsers(String email, String password, AcessLevels role) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setAcessLevels(role);
        return user;
    }

}
