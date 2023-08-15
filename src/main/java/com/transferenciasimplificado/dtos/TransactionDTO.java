package com.transferenciasimplificado.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId, LocalDateTime timestamp) {

}
