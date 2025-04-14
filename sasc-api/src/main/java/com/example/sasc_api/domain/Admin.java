package com.example.sasc_api.domain;

import java.util.Objects;

import com.example.sasc_api.domain.Enum.AcessLevels;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;


    @Enumerated(EnumType.STRING)
    private AcessLevels acessLevels = AcessLevels.ADMIN;

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), acessLevels);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Admin))
            return false;
        if (!super.equals(obj))
            return false;
        Admin other = (Admin) obj;
        return acessLevels == other.acessLevels;
    }

}
