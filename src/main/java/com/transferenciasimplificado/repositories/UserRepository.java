package com.transferenciasimplificado.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transferenciasimplificado.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByDocument(String document);
	
}