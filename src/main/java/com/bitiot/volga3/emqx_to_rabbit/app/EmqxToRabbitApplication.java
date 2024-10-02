package com.bitiot.volga3.emqx_to_rabbit.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class EmqxToRabbitApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmqxToRabbitApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
