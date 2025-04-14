package com.example.sasc_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sasc_api.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, String>{

    Optional<Funcionario> findByEmail(String email);
    boolean existsByEmail(String email);
}
