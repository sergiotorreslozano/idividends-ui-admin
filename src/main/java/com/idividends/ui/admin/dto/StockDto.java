package com.idividends.ui.admin.dto;

import java.math.BigInteger;

public class StockDto {

	private String symbol;

	private String market;

	private String name;

	private Double lastPrice;

	private Double change;

	private Double changePercent;

	private BigInteger marketCap;

	private Double changeYTD;

	private Double changePercentYTD;

	public StockDto() {

	}

	/**
	 * @param symbol
	 * @param market
	 * @param name
	 */
	public StockDto(String symbol, String market, String name) {
		super();
		this.symbol = symbol;
		this.market = market;
		this.name = name;
	}

	/**
	 * @return the symbol
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol
	 *            the symbol to set
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	/**
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * @param market
	 *            the market to set
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastPrice
	 */
	public Double getLastPrice() {
		return lastPrice;
	}

	/**
	 * @param lastPrice
	 *            the lastPrice to set
	 */
	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	/**
	 * @return the change
	 */
	public Double getChange() {
		return change;
	}

	/**
	 * @param change
	 *            the change to set
	 */
	public void setChange(Double change) {
		this.change = change;
	}

	/**
	 * @return the changePercent
	 */
	public Double getChangePercent() {
		return changePercent;
	}

	/**
	 * @param changePercent
	 *            the changePercent to set
	 */
	public void setChangePercent(Double changePercent) {
		this.changePercent = changePercent;
	}

	/**
	 * @return the marketCap
	 */
	public BigInteger getMarketCap() {
		return marketCap;
	}

	/**
	 * @param marketCap
	 *            the marketCap to set
	 */
	public void setMarketCap(BigInteger marketCap) {
		this.marketCap = marketCap;
	}

	/**
	 * @return the changeYTD
	 */
	public Double getChangeYTD() {
		return changeYTD;
	}

	/**
	 * @param changeYTD
	 *            the changeYTD to set
	 */
	public void setChangeYTD(Double changeYTD) {
		this.changeYTD = changeYTD;
	}

	/**
	 * @return the changePercentYTD
	 */
	public Double getChangePercentYTD() {
		return changePercentYTD;
	}

	/**
	 * @param changePercentYTD
	 *            the changePercentYTD to set
	 */
	public void setChangePercentYTD(Double changePercentYTD) {
		this.changePercentYTD = changePercentYTD;
	}

}
