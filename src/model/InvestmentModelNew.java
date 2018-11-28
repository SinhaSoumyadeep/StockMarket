package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;
import transferable.PortfolioTransferable;

/**
 * This class also represents a new model which extends the old model and implements new operations
 * that follows solid principles.
 * <p>In its simplest form, a stock of a company is simply a part of ownership in that company.
 * Ownership is divided into shares, where a share represents a fraction of the total ownership. For
 * example, Apple has about 5.2B shares. So if you own 100 shares, you own about 1.9X10 minus 6 of
 * the company and it would be worth about dollar20800 today. As a shareholder, you are an investor
 * in the company. An investor sends money to the company to buy some of its stock, and gets part
 * ownership in return. The total money invested in the stock money spent buying it is called the
 * cost basis of the purchase. The value of the stock on a particular day is the money the investor
 * would receive if he/she sold the stock on that day.</p>
 * <p>
 * Each publicly traded company’s stock is given a unique ticker symbol which is used to trade it
 * for example, Apple Inc. is AAPL, Microsoft is MSFT The price of stock keeps changing all day
 * depending on how many people want to own that stock versus how many people want to sell their
 * shares. The behavior of a US stock during a day can be understood by its opening price at 8am EST
 * when the New York Stock Exchange opens for business its closing price at 4pm EST when the NYSE
 * closes for regular business its low and high price during the day. At any point in time, the
 * total value of an investor’s holdings of a stock is simply the price of the stock at that time,
 * multiplied by the number of shares of that stock that the investor owns.
 * </p>
 * <p>This application helps users who are new to investing to learn about how money could grow, in
 * the style of virtual gambling. Similar to virtual gambling, our application will not use real
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
 *
 * <p>Invest a fixed amount into an existing portfolio containing multiple stocks, using a
 * specified weight for each stock in the portfolio.For example, the user can create a FANG
 * portfolio Facebook, Apple, Netflix, Google and then specify to invest dollar 2000 in the
 * portfolio, such that 40percent goes towards Facebook, 20percent towards Apple, 30percent towards
 * Netflix and 10percent towards Google.</p>
 *
 * <p>program offers a more convenient way to use equal weights for all stocks in the
 * portfolio as a preset. For example, invest dollar 2000 in this portfolio by weighing each stock
 * equally e.g. 25percent each in the FANG portfolio above.</p>
 *
 * <p>
 * Add support for the user to specify the commission fee for a transaction, and factor these fees
 * in the cost basis of a portfolio.
 * </p>
 */
public class InvestmentModelNew extends InvestmentModel implements InvestModelInterfaceNew {

  private HashMap<String, WeightsOfPortfolio> listOfWeights;
  private HashMap<String, InvestmentStrategyInterface> strategyTracker;

  /**
   * this constructor instantiates the new model.
   */
  public InvestmentModelNew() {

    listOfWeights = new HashMap<String, WeightsOfPortfolio>();
    strategyTracker = new HashMap<String, InvestmentStrategyInterface>();

  }

