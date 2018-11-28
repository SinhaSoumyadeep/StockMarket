
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.DollarCostAverageStrategy;
import model.InvestModelInterfaceNew;
import model.InvestmentModelNew;
import model.InvestmentStrategyInterface;
import transferable.PortfolioTransferable;
import transferable.StockTransferable;


import static org.junit.Assert.*;

public class InvestmentModelTest {

  private InvestModelInterfaceNew im;


  /**
   * Test to check if exception is thrown when buyStock method parameters are entered as null.
   */


  @Test
  public void testBuyStockNullParameter() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks(null, "2018-11-14", 10.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name or Commission " +
                      "cannot be Null.",
              e.getMessage());
    }
    try {
      im.buyStocks("aapl", "2018-11-14", null, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name or Commission " +
                      "cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10.0, null,
              "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name or Commission " +
                      "cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", null, 10.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name or Commission " +
                      "cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-21", 10.0, "abc",
              null);
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name or Commission " +
                      "cannot be Null.",
              e.getMessage());
    }


  }


  /**
   * Test to check if exception is thrown when string parameters of buyStock method are passed as
   * empty.
   */


  @Test
  public void testBuyStockEmptyParameter() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("", "2018-11-14", 10.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name or Commission cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "", 10.0, "abc", "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name or Commission cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10.0, "",
              "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name or Commission cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10.0, "abc",
              "");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name or Commission cannot be Empty.",
              e.getMessage());
    }

  }


  /**
   * Test to check if exception is thrown when number of shares entered is either negative or 0.
   */


  @Test
  public void testBuyStockNoOfShares() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2018-11-14", -1.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Number of Shares cannot be 0 or negative.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 0.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Number of Shares cannot be 0 or negative.",
              e.getMessage());
    }
  }

  /**
   * Test to check if exception is thrown when commission is negative.
   */


  @Test
  public void testNegativeCommission() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2018-11-14", 1.0, "abc",
              "-10");
    } catch (Exception e) {
      assertEquals("Commission cannot be negative.",
              e.getMessage());
    }

  }


  /**
   * Test to check if exception is thrown when timestamp entered is of future date.
   */


  @Test
  public void testBuyStockDate() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2019-11-20", 10.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Date passed is in the future.",
              e.getMessage());
    }

  }


  /**
   * Test to check if exception is thrown when timestamp entered is a holiday or weekend.
   */


  @Test
  public void testBuyStockDateHoliday() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2018-11-11", 10.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Stock Market Closed! It's a Weekend!",
              e.getMessage());
    }

  }


  /**
   * Test to check if exception is thrown when timestamp entered is a date way back in the past and
   * for which data does not exist.
   */


  @Test
  public void testBuyStockDatePast() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("msft", "1950-11-13", 10.0, "abc",
              "10");
    } catch (Exception e) {
      assertEquals("Sorry! cannot buy stock, Market is closed on 1950-11-13",
              e.getMessage());
    }

  }


  /**
   * Test to check if buyStock method works correctly.
   */


  @Test
  public void testBuyStockMethod() throws ParseException {
    im = new InvestmentModelNew();
    im.buyStocks("aapl", "2018-11-13", 10.0, "abc",
            "10");
    im.buyStocks("goog", "2018-11-13", 20.0, "abc",
            "10");

    assertEquals(2, im.evaluatePortfolio("abc", "2018-11-14")
            .getStocks().size());

  }


  /**
   * Test to check if correct portfolio name is returned when user asks to getPortfolioName for the
   * first time.
   */


  @Test
  public void testGetPortfolionNameFirstCall() {
    im = new InvestmentModelNew();
    im.getPortfolioNames();

    assertEquals(0, im.getPortfolioNames().size());

  }


  /**
   * Test to check if correct portfolio name is returned when user asks to getPortfolioName after
   * buying stocks and adding to portfolio.
   */


  @Test
  public void testGetPortfolionName() throws ParseException {
    im = new InvestmentModelNew();
    im.buyStocks("aapl", "2018-11-13", 10.0, "abc",
            "10");

    assertEquals("[abc]", im.getPortfolioNames().toString());
  }


  /**
   * Test to check if create portfolio works correctly when user creates one portfolio.
   */


  @Test
  public void testCreateOnePortfolio() throws ParseException {
    im = new InvestmentModelNew();
    im.createNewPortfolio("tech");

    assertEquals("[tech]", im.getPortfolioNames().toString());
  }


  /**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   */


  @Test
  public void testCreateMultiplePortfolio() throws ParseException {
    im = new InvestmentModelNew();
    im.createNewPortfolio("tech");
    im.createNewPortfolio("health");
    im.createNewPortfolio("retirement");
    im.createNewPortfolio("education");

    assertEquals("[tech, education, health, retirement]", im.getPortfolioNames().
            toString());
  }


  /**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   */


  @Test
  public void testCostBasis() throws ParseException, IOException {
    im = new InvestmentModelNew();
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im.createNewPortfolio("tech");
    im.createNewPortfolio("health");
    im.createNewPortfolio("retirement");
    im.createNewPortfolio("education");
    im.buyStocks("aapl", "2018-11-08", 10.0, "tech",
            "5");
    im.buyStocks("msft", "2018-11-07", 32.0, "tech",
            "10");
    im.buyStocks("goog", "2018-11-06", 27.0, "tech",
            "12");
    p = im.evaluatePortfolio("tech", "2018-11-15");

    stk = p.getStocks();


    assertEquals("goog", stk.get(0).getTicker());
    assertEquals(new Double(27), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(28518.87), stk.get(0).getTotalInvestment());
    assertEquals("aapl", stk.get(1).getTicker());
    assertEquals(new Double(10), stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(2089.9), stk.get(1).getTotalInvestment());
    assertEquals("msft", stk.get(2).getTicker());
    assertEquals(new Double(32), stk.get(2).getTotalNumberOfShares());
    assertEquals(new Double(3592.72), stk.get(2).getTotalInvestment());

  }


  /**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   */


  @Test
  public void testValue() throws ParseException, IOException {
    im = new InvestmentModelNew();
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im.createNewPortfolio("tech");
    im.createNewPortfolio("health");
    im.createNewPortfolio("retirement");
    im.createNewPortfolio("education");
    im.buyStocks("aapl", "2018-11-08", 10.0, "tech",
            "5");
    im.buyStocks("msft", "2018-11-07", 32.0, "tech",
            "10");
    im.buyStocks("goog", "2018-11-06", 27.0, "tech",
            "12");

    p = im.evaluatePortfolio("tech", "2018-11-13");
    assertEquals(new Double(34201.49), p.getTotalInvestment());
    assertEquals(new Double(33317.729999999996), p.getTotalValue());


  }

  /**
   * Test to check if stocks are bought successfully when commission is zero.
   */

  @Test
  public void testZeroCommission() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    im.createNewPortfolio("abc");
    im.buyStocks("goog", "2018-11-21", 5.0, "abc",
            "0");
    p = im.evaluatePortfolio("abc", "2018-11-21");

    stk = p.getStocks();
    assertEquals("goog", stk.get(0).getTicker());
    assertEquals(new Double(5.0), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(5188.049999999999), stk.get(0).getTotalInvestment());
    assertEquals(new Double(5188.049999999999), stk.get(0).getTotalValue());


  }

  /**
   * Test to check if commission is added successfully when buying one stock.
   */

  @Test
  public void testCommissionBuyStocks() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    im.createNewPortfolio("abc");
    im.buyStocks("goog", "2018-11-21", 5.0, "abc",
            "10");
    p = im.evaluatePortfolio("abc", "2018-11-21");

    stk = p.getStocks();
    assertEquals("goog", stk.get(0).getTicker());
    assertEquals(new Double(5.0), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(5198.049999999999), stk.get(0).getTotalInvestment());
    assertEquals(new Double(5188.049999999999), stk.get(0).getTotalValue());


  }

  /**
   * Test to check if commission is added successfully when buying multiple stocks.
   */

  @Test
  public void testCommissionBuyStocksMultiple() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    im.createNewPortfolio("abc");
    im.buyStocks("goog", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("msft", "2018-11-21", 10.0, "abc",
            "35");
    p = im.evaluatePortfolio("abc", "2018-11-21");

    stk = p.getStocks();
    assertEquals("goog", stk.get(0).getTicker());
    assertEquals(new Double(5.0), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(5198.049999999999), stk.get(0).getTotalInvestment());
    assertEquals(new Double(5188.049999999999), stk.get(0).getTotalValue());
    assertEquals("msft", stk.get(1).getTicker());
    assertEquals(new Double(10.0), stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(1066.1), stk.get(1).getTotalInvestment());
    assertEquals(new Double(1031.1), stk.get(1).getTotalValue());


  }


  /**
   * Test to check if exception is thrown when weights are negative.
   */
  @Test
  public void testWeightSummation() throws ParseException {
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 70.0);
      put("GOOG", 40.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("goog", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("msft", "2018-11-21", 10.0, "abc",
            "35");
    try {
      im.investStocks("abc", 10000.0, weights, "2018-11-21",
              "20");
    } catch (Exception e) {
      assertEquals("Weights should add up to 100.", e.getMessage());
    }
  }

  /**
   * Test to check if exception is thrown when weights do not add upto 100.
   */
  @Test
  public void testNegativeWeight() throws ParseException {
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", -20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("goog", "2018-11-21", 5.0, "abc",
            "10");
    try {
      im.investStocks("abc", 10000.0, weights, "2018-11-21",
              "20");
    } catch (Exception e) {
      assertEquals("Weight cannot be negative.", e.getMessage());
    }
  }

  /**
   * Test to check if invest stocks method works correctly.
   */
  @Test
  public void testInvestStock() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 100.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.investStocks("abc", 10000.0, weights, "2018-11-27",
            "20");

    p = im.evaluatePortfolio("abc", "2018-11-27");
    stk = p.getStocks();
    assertEquals("GOOG", stk.get(0).getTicker());
    assertEquals(new Double(14.574783849254603), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(15218.05), stk.get(0).getTotalInvestment());
    assertEquals(new Double(15222.050000000001), stk.get(0).getTotalValue());

  }

  /**
   * Test to check if exception is thrown when trying to invest fixed amount on a portfolio with no
   * stocks.
   */
  @Test
  public void testInvestStockEmptyPortfolio() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 100.0);
    }};
    im.createNewPortfolio("abc");
    try {
      im.investStocks("abc", 10000.0, weights, "2018-11-27",
              "20");
    } catch (Exception e) {
      assertEquals("Portfolio has no contents.", e.getMessage());
    }

  }

  /**
   * Test to check if invest stocks method works correctly.
   */
  @Test
  public void testInvestStockMultipleStocks() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    im.investStocks("abc", 10000.0, weights, "2018-11-27",
            "20");

    p = im.evaluatePortfolio("abc", "2018-11-27");
    stk = p.getStocks();
    assertEquals("MSFT", stk.get(0).getTicker());
    assertEquals(new Double(28.667164457718872), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(3061.1), stk.get(0).getTotalInvestment());
    assertEquals(new Double(3071.4), stk.get(0).getTotalValue());
    assertEquals("GOOG", stk.get(1).getTicker());
    assertEquals(new Double(12.659827079403682), stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(13218.05), stk.get(1).getTotalInvestment());
    assertEquals(new Double(13222.050000000001), stk.get(1).getTotalValue());

  }

  /**
   * Test to check if weights are distributed successfully.
   */
  @Test
  public void testWeightDistribution() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    PortfolioTransferable p1 = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    List<StockTransferable> stk1 = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");

    p = im.evaluatePortfolio("abc", "2018-11-27");
    stk = p.getStocks();
    assertEquals("MSFT", stk.get(0).getTicker());
    assertEquals(new Double(10.0), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(1041.1), stk.get(0).getTotalInvestment());
    assertEquals(new Double(1071.4), stk.get(0).getTotalValue());
    assertEquals("GOOG", stk.get(1).getTicker());
    assertEquals(new Double(5.0), stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(5198.049999999999), stk.get(1).getTotalInvestment());
    assertEquals(new Double(5222.05), stk.get(1).getTotalValue());

    im.investStocks("abc", 10000.0, weights, "2018-11-27",
            "20");

    p1 = im.evaluatePortfolio("abc", "2018-11-27");
    stk1 = p1.getStocks();
    assertEquals("MSFT", stk1.get(0).getTicker());
    assertEquals(new Double(28.667164457718872), stk1.get(0).getTotalNumberOfShares());
    assertEquals(new Double(3061.1), stk1.get(0).getTotalInvestment());
    assertEquals(new Double(3071.4), stk1.get(0).getTotalValue());
    assertEquals("GOOG", stk1.get(1).getTicker());
    assertEquals(new Double(12.659827079403682), stk1.get(1).getTotalNumberOfShares());
    assertEquals(new Double(13218.05), stk1.get(1).getTotalInvestment());
    assertEquals(new Double(13222.050000000001), stk1.get(1).getTotalValue());

  }

  /**
   * Test to check if invest stocks method works correctly when passing equal weights.
   */
  @Test
  public void testInvestStockMultipleStocksEqualWeight() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    im.investStocks("abc", 10000.0, "2018-11-27",
            "20");

    p = im.evaluatePortfolio("abc", "2018-11-27");
    stk = p.getStocks();
    assertEquals("MSFT", stk.get(0).getTicker());
    assertEquals(new Double(56.66791114429718), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(6061.1), stk.get(0).getTotalInvestment());
    assertEquals(new Double(6071.4), stk.get(0).getTotalValue());
    assertEquals("GOOG", stk.get(1).getTicker());
    assertEquals(new Double(9.7873919246273), stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(10218.05), stk.get(1).getTotalInvestment());
    assertEquals(new Double(10222.05), stk.get(1).getTotalValue());

  }

  /**
   * Test to check if exception is thrown when timestamp is a holiday.
   */
  @Test
  public void testInvestStockHoliday() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    try {
      im.investStocks("abc", 10000.0, weights, "2018-11-22",
              "20");
    } catch (Exception e) {
      assertEquals("Sorry! cannot buy stock, Market is closed on 2018-11-22",
              e.getMessage());
    }


  }

  /**
   * Test to check if exception is thrown when timestamp is weekend.
   */
  @Test
  public void testInvestStockWeekend() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    try {
      im.investStocks("abc", 10000.0, weights, "2018-11-25",
              "20");
    } catch (Exception e) {
      assertEquals("Stock Market Closed! It's a Weekend!", e.getMessage());
    }


  }

  /**
   * Test to check if exception is thrown when fixed amount is negative.
   */
  @Test
  public void testNegativeFixedAmount() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    try {
      im.investStocks("abc", -1000.0, weights, "2018-11-21",
              "20");
    } catch (Exception e) {
      assertEquals("Fixed Amount cannot be negative.", e.getMessage());
    }
  }

  /**
   * Test to check for Dollar Cost Average Strategy.
   */
  @Test
  public void testNeFrequencyNegative() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    try {
      InvestmentStrategyInterface i = new DollarCostAverageStrategy(1000.0,
              "2018-11-21", "2018-11-25", -1, "10");
      im.registerStrategy(i, "abc", weights);
    } catch (Exception e) {
      assertEquals("Frequency cannot be negative", e.getMessage());
    }


  }

  /**
   * Test to check if exception is thrown when end date is before start date.
   */
  @Test
  public void testEndDateBeforeStartDate() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");
    try {
      InvestmentStrategyInterface i = new DollarCostAverageStrategy(1000.0,
              "2018-11-21", "2018-11-20", 1, "10");
      im.registerStrategy(i, "abc", weights);
    } catch (Exception e) {
      assertEquals("Start date cannot be after end date or end date cannot be before " +
              "start date", e.getMessage());
    }


  }

  /**
   * Test to check if current date is considered when end date is in the future.
   */
  @Test
  public void testDollarCostAverageImplmentation() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    PortfolioTransferable p1 = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    List<StockTransferable> stk1 = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");

    InvestmentStrategyInterface i = new DollarCostAverageStrategy(1000.0,
            "2018-11-21", "2018-11-27", 1, "10");
    im.registerStrategy(i, "abc", weights);

    p = im.evaluatePortfolio("abc", "2018-11-22");
    stk = p.getStocks();
    assertEquals("MSFT", stk.get(0).getTicker());
    assertEquals(new Double(11.939676074095626), stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(1251.1), stk.get(0).getTotalInvestment());
    assertEquals(new Double(1231.1), stk.get(0).getTotalValue());
    assertEquals("GOOG", stk.get(1).getTicker());
    assertEquals(new Double(5.771002592496218), stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(6008.049999999999), stk.get(1).getTotalInvestment());
    assertEquals(new Double(5988.05), stk.get(1).getTotalValue());

    p1 = im.evaluatePortfolio("abc", "2018-11-24");
    stk1 = p1.getStocks();
    assertEquals("MSFT", stk1.get(0).getTicker());
    assertEquals(new Double(13.88010490886811), stk1.get(0).getTotalNumberOfShares());
    assertEquals(new Double(1461.1), stk1.get(0).getTotalInvestment());
    assertEquals(new Double(1430.622412957036), stk1.get(0).getTotalValue());
    assertEquals("GOOG", stk1.get(1).getTicker());
    assertEquals(new Double(6.552344155960686), stk1.get(1).getTotalNumberOfShares());
    assertEquals(new Double(6818.049999999999), stk1.get(1).getTotalInvestment());
    assertEquals(new Double(6708.814134405027), stk1.get(1).getTotalValue());


  }

  /**
   * Test to check if exception is thrown when new strategy is called on ongoing strategy.
   */
  @Test
  public void testNewStrategyCall() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    PortfolioTransferable p1 = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    List<StockTransferable> stk1 = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");

    try {
      InvestmentStrategyInterface i = new DollarCostAverageStrategy(1000.0,
              "2018-11-22", "2018-11-27", 1, "10");
      im.registerStrategy(i, "abc", weights);

      InvestmentStrategyInterface i1 = new DollarCostAverageStrategy(1000.0,
              "2018-11-23", "2018-11-29", 1, "10");
      im.registerStrategy(i1, "abc", weights);
    } catch (Exception e) {
      assertEquals("strategy is ongoing", e.getMessage());
    }


  }

  /**
   * Test to check if exception is thrown when startegy start date is before stocks are bought.
   */
  @Test
  public void testStartDateBeforeStockPurchased() throws ParseException {
    PortfolioTransferable p = new PortfolioTransferable();
    PortfolioTransferable p1 = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    List<StockTransferable> stk1 = new ArrayList<StockTransferable>();
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    im.buyStocks("GOOG", "2018-11-21", 5.0, "abc",
            "10");
    im.buyStocks("MSFT", "2018-11-21", 10.0, "abc",
            "10");

    try {
      InvestmentStrategyInterface i = new DollarCostAverageStrategy(1000.0,
              "2018-11-20", "2018-11-27", 1, "10");
      im.registerStrategy(i, "abc", weights);

    } catch (Exception e) {
      assertEquals("Start date of strategy cannot be before date of stocks purchased.",
              e.getMessage());
    }


  }

  /**
   * Test to check if current date is considered when end date is in the future.
   */
  @Test
  public void testStrategyOnEmptyPortfolio() throws ParseException {
    im = new InvestmentModelNew();
    HashMap<String, Double> weights = new HashMap<String, Double>() {{
      put("GOOG", 80.0);
      put("MSFT", 20.0);
    }};
    im.createNewPortfolio("abc");
    try {
      InvestmentStrategyInterface i = new DollarCostAverageStrategy(1000.0,
              "2018-11-21", "2018-11-27", 1, "10");
      im.registerStrategy(i, "abc", weights);
    }catch (Exception e){
      assertEquals("Portfolio has no contents.",e.getMessage());
    }


  }




}
