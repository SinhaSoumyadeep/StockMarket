package model;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface InvestModelInterfaceNew extends InvestmentModelInterface{
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String, Double> weights,String timeStamp) throws ParseException;
  public void investStocks(String portfolioName, Double fixedAmount,String timeStamp) throws ParseException;
  public void registerStrategy(InvestmentStrategyInterface strategy, String portfolioName);
  public HashMap<String, Double> viewWeights(String portfolioName);
}
