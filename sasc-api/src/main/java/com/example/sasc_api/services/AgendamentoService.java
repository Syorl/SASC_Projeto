package com.example.sasc_api.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sasc_api.domain.Agendamento;
import com.example.sasc_api.domain.Enum.Status;
import com.example.sasc_api.repositories.AgendamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public Agendamento save(Agendamento agendamento) {

        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não pode ser nulo.");
        }

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime inicio = agendamento.getDataAgendamento();
        LocalDateTime fim = agendamento.getDataFim();

        if (Duration.between(now, inicio).toMinutes() < 60) {
            throw new IllegalArgumentException("A reserva deve ser feita com pelo menos 1 hora de antecedência.");
        }

        if (Duration.between(inicio, fim).toHours() > 2) {
            throw new IllegalArgumentException("O tempo máximo de reserva é de 2 horas.");
        }

        agendamento.setStatus(Status.ATIVO);
        return agendamentoRepository.save(agendamento);

    }

    public List<Agendamento> findAll() {
        Status status = Status.ATIVO;
        return agendamentoRepository.findByStatus(status);
    }

}