  /**
   * this method Invests a fixed amount into an existing portfolio containing multiple stocks, using
   * a specified weight for each stock in the portfolio.
   *
   * @param portfolioName portfolio name
   * @param fixedAmount   fixed amount trying to invest.
   * @param weights       the weights for each stocks in portfolio.
   * @param timeStamp     the timestamp on which stocks are to be bought.
   * @param commission    the commission fee.
   * @throws ParseException if timestamp is invalid.
   */
  @Override
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String,
          Double> weights, String timeStamp, String commission) throws ParseException {

    portfolioName = portfolioName.toUpperCase();
    if(checkIfPortfolioIsEmpty(portfolioName)){
      throw new IllegalArgumentException("Portfolio has no contents.");
    }
    for(Double value: weights.values()){
      if(value < 0){
        throw new IllegalArgumentException("Weight cannot be negative.");
      }
    }
    Double cumulativeWeight = 0.0;
    for (String key : weights.keySet()) {
      cumulativeWeight = cumulativeWeight + weights.get(key);

    }
    if (cumulativeWeight != 100.0) {
      throw new IllegalArgumentException("Weights should add up to 100.");
    }
    IPortfolio investingPortfolio = this.listOfPortfolio.get(portfolioName);
    this.listOfWeights.put(portfolioName, new WeightsOfPortfolio(weights));
    for (String key : investingPortfolio.getStockNamesInPortfolio()) {
      Double moneyForEachStock = (weights.get(key) / 100) * fixedAmount;
      investStockhelper(moneyForEachStock, key, portfolioName, timeStamp, commission);
    }


  }

  /**
   * this method Invests a more convenient way to use equal weights for all stocks in the portfolio
   * as a preset.
   *
   * @param portfolioName portfolio name
   * @param fixedAmount   fixed amount trying to invest.
   * @param timeStamp     the timestamp on which stocks are to be bought.
   * @param commission    the commission fee.
   * @throws ParseException if timestamp is invalid.
   */
  @Override
  public void investStocks(String portfolioName, Double fixedAmount, String timeStamp,
                           String commission) throws ParseException {

    portfolioName = portfolioName.toUpperCase();
    if(checkIfPortfolioIsEmpty(portfolioName)){
      throw new IllegalArgumentException("Portfolio has no contents.");
    }

    IPortfolio investingPortfolio = this.listOfPortfolio.get(portfolioName);

    Double moneyForEachStock = fixedAmount / investingPortfolio.getStockNamesInPortfolio().size();

    for (String key : investingPortfolio.getStockNamesInPortfolio()) {

      investStockhelper(moneyForEachStock, key, portfolioName, timeStamp, commission);
    }

  }

  /**
   * This method is passed an investment strategy which is executed on the portfolio name passed.
   *
   * @param strategy      the Investment strategy.
   * @param portfolioName the portfolio name.
   * @param weights       the fixed weights for which stocks in portfolio is to be executed.
   */
  @Override
  public void registerStrategy(InvestmentStrategyInterface strategy, String portfolioName,
                               HashMap<String, Double> weights) {

    portfolioName = portfolioName.toUpperCase();
    invest(strategy, portfolioName, weights);

  }

  /**
   * This method is used to check the weights assigned to a partcular portfolio.
   *
   * @param portfolioName the portfolio name
   * @return a copy of the weights that were assigned to the portfolio.
   */
  @Override
  public HashMap<String, Double> viewWeights(String portfolioName) {
    portfolioName = portfolioName.toUpperCase();
    HashMap<String, Double> weightsOfAPortfolio = this.listOfWeights.get(portfolioName).getWeight();
    return new HashMap<String, Double>(weightsOfAPortfolio);
  }

  /**
   * This method actually calls the execute investment of the strategy specified  to start
   * investing.
   *
   * @param strategy      the investment strategy.
   * @param portfolioName the portfolio name.
   * @param weights       the fixed weights for which stocks in portfolio is to be executed.
   */
  private void invest(InvestmentStrategyInterface strategy, String portfolioName,
                      HashMap<String, Double> weights) {
    portfolioName = portfolioName.toUpperCase();
    try {

      strategy.exceuteStrategyOnPortfolio(portfolioName, this,
              LocalDate.now().toString(), weights);
    } catch (ParseException e) {
      throw new IllegalArgumentException("Date format is Invalid");
    }
  }

  /**
   * This method is just a helper method which helps in investing stocks to the portfolio passed.
   *
   * @param moneyForEachStock money available for each stock to invest on.
   * @param key               the ticker
   * @param portfolioName     the portfolio name.
   * @param timeStamp         the timestamp.
   * @param commission        the commission fee.
   * @throws ParseException when the time is invalid.
   */
  private void investStockhelper(Double moneyForEachStock, String key, String portfolioName,
                                 String timeStamp, String commission) throws ParseException {

    portfolioName = portfolioName.toUpperCase();
    IStockMarketSimulation stockMarket = StockMarketSimulation.getInstance();
    Double currentStockPrice = stockMarket.priceOfAStockAtACertainDate(key, timeStamp);

    Double partialNumberOfShares = (moneyForEachStock / currentStockPrice);
    Double wholeShares = partialNumberOfShares;
    buyStocks(key, timeStamp, wholeShares, portfolioName, commission);
  }

  /**
   * this method returns a copy of the stocks that are in a particular portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return the copy of the stock names.
   */
  @Override
  public List<String> getStocksInPortfolio(String portfolioName) {
    portfolioName = portfolioName.toUpperCase();
    if (this.listOfPortfolio.get(portfolioName).getStockNamesInPortfolio().isEmpty()) {
      throw new IllegalArgumentException("Portfolio has no contents.");
    }
    return new ArrayList<String>(this.listOfPortfolio.get(portfolioName)
            .getStockNamesInPortfolio());
  }

  /**
   * this method returns the latest date on which a stock was purchased on a particular portfolio.
   *
   * @param portfolioName the portfolio name
   * @return transaction date.
   */
  @Override
  public String getLatestInvestmentDateForPortfolio(String portfolioName) {
    portfolioName = portfolioName.toUpperCase();
    IPortfolio p = this.listOfPortfolio.get(portfolioName);
    return p.lastestTransactionDate();
  }


}

