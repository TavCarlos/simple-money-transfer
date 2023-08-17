package com.transferenciasimplificado.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transferenciasimplificado.domain.User;
import com.transferenciasimplificado.domain.transaction.Transaction;
import com.transferenciasimplificado.dtos.TransactionDTO;
import com.transferenciasimplificado.repositories.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	private TransactionRepository repository;
	
	
	public Transaction createTransaction(TransactionDTO transaction) {
		User sender = userService.findById(transaction.senderId());
		User receiver = userService.findById(transaction.receiverId());
		
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
		
//		notificationService.sendNotification(sender, "Transação realizada com sucesso");
//		notificationService.sendNotification(receiver, "Transação recebida com sucesso");
		
		return newTransaction;
	}
	
	
	public List<Transaction> getAllTransactions(){
		List<Transaction> transactions = repository.findAll();
		return transactions;
	}
}
