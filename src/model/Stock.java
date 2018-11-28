package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A single share of the stock represents fractional ownership of the corporation in proportion to
 * the total number of shares. This class is used to represents a stock. This is a immutable class
 * which means that once the stock has been initialized, the object cannot be mutated. This class
 * has been made immutable to make it more secure.
 */
public final class Stock implements Serializable {
  private final String ticker;
  private final String totalPrice;
  private final Double numberOfshares;
  private final List<Transaction> stockHistory;


  /**
   * This is a constructor that is used to instantiate the stock class object.
   *
   * @param ticker         the ticker for the stock.
   * @param timeStamp      time stamp of the stock.
   * @param buyingPrice    the buying price of the stock.
   * @param numberOfshares he number of shares bought.
   */
  public Stock(String ticker, String timeStamp, String buyingPrice, Double numberOfshares, String commission) {
    this.ticker = ticker;
    this.numberOfshares = numberOfshares;
    this.totalPrice = buyingPrice;
    this.stockHistory = new ArrayList<>();
    this.stockHistory.add(new Transaction(ticker, timeStamp, buyingPrice, numberOfshares, commission));
  }

  /**
   * This is overloaded constructor that is used to instantiate the stock class object.
   *
   * @param ticker         the ticker for the stock.
   * @param totalPrice     the total price of the stock.
   * @param stockHistory   the transaction history
   * @param numberOfshares the number of shares bought.
   */
  public Stock(String ticker, String totalPrice, List<Transaction> stockHistory, Double numberOfshares) {
    this.ticker = ticker;
    this.totalPrice = totalPrice;
    this.stockHistory = stockHistory;
    this.numberOfshares = numberOfshares;
  }

  /**
   * This method is used to get the total price of the stock.
   *
   * @return total price in string.
   */
  public String getTotalPrice() {
    return totalPrice;
  }


  /**
   * The method is used to get the number of shares for a particular size.
   *
   * @return total number of share.
   */
  public Double getNumberOfshares() {
    return numberOfshares;
  }

  /**
   * This method is used to get the transaction history.
   *
   * @return the list of transaction.
   */
  public List<Transaction> getStockHistory() {
    return new ArrayList<>(stockHistory);
  }

  /**
   * This method is used to get the ticker of the stock.
   *
   * @return the ticker symbol
   */
  public String getTicker() {
    return ticker;
  }


  public String toString() {
    return "[ " + ticker + " : " + totalPrice + " : " + numberOfshares + " ]";
  }


}
