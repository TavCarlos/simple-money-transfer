package com.transferenciasimplificado.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.transferenciasimplificado.domain.User;
import com.transferenciasimplificado.domain.transaction.Transaction;
import com.transferenciasimplificado.dtos.AuthorizationDTO;
import com.transferenciasimplificado.dtos.TransactionDTO;
import com.transferenciasimplificado.repositories.TransactionRepository;
import com.transferenciasimplificado.services.exceptions.NotAuthorizedException;

@Service
public class TransactionService {

	@Autowired
	private UserService userService;
	
	@Autowired
 	private NotificationService notificationService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${authorize.transaction}")
	private String authorizeTransactionURL;
	
	
	public Transaction createTransaction(TransactionDTO transaction) {
		User sender = userService.findById(transaction.senderId());
		User receiver = userService.findById(transaction.receiverId());
		
		userService.validateTransaction(sender, transaction.value());
		
		if(!this.isAuthorized()) {
			throw new NotAuthorizedException("Transação não autorizada.");
		}

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
		
		notificationService.sendNotification(sender, "Transação realizada com sucesso");
		notificationService.sendNotification(receiver, "Transação recebida com sucesso");
		
		return newTransaction;
	}
	
	private boolean isAuthorized() {
		ResponseEntity<AuthorizationDTO> authorizationResponse = restTemplate
				.getForEntity(authorizeTransactionURL, AuthorizationDTO.class);
		
		if(authorizationResponse.getStatusCode() == HttpStatus.OK) {
			String message = authorizationResponse.getBody().message();
			return "Autorizado".equalsIgnoreCase(message);
		}
		return false;
	}
	
	public List<Transaction> getAllTransactions(){
		List<Transaction> transactions = repository.findAll();
		return transactions;
	}
}
