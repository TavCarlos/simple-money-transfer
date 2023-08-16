package com.transferenciasimplificado.dtos;

import java.math.BigDecimal;

import com.transferenciasimplificado.domain.UserType;

public record UserDTO(String fristName, String lastName, String document, String email, String password, BigDecimal balance, UserType roleUser) {

}
