package com.transferenciasimplificado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.transferenciasimplificado.domain.User;
import com.transferenciasimplificado.dtos.NotificationDTO;
import com.transferenciasimplificado.services.exceptions.NotificationNotSentException;

@Service
public class NotificationService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${mock.notify}")
	private String mockNofity;
	
	public void sendNotification(User user, String message) {
		String email = user.getEmail();
		NotificationDTO notificationRequest = new NotificationDTO(email, message);
		
		try {
			ResponseEntity<String> notificationResponse = 
					restTemplate.postForEntity(mockNofity, notificationRequest, String.class);

			if(notificationResponse.getStatusCode() != HttpStatus.OK) {
				throw new NotificationNotSentException("Transação realizada com sucesso, entretanto não foi possível enviar a notificação por email.");
			}
			
		} catch (ResourceAccessException err) {
			throw new NotificationNotSentException("Transação realizada com sucesso, entretanto não foi possível enviar a notificação por email.");
		}
	}
}
