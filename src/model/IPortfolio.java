package model;

import java.util.List;

import transferable.PortfolioTransferable;

/**
 * <p>A portfolio of stocks is simply a collection of stocks (e.g. 10 shares of company X, 10
 * shares of company Y, etc.). The total value of a portfolio is then the sum of the values of its
 * individual holdings. Thus the value of a portfolio, much like individual stocks, also changes
 * with time. As an example, we hear about the SandP 500, Dow Jones Industrial and NASDAQ in the
 * news, quoted to gauge the health of the overall stock market. These three “indices” are nothing
 * more than portfolios of stocks of specific companies, which have been found to be reasonable
 * barometers of the health of the overall stock market and economy.</p>
 *
 * This interface allows various operations on portfolio objects such as adding stocks to the
 * portfolio, and calculate valuation of the portfolio.
 */
public interface IPortfolio {

  /**
   * This is used to add stocks to a portfolio. This method takes in a new stock and adds theh new
   * stock to the portfolio.
   *
   * @param newStock new stock.
   */
  void addStocksToPortfolio(Stock newStock);

  /**
   * This method returns a Portfolio Transferable object has all the calculation form the Portfolio
   * and eventually return to the controller.
   *
   * @param timestamp the time stamp.
   * @return PortfolioTransferable object that contains all the calculations.
   */
  PortfolioTransferable valuationForPortfolio(String timestamp);

  List<String> getStockNamesInPortfolio();

  public String lastestTransactionDate();
}
