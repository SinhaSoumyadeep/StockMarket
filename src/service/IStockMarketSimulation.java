package service;

import model.Stock;

public interface IStockMarketSimulation {

  Stock buyStock(String ticker, String timeStamp, Integer noOfShares);

  Double priceOfAStockAtACertainDate(String ticker, String timeStamp);

}
