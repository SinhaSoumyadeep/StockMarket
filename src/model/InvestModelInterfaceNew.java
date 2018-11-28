package model;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface InvestModelInterfaceNew extends InvestmentModelInterface{
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String, Double> weights,String timeStamp,String commission) throws ParseException;
  public void investStocks(String portfolioName, Double fixedAmount,String timeStamp,String commission) throws ParseException;
  public void registerStrategy(InvestmentStrategyInterface strategy, String portfolioName,  HashMap<String, Double> weights);
  public HashMap<String, Double> viewWeights(String portfolioName);
  public List<String> getStocksInPortfolio(String portfolioName);
}
