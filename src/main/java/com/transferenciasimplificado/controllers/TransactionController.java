package com.transferenciasimplificado.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transferenciasimplificado.domain.transaction.Transaction;
import com.transferenciasimplificado.dtos.TransactionDTO;
import com.transferenciasimplificado.services.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping
	public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
		Transaction newTransaction = transactionService.createTransaction(transaction);
		return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Transaction>> getAllTransactions(){
		List<Transaction> transactions = transactionService.getAllTransactions();
		return new ResponseEntity<>(transactions, HttpStatus.OK);
	}
}
