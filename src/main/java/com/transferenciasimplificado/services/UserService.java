package com.transferenciasimplificado.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transferenciasimplificado.domain.User;
import com.transferenciasimplificado.domain.UserType;
import com.transferenciasimplificado.dtos.UserDTO;
import com.transferenciasimplificado.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception {
		
		if(sender.getRoleUser() == UserType.MERCHANT) {
			throw new Exception("Usuário do tipo logista não está autorizado a realizar transações");
		}
	
		if(sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo indisponível");
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

	public User findById(Long id) throws Exception{
		return  repository.findById(id).orElseThrow(() -> new Exception("Usuário não econtrado"));
	}
	
	public void saveUser(User user) {
		repository.save(user);
	}
}
