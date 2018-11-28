package model;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public interface InvestModelInterfaceNew extends InvestmentModelInterface {
  /**
   * this method Invests a fixed amount into an existing portfolio containing multiple stocks, using
   * a specified weight for each stock in the portfolio.
   *
   * @param portfolioName portfolio name
   * @param fixedAmount   fixed amount trying to invest.
   * @param weights       the weights for each stocks in portfolio.
   * @param timeStamp     the timestamp on which stocks are to be bought.
   * @param commission    the commission fee.
   * @throws ParseException if timestamp is invalid.
   */
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String, Double> weights, String timeStamp, String commission) throws ParseException;

  /**
   * this method Invests a more convenient way to use equal weights for all stocks in the portfolio
   * as a preset.
   *
   * @param portfolioName portfolio name
   * @param fixedAmount   fixed amount trying to invest.
   * @param timeStamp     the timestamp on which stocks are to be bought.
   * @param commission    the commission fee.
   * @throws ParseException if timestamp is invalid.
   */
  public void investStocks(String portfolioName, Double fixedAmount, String timeStamp, String commission) throws ParseException;


  /**
   * This method is passed an investment strategy which is executed on the portfolio name passed.
   *
   * @param strategy      the Investment strategy.
   * @param portfolioName the portfolio name.
   * @param weights       the fixed weights for which stocks in portfolio is to be executed.
   */
  public void registerStrategy(InvestmentStrategyInterface strategy, String portfolioName, HashMap<String, Double> weights);

  /**
   * This method is used to check the weights assigned to a partcular portfolio.
   *
   * @param portfolioName the portfolio name
   * @return a copy of the weights that were assigned to the portfolio.
   */
  public HashMap<String, Double> viewWeights(String portfolioName);

  /**
   * this method returns a copy of the stocks that are in a particular portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return the copy of the stock names.
   */
  public List<String> getStocksInPortfolio(String portfolioName);

  /**
   * this method returns the latest date on which a stock was purchased on a particular portfolio.
   *
   * @param portfolioName the portfolio name
   * @return transaction date.
   */
  String getLatestInvestmentDateForPortfolio(String portfolioName);
}
