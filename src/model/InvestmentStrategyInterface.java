package model;

import java.text.ParseException;
import java.util.HashMap;

/**
 * This interface is a general representation of the various investment strategies that can be executed on a
 * portfolio. This interface provides a method to execute the strategy on the specified portfolios.
 * <p>
 * Many people look to the stock market for investing because of the promise of big gains. But how
 * does one maximize gain The idea is simple buy at a low price, and sell at a high price. The main
 * challenge is to identify when a stock is at a low price to buy and when it has reached its peak
 * to sell.
 * </p>
 * <p>
 * A traditional investment strategy is to find out information about companies before investing in
 * them. Such an investor would pore through financial statements of a company of interest, looking
 * for debt, revenue, profits, etc. before deciding whether investing in the company is a good idea.
 * Another investment strategy is value-based. This strategy calls for measuring the value of a
 * stock  Value investors also pore through financial statements and financial news, looking for
 * signs of a value buy, or a profitable sell. Some investors scrounge for bad short term news on
 * otherwise well established companies, hoping that the news will temporarily drop stock prices
 * making it a good deal. Such strategies often micro manage, trying to find individual companies to
 * invest. All of them are active styles of investing.
 * </p>
 * <p>
 * Although active investing has the potential to reap big benefits, it requires a constant time
 * commitment and a certain financial acumen. Most investors lack the required dedication and time,
 * due to which their strategy eventually fizzles out.
 * </p>
 * <p>
 * A more automated strategy is to create a portfolio of stocks, and invest money proportionally
 * into stocks in that portfolio. An investor following this strategy may occasionally change the
 * makeup of the portfolio, but spends far less time monitoring the market. This is an example of
 * passive style of investing. While this is a simpler strategy it has the potential of missing
 * stock market fluctuations that could produce more dramatic gains.
 * </p>
 *
 */
public interface InvestmentStrategyInterface {
  /**
   * This method takes in the portfolio name, model, time stamp and the weights to execute the
   * strategy on the portfolio passed.
   *
   * @param portfolioName the portfolio name of the portfolio to execute the strategy on.
   * @param im            the Investment model new object.
   * @param timestamp     time stamp till which the strategy needs to be executed.
   * @param weights       the fixed weights on which the strategy is to be executed till the end
   *                      date.
   * @throws ParseException if the timestamp is invalid.
   */
  void exceuteStrategyOnPortfolio(String portfolioName, InvestModelInterfaceNew im, String timestamp, HashMap<String, Double> weights) throws ParseException;
}
