package com.transferenciasimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferenciasimplificado.domain.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	Optional<Transaction> findTransactionById(Long id);
}
