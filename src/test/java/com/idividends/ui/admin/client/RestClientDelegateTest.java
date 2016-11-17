package com.idividends.ui.admin.client;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import com.idividends.ui.admin.dto.Result;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({ org.springframework.test.context.web.ServletTestExecutionListener.class,
		org.springframework.test.context.support.DirtiesContextBeforeModesTestExecutionListener.class,
		org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class,
		org.springframework.test.context.support.DirtiesContextTestExecutionListener.class,
		org.springframework.test.context.transaction.TransactionalTestExecutionListener.class,
		org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener.class })
public class RestClientDelegateTest {

	@Autowired
	private RestClientDelegate restClientDelegate;

	private static final String STOCK_URL = "STOCK_URL";

	@Test
	public void exchangeTest(){

		String url = System.getenv(STOCK_URL);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<Result> response = (ResponseEntity<Result>) restClientDelegate.doExchange(url, HttpMethod.GET,
				entity, Result.class);
		assertNotNull(response);
	}

}
