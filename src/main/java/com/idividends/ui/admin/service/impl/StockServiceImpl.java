package com.idividends.ui.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idividends.ui.admin.client.Client;
import com.idividends.ui.admin.dto.StockDto;
import com.idividends.ui.admin.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private Client client;

	public List<StockDto> findStocks() {
		return client.findAll();
	}

	public List<StockDto> findStocks(String symbol) {
		List<StockDto> aux = new ArrayList<>();
		StockDto stock = findStock(symbol);
		aux.add(stock);
		return aux;
	}

	public List<StockDto> save(StockDto stock) {
		client.save(stock);
		return client.findAll();
	}

	public List<StockDto> delete(StockDto stock) {
		client.delete(stock);
		return client.findAll();
	}

	@Override
	public StockDto findStock(String symbol) {
		return client.findOne(symbol);
	}

	@Override
	public List<StockDto> reloadData() {
		client.reloaData();
		return client.findAll();
	}

}
