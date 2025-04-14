package com.example.sasc_api.domain;


import com.example.sasc_api.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Pessoa {
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonIgnore
    private User idUser;


    @NotBlank
    @Size(min = 2, max = 50)
    @Pattern(regexp = "^[A-Z][a-z]*", message =
    "Surname must start with an uppercase letter.")
    private String name;

    @Email
    @Column(unique = true)
    private String email;


    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).*$", message = "Password must contain one uppercase, one lowercase, one number, and one special character.")
    private String password;




}
