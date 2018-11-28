package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;
import transferable.PortfolioTransferable;
import transferable.StockTransferable;
import utility.DateUtility;


/**
 * This class is the concrete implementation of the IPortfolio interface.
 * <p>A portfolio of stocks is simply a collection of stocks (e.g. 10 shares of company X, 10
 * shares of company Y, etc.). The total value of a portfolio is then the sum of the values of its
 * individual holdings. Thus the value of a portfolio, much like individual stocks, also changes
 * with time. As an example, we hear about the SandP 500, Dow Jones Industrial and NASDAQ in the
 * news, quoted to gauge the health of the overall stock market. These three indices are nothing
 * more than portfolios of stocks of specific companies, which have been found to be reasonable
 * barometers of the health of the overall stock market and economy.</p>
 *
 * This class allows various operations on portfolio objects such as adding stocks to the portfolio,
 * and calculate valuation of the portfolio.
 */
public class Portfolio implements Serializable, IPortfolio {
  private HashMap<String, Stock> portfolio; //<Ticker,Stock>
  private String latestDate;

  /**
   * This constructor instantiates a Portfolio object.
   */
  public Portfolio() {
    portfolio = new HashMap<String, Stock>();
    latestDate = "1000-01-01";
  }

  /**
   * This is used to add stocks to a portfolio. This method takes in a new stock and adds theh new
   * stock to the portfolio.
   */
  @Override
  public void addStocksToPortfolio(Stock newStock) {
    DateUtility du = new DateUtility();

    if (portfolio.containsKey(newStock.getTicker())) {
      Stock oldStock = portfolio.get(newStock.getTicker());

      List<Transaction> transactionHistory = new ArrayList<>(oldStock.getStockHistory());
      transactionHistory.addAll(newStock.getStockHistory());
      Collections.sort(transactionHistory);
      if (du.stringToDateConverter(latestDate).isBefore(du.stringToDateConverter(transactionHistory.get(transactionHistory.size() - 1).getTimeStamp()))) {
        latestDate = transactionHistory.get(transactionHistory.size() - 1).getTimeStamp();
      }

      Double totalPrice = oldStock.getNumberOfshares() * Double.parseDouble(oldStock.getTotalPrice()) + newStock.getNumberOfshares() * Double.parseDouble(newStock.getTotalPrice());
      Double totalNumberOfShares = oldStock.getNumberOfshares() + newStock.getNumberOfshares();
      String totalPriceString = String.format("%.4f", totalPrice);

      Stock combinedStock = new Stock(oldStock.getTicker(), totalPriceString, transactionHistory, totalNumberOfShares);

      portfolio.put(combinedStock.getTicker(), combinedStock);

    } else {
      List<Transaction> t = newStock.getStockHistory();
      Collections.sort(t);
      if (du.stringToDateConverter(latestDate).isBefore(du.stringToDateConverter(t.get(t.size() - 1).getTimeStamp()))) {
        latestDate = t.get(t.size() - 1).getTimeStamp();
      }
      portfolio.put(newStock.getTicker(), newStock);

    }

  }


  /**
   * This method returns a Portfolio Transferable object has all the calculation form the Portfolio
   * and eventually return to the controller.
   *
   * @param timestamp the time stamp.
   * @return PortfolioTransferable object that contains all the calculations.
   */
  @Override
  public PortfolioTransferable valuationForPortfolio(String timestamp) {
    List<Transaction> stockForDate = new ArrayList<>();
    DateUtility std = new DateUtility();
    LocalDate timestamp1 = std.stringToDateConverter(timestamp);
    for (Stock s : portfolio.values()) {
      List<Transaction> temp = s.getStockHistory().stream().filter(t -> {
        Transaction transaction = t;
        LocalDate time = std.stringToDateConverter(transaction.getTimeStamp());
        if (time.isBefore(timestamp1) || time.isEqual(timestamp1)) {
          return true;
        } else {
          return false;
        }
      }).collect(Collectors.toList());
      stockForDate.addAll(temp);
    }
    return stockEvaluation(stockForDate, timestamp);


  }

  /**
   * This method will yield the copy of the stock names in the portfolio.
   *
   * @return List of stock names contained in the portfolio.
   */
  @Override
  public List<String> getStockNamesInPortfolio() {
    return new ArrayList<String>(portfolio.keySet());
  }

  /**
   * This method will yield the latest transaction date on which a new stock was purchased.
   *
   * @return the latest time stamp.
   */
  @Override
  public String lastestTransactionDate() {
    return new String(latestDate);
  }

  /**
   * This method is used to calculate the valuation of the individual stocks as well as the
   * portfolio. If the user wants to compare their stock which a date that is a weekand or holiday,
   * then the stock is compared with the price with the date when the stock market was last open. If
   * the user provides a date for which the data is unavailable, then it will inform the user that
   * the data for the particular company is unavailable.
   */
  private PortfolioTransferable stockEvaluation(List<Transaction> filteredTransactionListByDate, String timestamp) {

    PortfolioTransferable pt = new PortfolioTransferable();
    List<StockTransferable> stList = new ArrayList<>();

    List<String> listOfKeys = filteredTransactionListByDate.stream().map(e -> e.getTicker()).distinct().collect(Collectors.toList());
    StringBuffer sb = new StringBuffer();
    IStockMarketSimulation sms = StockMarketSimulation.getInstance();
    Double totalInvestment = 0.0;
    Double totalPortfolioValuation = 0.0;
    for (String key : listOfKeys) {
      Double currentTickerPrice = sms.priceOfAStockAtACertainDate(key, timestamp);

      List<Transaction> temp = filteredTransactionListByDate.stream().filter(t -> t.getTicker().equals(key)).collect(Collectors.toList());
      Double totalNumberOfShareForATicker = 0.0;
      Double totalStockInvestmentForATicker = 0.0;
      for (Transaction t : temp) {
        totalNumberOfShareForATicker = totalNumberOfShareForATicker + t.getNoOfShares();
        Double totalForOneTransaction = (Double.parseDouble(t.getBuyingPrice()) * t.getNoOfShares()) + (Double.parseDouble(t.getCommission()));
        totalStockInvestmentForATicker = totalStockInvestmentForATicker + totalForOneTransaction;

      }
      totalInvestment = totalInvestment + totalStockInvestmentForATicker;
      Double totalValuationOfAStock = totalNumberOfShareForATicker * currentTickerPrice;

      totalPortfolioValuation = totalPortfolioValuation + totalValuationOfAStock;

      StockTransferable st = new StockTransferable();
      st.setTicker(key);
      st.setCurrentPrice(currentTickerPrice);
      st.setTotalInvestment(totalStockInvestmentForATicker);
      st.setTotalNumberOfShares(totalNumberOfShareForATicker);
      st.setTotalReturn((totalValuationOfAStock - totalStockInvestmentForATicker));
      st.setTotalValue(totalValuationOfAStock);
      stList.add(st);
    }

    pt.setStocks(stList);
    pt.setTimeStamp(timestamp);
    pt.setTotalInvestment(totalInvestment);
    pt.setTotalValue(totalPortfolioValuation);

    return pt;

  }


}
