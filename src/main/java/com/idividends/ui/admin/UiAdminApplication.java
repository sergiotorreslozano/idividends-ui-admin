package com.idividends.ui.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class)
public class UiAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(UiAdminApplication.class, args);
	}
}
