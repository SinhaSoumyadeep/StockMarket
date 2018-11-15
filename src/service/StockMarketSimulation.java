package service;


import java.time.LocalDate;
import java.util.HashMap;

import model.Stock;
import manager.StockMarketServiceManager;
import utility.DateUtility;

public class StockMarketSimulation {

  private HashMap<String, String> companyListing;


  private static StockMarketSimulation instance = null;

  private StockMarketSimulation() {
    companyListing = new HashMap<>();
  }

  public static StockMarketSimulation getInstance() {
    if (instance == null) {
      synchronized(StockMarketSimulation.class) {
        if (instance == null) {
          instance = new StockMarketSimulation();
        }
      }
    }
    return instance;
  }

  private void addCompanyToListing(String ticker, String listing)
  {
      companyListing.put(ticker,listing);
  }

  public Stock buyStock(String ticker, String timeStamp, Integer noOfShares)
  {

    if(companyListing.containsKey(ticker))
    {
      //System.out.println(ticker+" using cached version!");

      String listing = companyListing.get(ticker);
      if(!listing.contains(timeStamp)){
        throw new IllegalArgumentException("Sorry! cannot buy stock, Market is closed on "+timeStamp);
      }
      String tuple =  stockForDate(listing,timeStamp);
      String[] dataValue = tuple.split(",");
      return new Stock(ticker,dataValue[0],dataValue[1],noOfShares);

    }
    else{
      //System.out.println(ticker+" using api hit!");
      StockMarketServiceManager stockManager = new StockMarketServiceManager();
      String listing = stockManager.getCompanyListing(ticker);
      if(!listing.contains(timeStamp)){
        throw new IllegalArgumentException("Sorry! cannot buy stock, Market is closed on "+timeStamp);
      }
      addCompanyToListing(ticker,listing);
      String tuple =  stockForDate(listing,timeStamp);
      String[] dataValue = tuple.split(",");
      //timestamp,open,high,low,close,volume
      return new Stock(ticker,dataValue[0],dataValue[1],noOfShares);

    }

  }

  public Double priceOfAStockAtACertainDate(String ticker, String timeStamp){
    if(companyListing.containsKey(ticker))
    {
      String listing = companyListing.get(ticker);
      String tuple =  stockForDate(listing,timeStamp);

      //System.out.println("::::::::::>>"+tuple);
      String[] dataValue = tuple.split(",");
      return  Double.parseDouble(dataValue[1]);

    }
    else{
      StockMarketServiceManager stockManager = new StockMarketServiceManager();
      String listing = stockManager.getCompanyListing(ticker);
      addCompanyToListing(ticker,listing);
      String tuple =  stockForDate(listing,timeStamp);
      String[] dataValue = tuple.split(",");
      return Double.parseDouble(dataValue[1]);

    }
  }




  private String stockForDate(String listing, String timeStamp){

    //System.out.println(listing);
    DateUtility du = new DateUtility();
    String last = listing.substring(listing.lastIndexOf("\n")).trim();
    //System.out.println("the last time stamp is: "+last);

    String lastTimeStamp = last.split(",")[0];

    //System.out.println("the last time stamo dvsgvhjsdgfjsdh: "+lastTimeStamp);

    if(du.stringToDateConverter(lastTimeStamp).isAfter(du.stringToDateConverter(timeStamp))){
      throw new IllegalArgumentException("Data Not Available for This Date!");
    }



    if(listing.contains(timeStamp)) {
      String list = listing.substring(listing.indexOf(timeStamp)).trim();
      list = list.substring(0, list.indexOf("\n"));
      return list;
    }
    else{

      LocalDate date = new DateUtility().stringToDateConverter(timeStamp);

      date = date.minusDays(1);

      return stockForDate(listing,date.toString());
    }

  }

  public void updateListing(String ticker)
  {
    StockMarketServiceManager sm = new StockMarketServiceManager();
    if(!companyListing.containsKey(ticker))
    {
      String listing = sm.getCompanyListing(ticker);
      companyListing.put(ticker,listing);
    }

  }




}
