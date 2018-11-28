package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utility.DateUtility;

/**
 * This class is an implementation of Dollar Cost Average Strategy which is a A popular long-term
 * passive investment strategy is called dollar cost averaging. In this strategy, an investor
 * creates a portfolio of stocks and determines their relative proportion example 60 percent stocks
 * 40 percent bonds with the stocks equally divided into technology financial utility real estate
 * consumer staple and consumer discretionary companies and the bonds equally divided into federal
 * state and municipal bonds. Then the investor invests a fixed amount of money in this portfolio at
 * regular frequency example invest dollar 1000 at the beginning of each month over a long period of
 * time paying no attention to what the stock market is doing on the days of the stock purchases.
 * This may be termed as the investment equivalent of putting a fixed amount of money in a savings
 * account each month.The benefit of this strategy is that it is extremely simple the investor does
 * not have to spend time and effort tweaking a portfolio, or monitoring the stock market. This
 * strategy is based on the well-founded hypothesis that although the stock prices fluctuate every
 * day, the long-term trend of a well-chosen portfolio is upward. The fixed-frequency investing
 * effectively averages the unpredictable short-term gains and losses of the portfolio. While this
 * strategy fails to capitalize on the market ups and downs it has the potential to produce a more
 * stable gain when the strategy is followed without hesitation over a long period of time. This
 * strategy is very popular for example most people manage their retirement accounts this way.This
 * interface provides method to execute the strategy on the passed portfolio.
 */
public class DollarCostAverageStrategy implements InvestmentStrategyInterface {

  private Double fixedAmount;
  private String startDate;
  private String endDate;
  private Integer frequency;
  private static HashMap<String, List<String>> transactionHistory;
  private String commission;

  /**
   * this is a static block to initialize the record keeping.
   */
  static {
    transactionHistory = new HashMap<String, List<String>>();
  }


  /**
   * This constructor instantiates the Dollar cost average strategy class.
   *
   * @param fixedAmount the fixed amount.
   * @param startDate   the start day.
   * @param endDate     the end day.
   * @param frequency   the frequency.
   * @param commission  the commission.
   */
  public DollarCostAverageStrategy(Double fixedAmount, String startDate, String endDate,
                                   Integer frequency, String commission) {

    if(fixedAmount < 0){
      throw new IllegalArgumentException("Fixed amount cannot be negative.");
    }
    if(frequency < 0){
      throw new IllegalArgumentException("Frequency cannot be negative.");
    }
    DateUtility du = new DateUtility();
    if (du.stringToDateConverter(startDate).isAfter(du.stringToDateConverter(endDate))) {
      throw new IllegalArgumentException("Start date cannot be after end date or end date cannot " +
              "be before start date");
    }

    this.fixedAmount = fixedAmount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frequency = frequency;
    this.commission = commission;

  }

  /**
   * This method takes in the portfolio name, model, time stamp and the weights to execute the
   * strategy on the portfolio passed.
   *
   * @param portfolioName the portfolio name of the portfolio to execute the strategy on.
   * @param im            the Investment model new object.
   * @param timestamp     time stamp till which the strategy needs to be executed.
   * @param weights       the fixed weights on which the strategy is to be executed till the end
   *                      date.
   * @throws ParseException if the timestamp is invalid.
   */
  @Override
  public void exceuteStrategyOnPortfolio(String portfolioName, InvestModelInterfaceNew im, String timestamp, HashMap<String, Double> weights) throws ParseException {
    DateUtility d = new DateUtility();

    Double cumulativeWeight = 0.0;
    for (String key : weights.keySet()) {
      cumulativeWeight = cumulativeWeight + weights.get(key);

    }
    if (cumulativeWeight != 100.0) {
      throw new IllegalArgumentException("Weights should add up to 100.");
    }

    if (transactionHistory.containsKey(portfolioName)) {
      if (!transactionHistory.get(portfolioName).isEmpty()) {
        for (String endDate : transactionHistory.get(portfolioName)) {
          if (d.stringToDateConverter(endDate).isAfter(d.stringToDateConverter(this.startDate))) {
            throw new IllegalArgumentException("strategy is ongoing");
          }
        }
      }
    }

    if (d.stringToDateConverter(startDate).isBefore(d.stringToDateConverter(im.getLatestInvestmentDateForPortfolio(portfolioName)))) {
      throw new IllegalArgumentException("Start date of strategy cannot be before date of stocks purchased.");
    }
    if (im.checkIfPortfolioIsEmpty(portfolioName)) {
      throw new IllegalArgumentException("Portfolio has no contents.");
    }

    if (d.stringToDateConverter(timestamp).isAfter(d.stringToDateConverter(this.endDate))) {
      timestamp = this.endDate;
    }





    LocalDate transactionEndDateForSession = d.stringToDateConverter(timestamp);
    LocalDate beginDate = d.stringToDateConverter(startDate);

    LocalDate nextDate = beginDate;

    while (nextDate.isBefore(transactionEndDateForSession) || nextDate.isEqual(transactionEndDateForSession)) {

      try {
        if (d.isWeekDay(nextDate.toString())) {
          im.investStocks(portfolioName, fixedAmount, weights, nextDate.toString(), commission);
          nextDate = nextDate.plusDays(frequency);
        } else {
          nextDate = nextDate.plusDays(1);
        }
      } catch (Exception e) {

        nextDate = nextDate.plusDays(1);
      }

    }

    if (!transactionHistory.containsKey(portfolioName)) {
      List<String> transact = new ArrayList<String>();
      transact.add(this.endDate);
      transactionHistory.put(portfolioName, transact);
    } else {
      transactionHistory.get(portfolioName).add(this.endDate);
    }
  }
}
