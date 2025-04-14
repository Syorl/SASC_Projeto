package com.example.sasc_api.dto;

import com.example.sasc_api.domain.Enum.AcessLevels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRegister {

    private String email;
    private String password;
    private AcessLevels role;
}
