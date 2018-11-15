package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import service.StockMarketSimulation;
import transferable.PortfolioTransferable;
import transferable.StockTransferable;
import utility.DateUtility;


public class Portfolio implements Serializable {
  private HashMap<String, Stock> portfolio; //<Ticker,Stock>

  public Portfolio() {
    portfolio = new HashMap<>();
  }

  public void addStocksToPortfolio(Stock newStock) {
    if (portfolio.containsKey(newStock.getTicker())) {
      Stock oldStock = portfolio.get(newStock.getTicker());

      List<Transaction> transactionHistory = new ArrayList<>(oldStock.getStockHistory());
      transactionHistory.addAll(newStock.getStockHistory());

      Double totalPrice = oldStock.getNumberOfshares() * Double.parseDouble(oldStock.getTotalPrice()) + newStock.getNumberOfshares() * Double.parseDouble(newStock.getTotalPrice());
      Integer totalNumberOfShares = oldStock.getNumberOfshares() + newStock.getNumberOfshares();
      String totalPriceString = String.format("%.4f", totalPrice);

      Stock combinedStock = new Stock(oldStock.getTicker(), totalPriceString, transactionHistory, totalNumberOfShares);

      portfolio.put(combinedStock.getTicker(), combinedStock);

    } else {
      portfolio.put(newStock.getTicker(), newStock);

    }
  }

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

  private PortfolioTransferable stockEvaluation(List<Transaction> filteredTransactionListByDate, String timestamp) {

    PortfolioTransferable pt = new PortfolioTransferable();
    List<StockTransferable> stList = new ArrayList<>();

    List<String> listOfKeys = filteredTransactionListByDate.stream().map(e -> e.getTicker()).distinct().collect(Collectors.toList());
    StringBuffer sb = new StringBuffer();
    StockMarketSimulation sms = StockMarketSimulation.getInstance();
    Double totalInvestment = 0.0;
    Double totalPortfolioValuation = 0.0;
    for (String key : listOfKeys) {
      Double currentTickerPrice = sms.priceOfAStockAtACertainDate(key, timestamp);

      List<Transaction> temp = filteredTransactionListByDate.stream().filter(t -> t.getTicker().equals(key)).collect(Collectors.toList());
      Integer totalNumberOfShareForATicker = 0;
      Double totalStockInvestmentForATicker = 0.0;
      for (Transaction t : temp) {
        totalNumberOfShareForATicker = totalNumberOfShareForATicker + t.getNoOfShares();
        Double totalForOneTransaction = Double.parseDouble(t.getBuyingPrice()) * t.getNoOfShares();
        totalStockInvestmentForATicker = totalStockInvestmentForATicker + totalForOneTransaction;

      }
      totalInvestment = totalInvestment+totalStockInvestmentForATicker;
      Double totalValuationOfAStock = totalNumberOfShareForATicker * currentTickerPrice;

      totalPortfolioValuation = totalPortfolioValuation+totalValuationOfAStock;

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

  @Override
  public String toString() {
    return portfolio.toString();
  }
}
