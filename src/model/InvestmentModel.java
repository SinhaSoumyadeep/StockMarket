package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import service.StockMarketSimulation;
import transferable.PortfolioTransferable;
import utility.DateUtility;


public class InvestmentModel implements InvestMentModelInterface,Serializable {
  private static final long serialVersionUID = 6033262243162979644L;
  private HashMap<String, Portfolio> listOfPortfolio;

  public InvestmentModel(){
    listOfPortfolio = new HashMap<>();
  }

  public void buyStocks(String ticker, String timeStamp, Integer noOfShares, String portfolioName) throws IllegalArgumentException{

    DateUtility checkDate = new DateUtility();

    if(!checkDate.isWeekDay(timeStamp)){
      throw new IllegalArgumentException("Stock Market Closed! It's a Weekend!");
    }

    StockMarketSimulation stockMarket = StockMarketSimulation.getInstance();
    Stock s = stockMarket.buyStock(ticker,timeStamp,noOfShares);
    if(listOfPortfolio.containsKey(portfolioName.trim())){
      listOfPortfolio.get(portfolioName.trim()).addStocksToPortfolio(s);
    }
    else{
      Portfolio p = new Portfolio();
      p.addStocksToPortfolio(s);
      listOfPortfolio.put(portfolioName.trim(),p);
    }


  }

  public PortfolioTransferable evaluatePortfolio(String portfolioName, String timestamp) throws IllegalArgumentException{

    if(listOfPortfolio.containsKey(portfolioName)){
      PortfolioTransferable statement = listOfPortfolio.get(portfolioName).valuationForPortfolio(timestamp);
      return statement;
    }
    else{
      throw new IllegalArgumentException("Portfolio does not exist.");
    }

  }

  public List<String> getPortfolioNames()
  {
    return new ArrayList<>(listOfPortfolio.keySet());
  }

  public void createNewPortfolio(String portfolioName) throws IllegalArgumentException
  {
    if(!listOfPortfolio.containsKey(portfolioName)){
      listOfPortfolio.put(portfolioName, new Portfolio());
    }
    else {
      throw new IllegalArgumentException("Portfolio already exists!");// handled in controller.
    }
  }





}
