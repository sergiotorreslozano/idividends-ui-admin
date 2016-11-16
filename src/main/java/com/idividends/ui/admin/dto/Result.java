package com.idividends.ui.admin.dto;

import java.util.List;

/**
 * Wrapper to get the list of stocks remotely
 * 
 * @author sergio.torres.lozano
 *
 */
public class Result {

	/**
	 * the list of stocks
	 */
	private List<StockDto> stocks;

	/**
	 * 
	 */
	public Result() {
		super();
	}

	/**
	 * @param stocks
	 */
	public Result(List<StockDto> stocks) {
		super();
		this.stocks = stocks;
	}

	/**
	 * @return the stocks
	 */
	public List<StockDto> getStocks() {
		return stocks;
	}

}
