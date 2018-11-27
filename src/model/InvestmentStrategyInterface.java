package model;

import java.text.ParseException;
import java.util.HashMap;

public interface InvestmentStrategyInterface {
  void exceuteStrategyOnPortfolio(String portfolioName, InvestModelInterfaceNew im, String timestamp, HashMap<String, Double> weights) throws ParseException;
}
