package transferable;

/**
 * This is a class which was created to transfer the details of stock to the controller. This class
 * provides details of the stock such as the cost basis and total value of the stock, total
 * investment, total return, current price of the stock etc. .
 */
public class StockTransferable {
  private String ticker;
  private Double totalNumberOfShares;
  private Double currentPrice;
  private Double totalValue;
  private Double totalInvestment;
  private Double totalReturn;

  /**
   * Gets ticker.
   *
   * @return the ticker
   */
  public String getTicker() {
    return ticker;
  }

  /**
   * Sets ticker.
   *
   * @param ticker the ticker
   */
  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  /**
   * Gets total number of shares.
   *
   * @return the total number of shares
   */
  public Double getTotalNumberOfShares() {
    return totalNumberOfShares;
  }

  /**
   * Sets total number of shares.
   *
   * @param totalNumberOfShares the total number of shares
   */
  public void setTotalNumberOfShares(Double totalNumberOfShares) {
    this.totalNumberOfShares = totalNumberOfShares;
  }

  /**
   * Gets current price.
   *
   * @return the current price
   */
  public Double getCurrentPrice() {
    return currentPrice;
  }

  /**
   * Sets current price.
   *
   * @param currentPrice the current price
   */
  public void setCurrentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
  }

  /**
   * Gets total value.
   *
   * @return the total value
   */
  public Double getTotalValue() {
    return totalValue;
  }

  /**
   * Sets total value.
   *
   * @param totalValue the total value
   */
  public void setTotalValue(Double totalValue) {
    this.totalValue = totalValue;
  }

  /**
   * Gets total investment.
   *
   * @return the total investment
   */
  public Double getTotalInvestment() {
    return totalInvestment;
  }

  /**
   * Sets total investment.
   *
   * @param totalInvestment the total investment
   */
  public void setTotalInvestment(Double totalInvestment) {
    this.totalInvestment = totalInvestment;
  }

  /**
   * Gets total return.
   *
   * @return the total return
   */
  public Double getTotalReturn() {
    return totalReturn;
  }

  /**
   * Sets total return.
   *
   * @param totalReturn the total return
   */
  public void setTotalReturn(Double totalReturn) {
    this.totalReturn = totalReturn;
  }
}
