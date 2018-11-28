package model;

import java.io.Serializable;

import utility.DateUtility;

/**
 * This class represents the each transactions of the user while purchasing stock and selling stock.
 * This class has been made immutable.
 */
public final class Transaction implements Serializable, Comparable {

  private final String ticker;
  private final String timeStamp;
  private final String buyingPrice;
  private final Double noOfShares;
  private final String commission;

  /**
   * This constructor is used to instantiate an object of  Transaction.
   *
   * @param ticker      the ticker of the stock.
   * @param timeStamp   timestamp of the stock.
   * @param buyingPrice buying price of the stock.
   * @param noOfShares  number of shares bought in one transaction.
   * @param commission  the commission
   */
  public Transaction(String ticker, String timeStamp, String buyingPrice, Double noOfShares, String commission) {
    this.ticker = ticker;
    this.timeStamp = timeStamp;
    this.buyingPrice = buyingPrice;
    this.noOfShares = noOfShares;
    this.commission = commission;
  }

  /**
   * This method is used to get the ticker of the stock.
   *
   * @return the ticker symbol
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * This method returns the time stamp on which the stock was purchased or sold.
   *
   * @return time stamp.
   */
  public String getTimeStamp() {
    return timeStamp;
  }

  /**
   * This method returns the price on which the stock was bought.
   *
   * @return price of the stock.
   */
  public String getBuyingPrice() {
    return buyingPrice;
  }

  /**
   * The method is used to get the number of shares for a particular size.
   *
   * @return total number of share.
   */
  public Double getNoOfShares() {
    return noOfShares;
  }


  /**
   * Gets commission.
   *
   * @return commission
   */
  public String getCommission() {
    return commission;
  }


  /**
   * compares the dates of transactions.
   *
   * @param o the object passed.
   * @return an integer that determines if the date is smaller or bigger.
   */
  @Override
  public int compareTo(Object o) {
    DateUtility du = new DateUtility();
    Transaction t = (Transaction) o;

    return du.stringToDateConverter(this.timeStamp).compareTo(du.stringToDateConverter(t.timeStamp));


  }
}
