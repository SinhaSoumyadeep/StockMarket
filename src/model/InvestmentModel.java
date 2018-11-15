package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;
import transferable.PortfolioTransferable;
import utility.DateUtility;


public class InvestmentModel implements InvestmentModelInterface,Serializable {
  private static final long serialVersionUID = 6033262243162979644L;
  private HashMap<String, IPortfolio> listOfPortfolio;

  public InvestmentModel(){
    listOfPortfolio = new HashMap<>();
  }

  public void buyStocks(String ticker, String timeStamp, Integer noOfShares, String portfolioName)
          throws IllegalArgumentException, ParseException {

    checkBuyStocksParameters(ticker,timeStamp,noOfShares,portfolioName);

    DateUtility checkDate = new DateUtility();

    if(!checkDate.isWeekDay(timeStamp)){
      throw new IllegalArgumentException("Stock Market Closed! It's a Weekend!");
    }

    IStockMarketSimulation stockMarket = StockMarketSimulation.getInstance();
    Stock s = stockMarket.buyStock(ticker,timeStamp,noOfShares);
    if(listOfPortfolio.containsKey(portfolioName.trim())){
      listOfPortfolio.get(portfolioName.trim()).addStocksToPortfolio(s);
    }
    else{
      IPortfolio p = new Portfolio();
      p.addStocksToPortfolio(s);
      listOfPortfolio.put(portfolioName.trim(),p);
    }


  }

  public PortfolioTransferable evaluatePortfolio(String portfolioName, String timestamp)
          throws IllegalArgumentException{

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

  private void checkBuyStocksParameters(String ticker, String timeStamp, Integer noOfShares,
                                        String portfolioName) throws ParseException {


    if( ticker == null || timeStamp == null || noOfShares == null || portfolioName == null){
      throw new IllegalArgumentException("Ticker or Timestamp or NoOfShares or Portfolio " +
              "Name cannot be Null.");
    }

    if(ticker.equals("") || timeStamp.equals("") || portfolioName.equals("")){
      throw new IllegalArgumentException("Ticker or Timestamp or Portfolio Name cannot be Empty.");
    }

    if(noOfShares <= 0){
      throw new IllegalArgumentException("Number of Shares cannot be 0 or negative.");
    }

    DateFormat df = new SimpleDateFormat("yyyy-MM-dddd");
    Date passedDate = df.parse(timeStamp);
    Date todayDate = new Date();

    if(passedDate.compareTo(todayDate) > 0){
      throw new IllegalArgumentException("Date passed is in the future.");
    }

  }






}
