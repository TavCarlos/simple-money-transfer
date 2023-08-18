package com.transferenciasimplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transferenciasimplificado.domain.User;
import com.transferenciasimplificado.domain.UserType;
import com.transferenciasimplificado.dtos.UserDTO;
import com.transferenciasimplificado.repositories.UserRepository;
import com.transferenciasimplificado.services.exceptions.InsufficientBalanceException;
import com.transferenciasimplificado.services.exceptions.NotAuthorizedException;
import com.transferenciasimplificado.services.exceptions.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) {
		
		if(sender.getRoleUser() == UserType.MERCHANT) {
			throw new NotAuthorizedException("Usuário do tipo logista não está autorizado a realizar transações");
		}
	
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new InsufficientBalanceException("Saldo indisponível");
		}
	}
	
	public User createUser(UserDTO data) {
		User newUser = new User(data);
		this.saveUser(newUser);
		return newUser;
	}
	
	public List<User> getAllUsers(){
		return repository.findAll();
	}

	public User findById(Long id) {
		return  repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não econtrado"));
	}
	
	public void saveUser(User user) {
		repository.save(user);
	}
}
