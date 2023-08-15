package com.transferenciasimplificado.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transferenciasimplificado.domain.User;
import com.transferenciasimplificado.domain.transaction.Transaction;
import com.transferenciasimplificado.dtos.TransactionDTO;
import com.transferenciasimplificado.repositories.TransactionRepository;

@Service
public class TransferenceService {

	@Autowired
	UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	
	public void createTransaction(TransactionDTO transaction) throws Exception {
		User sender = userService.findUserById(transaction.senderId());
		User receiver = userService.findUserById(transaction.receiverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReceiver(receiver);
		newTransaction.setTimestamp(LocalDateTime.now());
		
		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		receiver.setBalance(receiver.getBalance().add(transaction.value()));
		
		repository.save(newTransaction);
		userService.saveUser(sender);
		userService.saveUser(receiver);
	}
}
