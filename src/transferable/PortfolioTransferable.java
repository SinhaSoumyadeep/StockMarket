package transferable;

import java.util.List;

/**
 * This is a class which was created to transfer the details of portfolio to the controller. This
 * class provides details of the portfolio such as the cost basis and total value of the portfolio.
 */
public class PortfolioTransferable {
  private Double totalInvestment;
  private String timeStamp;
  private Double totalValue;
  private List<StockTransferable> stocks;

  /**
   * This method returns the total investment for a portfolio.
   *
   * @return total investment
   */
  public Double getTotalInvestment() {
    return totalInvestment;
  }

  /**
   * This method sets the total investment.
   *
   * @param totalInvestment total investment.
   */
  public void setTotalInvestment(Double totalInvestment) {
    this.totalInvestment = totalInvestment;
  }

  /**
   * This method is used to get the time stamp.
   *
   * @return time stamp as a string.
   */
  public String getTimeStamp() {
    return timeStamp;
  }

  /**
   * This method is used to set the time stamp.
   *
   * @param timeStamp time stamp.
   */
  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  /**
   * This method is used to get the total value of the portfolio.
   *
   * @return total value.
   */
  public Double getTotalValue() {
    return totalValue;
  }

  /**
   * This is used to set the total value.
   *
   * @param totalValue total value.
   */
  public void setTotalValue(Double totalValue) {
    this.totalValue = totalValue;
  }

  /**
   * This method is used to get the list of stock Transferable object.
   *
   * @return list of stock Transferable object.
   */
  public List<StockTransferable> getStocks() {
    return stocks;
  }

  /**
   * This method is used to setthe list of stock Transferable object.
   *
   * @param stocks list of stock Transferable object.
   */
  public void setStocks(List<StockTransferable> stocks) {
    this.stocks = stocks;
  }


}
