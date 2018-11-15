package model;

import java.util.List;

import transferable.PortfolioTransferable;

public interface InvestMentModelInterface {
  public void buyStocks(String ticker, String timeStamp, Integer noOfShares, String portfolioName) throws IllegalArgumentException;
  public PortfolioTransferable evaluatePortfolio(String portfolioName, String timestamp) throws IllegalArgumentException;
  public List<String> getPortfolioNames();
  public void createNewPortfolio(String portfolioName) throws IllegalArgumentException;
}
