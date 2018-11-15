package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class Stock implements Serializable {
  private final String ticker;
  private final String totalPrice;
  private final Integer numberOfshares;
  private final List<Transaction> stockHistory;


  public Stock(String ticker, String timeStamp, String buyingPrice, Integer numberOfshares){
    this.ticker = ticker;
    this.numberOfshares = numberOfshares;
    this.totalPrice = buyingPrice;
    this.stockHistory = new ArrayList<>();
    this.stockHistory.add(new Transaction(ticker,timeStamp,buyingPrice,numberOfshares));
  }

  public Stock(String ticker,String totalPrice,List<Transaction> stockHistory, Integer numberOfshares)
  {
    this.ticker = ticker;
    this.totalPrice = totalPrice;
    this.stockHistory = stockHistory;
    this.numberOfshares = numberOfshares;
  }

  public String getTotalPrice() {
    return totalPrice;
  }


  public Integer getNumberOfshares() {
    return numberOfshares;
  }

  public List<Transaction> getStockHistory() {
    return new ArrayList<>(stockHistory);
  }

  public String getTicker() {
    return ticker;
  }


  @Override
  public String toString(){
    return "Ticker: " + this.ticker+" Total Price: "+this.totalPrice+" No of Shares: " + this.numberOfshares+" TransactionHistory: "+this.stockHistory;
  }
}
