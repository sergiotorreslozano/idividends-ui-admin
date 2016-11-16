package com.idividends.ui.admin.client;

import java.util.List;

import com.idividends.ui.admin.dto.StockDto;

public interface Client {

	/**
	 * finds all the stocks
	 * 
	 * @return a list of Stocks
	 */
	List<StockDto> findAll();

	/**
	 * Finds one stock
	 * 
	 * @param symbol
	 *            the symbol to search for
	 * @return a Stock
	 */
	StockDto findOne(String symbol);

	/**
	 * Saves or update a Store
	 * 
	 * @param stock
	 *            the stock to save
	 */
	void save(StockDto stock);

	/**
	 * Deletes a stock
	 * 
	 * @param stock
	 *            the stock to delete
	 */
	void delete(StockDto stock);

	/**
	 * reload all the data
	 */
	void reloaData();
}
