package com.example.sasc_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sasc_api.domain.Sala;
@Repository
public interface SalaRepository extends JpaRepository<Sala, String> {

}
