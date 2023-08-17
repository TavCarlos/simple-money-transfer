package com.transferenciasimplificado.services.exceptions;

public class UserNotAuthorizedException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public UserNotAuthorizedException(String msg) {
		super(msg);
	}
}
