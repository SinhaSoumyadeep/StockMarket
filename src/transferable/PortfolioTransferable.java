package transferable;

import java.util.List;

public class PortfolioTransferable {
  private Double totalInvestment;
  private String timeStamp;
  private Double totalValue;
  private List<StockTransferable> stocks;

  public Double getTotalInvestment() {
    return totalInvestment;
  }

  public void setTotalInvestment(Double totalInvestment) {
    this.totalInvestment = totalInvestment;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public Double getTotalValue() {
    return totalValue;
  }

  public void setTotalValue(Double totalValue) {
    this.totalValue = totalValue;
  }

  public List<StockTransferable> getStocks() {
    return stocks;
  }

  public void setStocks(List<StockTransferable> stocks) {
    this.stocks = stocks;
  }

}
