package service;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

import model.Stock;
import manager.StockMarketServiceManager;
import utility.DateUtility;

/**
 * This Class simulates the Stock market where in it keep a record of the stocks of different
 * companies. It also provides various methods to perform various operations such as buying stocks
 * and get current price of the stoock at a certain date. This class is a singleton class that will
 * be shared by everyone.
 */
public class StockMarketSimulation implements IStockMarketSimulation, Serializable {
  private static final long serialVersionUID = 6033262247182979644L;
  private HashMap<String, String> companyListing;
  private static StockMarketSimulation instance = null;

  /**
   * This is a private constructor.
   */
  private StockMarketSimulation() {
    companyListing = new HashMap<>();
  }

  /**
   * This is a static method that is used to create a single instance of the StockMarket Simulator.
   *
   * @return an Object of StockMarketSimulator.
   */
  public static StockMarketSimulation getInstance() {
    if (instance == null) {
      synchronized (StockMarketSimulation.class) {
        if (instance == null) {
          instance = new StockMarketSimulation();
        }
      }
    }
    return instance;
  }

  /**
   * This method is used to add listing of a company to the stockMarket.
   *
   * @param ticker  ticker of the company.
   * @param listing listing of the company.
   */
  private void addCompanyToListing(String ticker, String listing) {
    companyListing.put(ticker, listing);
  }

  /**
   * This method is used to buy a stock from the stock market.
   *
   * @param ticker     the ticker symbol.
   * @param timeStamp  the time stamp.
   * @param noOfShares number of shares to be purchased.
   * @return the Stock object.
   */
  @Override
  public Stock buyStock(String ticker, String timeStamp, Integer noOfShares) {

    if (companyListing.containsKey(ticker)) {
      String listing = companyListing.get(ticker);
      if (!listing.contains(timeStamp)) {
        throw new IllegalArgumentException("Sorry! cannot buy stock, Market is closed on " + timeStamp);
      }
      String tuple = stockForDate(listing, timeStamp);
      String[] dataValue = tuple.split(",");
      return new Stock(ticker, dataValue[0], dataValue[1], noOfShares);
    } else {
      StockMarketServiceManager stockManager = new StockMarketServiceManager();
      String listing = stockManager.getCompanyListing(ticker);
      if (!listing.contains(timeStamp)) {
        throw new IllegalArgumentException("Sorry! cannot buy stock, Market is closed on " + timeStamp);
      }
      addCompanyToListing(ticker, listing);
      String tuple = stockForDate(listing, timeStamp);
      String[] dataValue = tuple.split(",");
      return new Stock(ticker, dataValue[0], dataValue[1], noOfShares);

    }

  }

  /**
   * This method is used to retrieve the price of the a stock at a certain date.
   *
   * @param ticker    the ticker symbol.
   * @param timeStamp the time stamp for a stock.
   * @return the price of the stock at the timestamp passed.
   */
  @Override
  public Double priceOfAStockAtACertainDate(String ticker, String timeStamp) {
    if (companyListing.containsKey(ticker)) {
      System.out.println("cache!!");
      String listing = companyListing.get(ticker);
      String tuple = stockForDate(listing, timeStamp);
      String[] dataValue = tuple.split(",");
      return Double.parseDouble(dataValue[1]);

    } else {
      System.out.println("api!!");
      StockMarketServiceManager stockManager = new StockMarketServiceManager();
      String listing = stockManager.getCompanyListing(ticker);
      addCompanyToListing(ticker, listing);
      String tuple = stockForDate(listing, timeStamp);
      String[] dataValue = tuple.split(",");
      return Double.parseDouble(dataValue[1]);

    }
  }


  /**
   * This method returns the stock at a certain date.
   *
   * @param listing   listing of the company.
   * @param timeStamp timestamp for which the stock is required.
   * @return the stock as a string.
   */
  private String stockForDate(String listing, String timeStamp) {
    DateUtility du = new DateUtility();
    String last = listing.substring(listing.lastIndexOf("\n")).trim();
    String lastTimeStamp = last.split(",")[0];
    if (du.stringToDateConverter(lastTimeStamp).isAfter(du.stringToDateConverter(timeStamp))) {
      throw new IllegalArgumentException("Data Not Available for This Date!");
    }
    if (listing.contains(timeStamp)) {
      String list = listing.substring(listing.indexOf(timeStamp)).trim();
      list = list.substring(0, list.indexOf("\n"));
      return list;
    } else {
      LocalDate date = new DateUtility().stringToDateConverter(timeStamp);
      date = date.minusDays(1);
      return stockForDate(listing, date.toString());
    }

  }

  public String toString()
  {
    return companyListing.toString();
  }

  private Object readResolve()
  {
    return instance;
  }


}
