package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;
import transferable.PortfolioTransferable;
import utility.DateUtility;

/**
 * This is the concrete implementation of interface simulates the functionality of stock market and
 * provides users operations to buy stocks, create portfolio and view its compositions. This class
 * can also be serialized.
 *
 * <p>In its simplest form, a stock of a company is simply a part of ownership in that company.
 * Ownership is divided into shares, where a share represents a fraction of the total ownership. For
 * example, Apple has about 5.2B shares. So if you own 100 shares, you own about 1.9𝑥10−6% of the
 * company (and it would be worth about $20800 today). As a shareholder, you are an investor in the
 * company. An investor sends money to the company to buy some of its stock, and gets part ownership
 * in return. The total money invested in the stock (money spent buying it) is called the cost basis
 * of the purchase. The value of the stock on a particular day is the money the investor would
 * receive if he/she sold the stock on that day.</p>
 * <p>
 * Each publicly traded company’s stock is given a unique “ticker symbol” which is used to trade it
 * (for example, Apple Inc. is AAPL, Microsoft is MSFT). The price of stock keeps changing all day
 * depending on how many people want to own that stock versus how many people want to sell their
 * shares. The behavior of a US stock during a day can be understood by its opening price (at 8am
 * EST when the New York Stock Exchange opens for business), its closing price (at 4pm EST when the
 * NYSE closes for regular business), its low and high price (during the day). At any point in time,
 * the total value of an investor’s holdings of a stock is simply the price of the stock at that
 * time, multiplied by the number of shares of that stock that the investor owns.
 * </p>
 * <p>This application helps users who are new to investing to learn about how money could grow, in
 * the style of “virtual gambling”. Similar to virtual gambling, our application will not use real
 * money and investment. Instead it will allow the user to create investment portfolios, try out
 * buying and selling of stock, and various investment strategies to see how they can grow (or
 * shrink) their money with time.</p>
 *
 * <p>this program has the following features:</p>
 * <p>Allow a user to create one or more portfolios and examine its composition.</p>
 * <p>Buy shares of some stock in a portfolio worth a certain amount at a certain date.</p>
 * <p>Determine the total cost basis and total value of a portfolio at a certain date.</p>
 *
 * <p>This interface provides operations that allows user to buy stocks, evaluate portfolios, and
 * create new portfolio.</p>
 *
 * <p>This model will also handle the case when the date entered is in future or weekend</p>
 *
 * <p>Since the application buy stocks from the past and data available is day to day basis hence,
 * closing of market after 4 pm cannot be handled.</p>
 *
 * <p>If the user wants to compare their stock which a date that is a weekand or holiday, then the
 * stock is compared with the price with the date when the stock market was last open. If the user
 * provides a date for which the data is unavailable, then it will inform the user that the data for
 * the particular company is unavailable.</p>
 */
public class InvestmentModel implements InvestmentModelInterface, Serializable {
  private static final long serialVersionUID = 6033262243162979644L;
  private HashMap<String, IPortfolio> listOfPortfolio;

  /**
   * This constructor is used to instantiate the Investmant model.
   */
  public InvestmentModel() {
    listOfPortfolio = new HashMap<String, IPortfolio>();
  }

  /**
   * This method allows user to buy stock at a certain date which is specified by the user. It takes
   * ticker symbol, timestamp , numbre of shares the user wants to buy of the company passed and the
   * portfolio the user wants the user to add the stock to.
   *
   * @param ticker        the ticker symbol.
   * @param timeStamp     the time stamp.
   * @param noOfShares    the number of shares.
   * @param portfolioName the portfolio name.
   * @throws IllegalArgumentException if the stock market is closed on holidays.
   * @throws ParseException           if the date comparision fails.
   */
  @Override
  public void buyStocks(String ticker, String timeStamp, Integer noOfShares, String portfolioName)
          throws IllegalArgumentException, ParseException {

    checkBuyStocksParameters(ticker, timeStamp, noOfShares, portfolioName);

    DateUtility checkDate = new DateUtility();

    if (!checkDate.isWeekDay(timeStamp)) {
      throw new IllegalArgumentException("Stock Market Closed! It's a Weekend!");
    }

    IStockMarketSimulation stockMarket = StockMarketSimulation.getInstance();
    Stock s = stockMarket.buyStock(ticker, timeStamp, noOfShares);
    if (listOfPortfolio.containsKey(portfolioName.trim())) {
      listOfPortfolio.get(portfolioName.trim()).addStocksToPortfolio(s);
    } else {
      IPortfolio p = new Portfolio();
      p.addStocksToPortfolio(s);
      listOfPortfolio.put(portfolioName.trim(), p);
    }


  }

  /**
   * This method allows the user to evaluate their portfolios against the time stamp provided.
   *
   * @param portfolioName the portfolio name.
   * @param timestamp     the timestamp.
   * @return a Portfolio Transferable object that contains all the information of the portfolio.
   * @throws IllegalArgumentException if the portfolio doesnot exist.
   */
  @Override
  public PortfolioTransferable evaluatePortfolio(String portfolioName, String timestamp)
          throws IllegalArgumentException {

    if (listOfPortfolio.containsKey(portfolioName)) {
      PortfolioTransferable statement = listOfPortfolio.get(portfolioName).valuationForPortfolio(timestamp);
      return statement;
    } else {
      throw new IllegalArgumentException("Portfolio does not exist.");
    }

  }

  /**
   * This method returns the list of portfolio names of a user.
   *
   * @return List of the portfolio names.
   */
  @Override
  public List<String> getPortfolioNames() {
    return new ArrayList<>(listOfPortfolio.keySet());
  }

  /**
   * This method allows the user to create a new portfolio.
   *
   * @throws IllegalArgumentException if the portfolio name already exists.
   */
  @Override
  public void createNewPortfolio(String portfolioName) throws IllegalArgumentException {
    if (!listOfPortfolio.containsKey(portfolioName)) {
      listOfPortfolio.put(portfolioName, new Portfolio());
    } else {
      throw new IllegalArgumentException("Portfolio already exists!");// handled in controller.
    }
  }

  /**
   * This method verifies the various buy stock parameters.
   *
   * @param ticker        the ticker symbol.
   * @param timeStamp     the time stamp.
   * @param noOfShares    number of shares.
   * @param portfolioName portfolio name.
   * @throws IllegalArgumentException when the ticker,timestamp, num of shares or portfolio is
   *                                  null.
   * @throws IllegalArgumentException when the ticker or timestamp is empty.
   * @throws IllegalArgumentException when the number of shares is 0 or negative.
   * @throws IllegalArgumentException when the date is in future.
   * @throws ParseException           when the date is not in "yyyy-MM-dd" format.
   */
  private void checkBuyStocksParameters(String ticker, String timeStamp, Integer noOfShares,
                                        String portfolioName) throws IllegalArgumentException, ParseException {


    if (ticker == null || timeStamp == null || noOfShares == null || portfolioName == null) {
      throw new IllegalArgumentException("Ticker or Timestamp or NoOfShares or Portfolio " +
              "Name cannot be Null.");
    }

    if (ticker.equals("") || timeStamp.equals("") || portfolioName.equals("")) {
      throw new IllegalArgumentException("Ticker or Timestamp or Portfolio Name cannot be Empty.");
    }

    if (noOfShares <= 0) {
      throw new IllegalArgumentException("Number of Shares cannot be 0 or negative.");
    }

    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    Date passedDate = df.parse(timeStamp);
    Date todayDate = new Date();

    if (passedDate.compareTo(todayDate) > 0) {
      throw new IllegalArgumentException("Date passed is in the future.");
    }

  }


}
