package com.example.sasc_api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.example.sasc_api.domain.Enum.AcessLevels;
import com.example.sasc_api.domain.Enum.Setor;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private AcessLevels acessLevels = AcessLevels.USER;

    @Enumerated(EnumType.STRING)
    private Setor setor;

    @OneToMany
    @JsonBackReference
    private List<Agendamento> agendamentos = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), acessLevels);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj) || getClass() != obj.getClass())
            return false;
        Funcionario funcionario = (Funcionario) obj;
        return acessLevels == funcionario.acessLevels;
    }

    public void addAgendamento(Agendamento agendamento) {
        this.agendamentos.add(agendamento);
    }

}
