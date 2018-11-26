package model;

import java.text.ParseException;
import java.util.List;

import transferable.PortfolioTransferable;

/**
 * This interface simulates the functionality of stock market and provides users operations to buy
 * stocks, create portfolio and view its compositions.
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
public interface InvestmentModelInterface {
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
  void buyStocks(String ticker, String timeStamp, Integer noOfShares, String portfolioName, String commission) throws IllegalArgumentException, ParseException;

  /**
   * This method allows the user to evaluate their portfolios against the time stamp provided.
   *
   * @param portfolioName the portfolio name.
   * @param timestamp     the timestamp.
   * @return a Portfolio Transferable object that contains all the information of the portfolio.
   * @throws IllegalArgumentException if the portfolio doesnot exist.
   */
  PortfolioTransferable evaluatePortfolio(String portfolioName, String timestamp) throws IllegalArgumentException;

  /**
   * This method returns the list of portfolio names of a user.
   *
   * @return List of the portfolio names.
   */
  List<String> getPortfolioNames();

  /**
   * This method allows the user to create a new portfolio.
   *
   * @param portfolioName name of the portfolio.
   * @throws IllegalArgumentException if the portfolio name already exists.
   */
  void createNewPortfolio(String portfolioName) throws IllegalArgumentException;
}
