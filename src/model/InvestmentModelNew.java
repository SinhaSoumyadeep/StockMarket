package model;

import java.text.ParseException;
import java.util.HashMap;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;

public class InvestmentModelNew extends InvestmentModel implements InvestModelInterfaceNew {


  @Override
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String, Double> weights, String timeStamp) {
    System.out.println(listOfPortfolio.get(portfolioName));
  }

  @Override
  public void investStocks(String portfolioName, Double fixedAmount, String timeStamp) throws ParseException {

    IPortfolio investingPortfolio = this.listOfPortfolio.get(portfolioName);

    Double moneyForEachStock = fixedAmount/investingPortfolio.getStockNamesInPortfolio().size();

    for (String key: investingPortfolio.getStockNamesInPortfolio())
    {
      IStockMarketSimulation stockMarket = StockMarketSimulation.getInstance();
      Double currentStockPrice = stockMarket.priceOfAStockAtACertainDate(key, timeStamp);


      Double partialNumberOfShares = (moneyForEachStock/currentStockPrice);
      Integer wholeShares = partialNumberOfShares.intValue();

      Double remaningAmountInWallet = moneyForEachStock - (wholeShares*currentStockPrice);

      System.out.println("ticker: "+key);
      System.out.println("money available for each stock: "+moneyForEachStock);
      System.out.println("current stock Price: "+currentStockPrice);
      System.out.println("whole shares: "+wholeShares);
      System.out.println("the remaining amount in wallet :"+remaningAmountInWallet);

      buyStocks(key,timeStamp,wholeShares,portfolioName);


    }

  }
}
