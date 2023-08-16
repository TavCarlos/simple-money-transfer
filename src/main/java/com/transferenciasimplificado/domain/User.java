package com.transferenciasimplificado.domain;

import java.math.BigDecimal;
import java.util.Objects;

import com.transferenciasimplificado.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "users")
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String fristName;

	private String lastName;
	
	@Column(unique = true)
	private String document;
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserType roleUser;

	public User() {}
	
	public User(String fristName, String lastName, String document, String email, String password, BigDecimal balance, UserType roleUser) {
		super();
		this.fristName = fristName;
		this.lastName = lastName;
		this.document = document;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.roleUser = roleUser;
	}

	public User(UserDTO data) {
		this.fristName = data.fristName();
		this.lastName = data.lastName();
		this.document = data.document();
		this.email = data.email();
		this.password = data.password();
		this.balance = data.balance();
		this.roleUser = data.roleUser();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFristName() {
		return fristName;
	}
	public void setFristName(String fristName) {
		this.fristName = fristName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public UserType getRoleUser() {
		return roleUser;
	}
	public void setRoleUser(UserType roleUser) {
		this.roleUser = roleUser;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
