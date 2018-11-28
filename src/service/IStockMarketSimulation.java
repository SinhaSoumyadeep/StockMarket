package service;

import model.Stock;

/**
 * This Interface simulates the Stock market where in it keep a record of the stocks of different
 * companies.
 */
public interface IStockMarketSimulation {

  /**
   * This method is used to buy a stock from the stock market.
   *
   * @param ticker     the ticker symbol.
   * @param timeStamp  the time stamp.
   * @param noOfShares number of shares to be purchased.
   * @return the Stock object.
   */
  Stock buyStock(String ticker, String timeStamp, Double noOfShares, String commission);

  /**
   * This method is used to retrieve the price of the a stock at a certain date.
   *
   * @param ticker    the ticker symbol.
   * @param timeStamp the time stamp for a stock.
   * @return the price of the stock at the timestamp passed.
   */
  Double priceOfAStockAtACertainDate(String ticker, String timeStamp);

}
