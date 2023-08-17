package com.transferenciasimplificado.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.transferenciasimplificado.services.exceptions.InsufficientBalanceException;
import com.transferenciasimplificado.services.exceptions.NotificationNotSentException;
import com.transferenciasimplificado.services.exceptions.UserNotAuthorizedException;
import com.transferenciasimplificado.services.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<StandardError> userNotfound(UserNotFoundException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.NOT_FOUND.value());
		err.setError("resource not found");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	@ExceptionHandler(UserNotAuthorizedException.class)
	public ResponseEntity<StandardError> UserNotAuthorizedException(UserNotAuthorizedException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.FORBIDDEN.value());
		err.setError("user not authorized");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	@ExceptionHandler(NotificationNotSentException.class)
	public ResponseEntity<StandardError> NotificationNotSent(NotificationNotSentException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		err.setError("notification sending error");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<StandardError> insufficientBalance(InsufficientBalanceException e, HttpServletRequest request){
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_REQUEST.value());
		err.setError("insufficient balance");
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
}
