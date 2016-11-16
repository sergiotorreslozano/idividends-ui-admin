package com.idividends.ui.admin.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import com.idividends.ui.admin.dto.StockDto;

@Component
public class RestClientDelegate {

	@Autowired
	private RestOperations restTemplate;

	public <T> ResponseEntity<T> exchange(String url, HttpMethod method, Class<T> clazz) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		return doExchange(url, method, entity, clazz);
	}

	public <T> ResponseEntity<T> doExchange(String url, HttpMethod method, HttpEntity<?> entity, Class<T> clazz) {
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		return restTemplate.exchange(builder.build().encode().toUri(), method, entity, clazz);
	}

	public ResponseEntity<?> exchange(String url, HttpMethod method, Class<?> clazz, StockDto stock) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<?> entity = new HttpEntity<>(stock, headers);
		return doExchange(url, method, entity, clazz);
	}

}
