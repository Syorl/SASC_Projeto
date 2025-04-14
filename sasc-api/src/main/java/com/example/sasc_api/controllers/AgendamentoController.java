package com.example.sasc_api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sasc_api.domain.Agendamento;
import com.example.sasc_api.services.AgendamentoService;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody Agendamento agendamento) {
        Agendamento novoAgendamento = agendamentoService.save(agendamento);
        return ResponseEntity.ok(novoAgendamento);
    }


    @GetMapping("/ativos")
    public ResponseEntity<List<Agendamento>> listarAgendamentosAtivos() {
        return ResponseEntity.ok(agendamentoService.findAll());
    }


}
