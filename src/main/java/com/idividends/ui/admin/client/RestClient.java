package com.idividends.ui.admin.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.idividends.ui.admin.dto.Result;
import com.idividends.ui.admin.dto.StockDto;
import com.idividends.ui.admin.dto.TaskDto;

@Component
public class RestClient implements Client {

	private static final String STOCK_URL = "STOCK_URL";

	@Autowired
	private RestClientDelegate clientDelegate;

	public List<StockDto> findAll() {
		String url = System.getenv(STOCK_URL);
		ResponseEntity<Result> response = (ResponseEntity<Result>) clientDelegate.exchange(url, HttpMethod.GET,
				Result.class);
		return response.getBody().getStocks();
	}

	public StockDto findOne(String symbol) {
		String url = System.getenv(STOCK_URL) + "/" + symbol;
		ResponseEntity<StockDto> response = (ResponseEntity<StockDto>) clientDelegate.exchange(url, HttpMethod.GET,
				StockDto.class);
		return response.getBody();
	}

	public void save(StockDto stock) {
		String url = System.getenv(STOCK_URL);
		clientDelegate.exchange(url, HttpMethod.POST, StockDto.class, stock);
	}

	public void delete(StockDto stock) {
		String url = System.getenv(STOCK_URL) + "/" + stock.getSymbol();
		clientDelegate.exchange(url, HttpMethod.DELETE, StockDto.class, null);
	}

	public void reloaData() {
		String url = System.getenv(STOCK_URL) + "/updateall";
		clientDelegate.exchange(url, HttpMethod.POST, TaskDto.class, null);
	}
	
}
