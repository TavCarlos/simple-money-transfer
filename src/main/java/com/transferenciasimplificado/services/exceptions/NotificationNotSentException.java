package com.transferenciasimplificado.services.exceptions;

public class NotificationNotSentException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NotificationNotSentException(String msg) {
		super(msg);
	}
	
}
