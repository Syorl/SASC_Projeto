package com.example.sasc_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sasc_api.domain.Agendamento;
import com.example.sasc_api.domain.Enum.Status;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, String> {

    List<Agendamento> findByStatus(Status status);
    

}
