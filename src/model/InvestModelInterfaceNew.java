package model;

import java.text.ParseException;
import java.util.HashMap;

public interface InvestModelInterfaceNew extends InvestmentModelInterface{
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String, Double> weights,String timeStamp);
  public void investStocks(String portfolioName, Double fixedAmount,String timeStamp) throws ParseException;
}
