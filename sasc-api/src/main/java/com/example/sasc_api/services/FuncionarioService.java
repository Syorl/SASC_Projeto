package com.example.sasc_api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.sasc_api.domain.Funcionario;
import com.example.sasc_api.domain.Enum.AcessLevels;
import com.example.sasc_api.dto.AuthenticationRegister;
import com.example.sasc_api.dto.FuncionarioRegister;
import com.example.sasc_api.infra.security.WebSecurityConfig;
import com.example.sasc_api.repositories.FuncionarioRepository;

import jakarta.transaction.Transactional;
@Service
public class FuncionarioService {
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    UserService usersService;
    @Autowired
    private WebSecurityConfig webSecurityConfig;

    @Transactional
    public Funcionario save(FuncionarioRegister funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("funcionario cannot be empty");
        }

        if (funcionarioRepository.existsByEmail(funcionario.getEmail())) {
            throw new IllegalArgumentException("Employee with email " + funcionario.getEmail() + " already exists");
        }

        try {

            String encodedPassword = webSecurityConfig.passwordEncoder().encode(funcionario.getPassword());
            Funcionario savedFuncionario = new Funcionario();
            savedFuncionario.setEmail(funcionario.getEmail());
            savedFuncionario.setPassword(encodedPassword);
            savedFuncionario.setAcessLevels(AcessLevels.USER); 
            savedFuncionario.setSetor(funcionario.getSetor());

            AuthenticationRegister user = new AuthenticationRegister(savedFuncionario.getEmail(), encodedPassword,
                    AcessLevels.USER);
            usersService.save(user);
            System.out.println("Employee saved in users table");

            return funcionarioRepository.save(savedFuncionario);

        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Integrity violation while saving employee: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("Error while saving employee", e);
        }
    }

    public Funcionario findByEmail(String email) {
        return funcionarioRepository.findByEmail(email).orElse(null);
    }

}
