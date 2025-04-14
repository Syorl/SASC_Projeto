package com.example.sasc_api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sasc_api.domain.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
    Optional<Admin> findByEmail(String email);
}
