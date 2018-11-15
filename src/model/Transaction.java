package model;

import java.io.Serializable;

public final class Transaction implements Serializable {

  private final String ticker;
  private final String timeStamp;
  private final String buyingPrice;
  private final Integer noOfShares;

  public String getTicker() {
    return ticker;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public String getBuyingPrice() {
    return buyingPrice;
  }

  public Integer getNoOfShares() {
    return noOfShares;
  }

  public Transaction(String ticker, String timeStamp, String buyingPrice, Integer noOfShares){
    this.ticker = ticker;
    this.timeStamp = timeStamp;
    this.buyingPrice = buyingPrice;
    this.noOfShares = noOfShares;
  }

  public String toString()
  {
    return "Ticker: "+ticker+" TimeStamp: "+timeStamp+" Buying Price: "+buyingPrice + " No Of Shares: "+noOfShares;
  }



}
