package model;

import java.text.ParseException;

public interface InvestmentStrategyInterface {
  void exceuteStrategyOnPortfolio(IPortfolio portfolio, InvestModelInterfaceNew im, String timestamp) throws ParseException;
}
