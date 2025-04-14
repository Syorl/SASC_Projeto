package com.example.sasc_api.dto;


import com.example.sasc_api.domain.Enum.Setor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioRegister {
    private String email;
    private String password;
    private Setor setor;
}
