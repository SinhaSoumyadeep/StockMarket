package transferable;

public class StockTransferable {
  private String ticker;
  private Integer totalNumberOfShares;
  private Double currentPrice;
  private Double totalValue;
  private Double totalInvestment;
  private Double totalReturn;

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public Integer getTotalNumberOfShares() {
    return totalNumberOfShares;
  }

  public void setTotalNumberOfShares(Integer totalNumberOfShares) {
    this.totalNumberOfShares = totalNumberOfShares;
  }

  public Double getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(Double currentPrice) {
    this.currentPrice = currentPrice;
  }

  public Double getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(Double totalValue) {
    this.totalValue = totalValue;
  }

  public Double getTotalInvestment() {
    return totalInvestment;
  }

  public void setTotalInvestment(Double totalInvestment) {
    this.totalInvestment = totalInvestment;
  }

  public Double getTotalReturn() {
    return totalReturn;
  }

  public void setTotalReturn(Double totalReturn) {
    this.totalReturn = totalReturn;
  }
}
