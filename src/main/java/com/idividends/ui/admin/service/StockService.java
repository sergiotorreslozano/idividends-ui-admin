package com.idividends.ui.admin.service;

import java.util.List;

import com.idividends.ui.admin.dto.StockDto;

public interface StockService {

	List<StockDto> findStocks();

	List<StockDto> findStocks(String symbol);

	StockDto findStock(String symbol);

	List<StockDto> save(StockDto stock);

	List<StockDto> delete(StockDto stock);

	List<StockDto> reloadData();

}
