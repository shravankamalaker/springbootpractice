package com.java.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.java.dto.MessageDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class lombokPracticeTest {

	@Autowired
	private TestRestTemplate  restTemplate;
	
	@Test
	@DisplayName("/hello rest api user" )
	void testMessage() {
		String user =  "Shravan Kamalaker";
		
		URI  targetURL = UriComponentsBuilder.fromUriString("/hello")
				.queryParam("user", user)
				.build()
				.encode()
				.toUri();
		
		MessageDto messageDto = this.restTemplate.getForObject(targetURL, MessageDto.class);
		assertEquals("Hello "+user+" !! Thanks for the subscription.", messageDto.getMessage());
		
	}
}
