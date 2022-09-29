package com.java.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.MessageDto;
import com.java.service.MessageService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class HelloController {

	@Autowired
	private MessageService messageService;
	
	@GetMapping("/hello")
	public MessageDto sayHello(@RequestParam String user) {
		log.info("test log info : "+  user);
		
		MessageDto messageDto = messageService.getSubscriptionMessage(user);
		log.debug("test debug log :"+messageDto.toString());
		return messageDto;
	}
	
}
