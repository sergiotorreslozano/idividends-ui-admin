package com.idividends.ui.admin.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestOperations;

@Configuration
public class RestConfiguration {

	private final static String REMOTE_USER = "REMOTE_USER";
	private final static String REMOTE_PASSWORD = "REMOTE_PASSWORD";

	@Bean
	public RestOperations restTemplate(RestTemplateBuilder builder) {
		return builder.basicAuthorization(System.getenv(REMOTE_USER), System.getenv(REMOTE_PASSWORD)).build();
	}
}
