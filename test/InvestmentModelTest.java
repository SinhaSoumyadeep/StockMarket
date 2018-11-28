/*
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.InvestModelInterfaceNew;
import model.InvestmentModelNew;
import model.Portfolio;
import model.Stock;
import transferable.PortfolioTransferable;
import transferable.StockTransferable;
import utility.Options;
import view.InvestmentView;

import static org.junit.Assert.*;

public class InvestmentModelTest {

  private InvestModelInterfaceNew im;

  */
/**
   * Test to check if exception is thrown when buyStock method parameters are entered as null.
   *//*


  @Test
  public void testBuyStockNullParameter() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks(null, "2018-11-14", 10.0, "abc", "10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }
    try {
      im.buyStocks("aapl", "2018-11-14", null, "abc","10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10.0, null,"10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", null, 10, "abc","10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }

  }

  */
/**
   * Test to check if exception is thrown when string parameters of buyStock method are passed as
   * empty.
   *//*


  @Test
  public void testBuyStockEmptyParameter() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("", "2018-11-14", 10, "abc","10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "", 10, "abc","10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10, "","10");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name cannot be Empty.",
              e.getMessage());
    }

  }

  */
/**
   * Test to check if exception is thrown when number of shares entered is either negative or 0.
   *//*


  @Test
  public void testBuyStockNoOfShares() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2018-11-14", -1, "abc","10");
    } catch (Exception e) {
      assertEquals("Number of Shares cannot be 0 or negative.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 0, "abc","10");
    } catch (Exception e) {
      assertEquals("Number of Shares cannot be 0 or negative.",
              e.getMessage());
    }
  }

  */
/**
   * Test to check if exception is thrown when timestamp entered is of future date.
   *//*


  @Test
  public void testBuyStockDate() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2019-11-20", 10, "abc","10");
    } catch (Exception e) {
      assertEquals("Date passed is in the future.",
              e.getMessage());
    }

  }

  */
/**
   * Test to check if exception is thrown when timestamp entered is a holiday or weekend.
   *//*


  @Test
  public void testBuyStockDateHoliday() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2018-11-11", 10, "abc","10");
    } catch (Exception e) {
      assertEquals("Stock Market Closed! It's a Weekend!",
              e.getMessage());
    }

  }

  */
/**
   * Test to check if exception is thrown when timestamp entered is a date way back in the past and
   * for which data does not exist.
   *//*


  @Test
  public void testBuyStockDatePast() {
    im = new InvestmentModelNew();
    try {
      im.buyStocks("msft", "1950-11-13", 10, "abc","10");
    } catch (Exception e) {
      assertEquals("",
              e.getMessage());
    }

  }

  */
/**
   * Test to check if buyStock method works correctly.
   *//*


  @Test
  public void testBuyStockMethod() throws ParseException {
    im = new InvestmentModelNew();
    im.buyStocks("aapl", "2018-11-13", 10, "abc","10");
    im.buyStocks("goog", "2018-11-13", 20, "abc","10");

    assertEquals(2, im.evaluatePortfolio("abc", "2018-11-14")
            .getStocks().size());

  }

  */
/**
   * Test to check if correct portfolio name is returned when user asks to getPortfolioName for the
   * first time.
   *//*


  @Test
  public void testGetPortfolionNameFirstCall() {
    im = new InvestmentModelNew();
    im.getPortfolioNames();

    assertEquals(0, im.getPortfolioNames().size());

  }


  */
/**
   * Test to check if correct portfolio name is returned when user asks to getPortfolioName after
   * buying stocks and adding to portfolio.
   *//*


  @Test
  public void testGetPortfolionName() throws ParseException {
    im = new InvestmentModelNew();
    im.buyStocks("aapl", "2018-11-13", 10, "abc","10");

    assertEquals("[abc]", im.getPortfolioNames().toString());
  }

  */
/**
   * Test to check if create portfolio works correctly when user creates one portfolio.
   *//*


  @Test
  public void testCreateOnePortfolio() throws ParseException {
    im = new InvestmentModelNew();
    im.createNewPortfolio("tech");

    assertEquals("[tech]", im.getPortfolioNames().toString());
  }

  */
/**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   *//*


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

  */
/**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   *//*


  @Test
  public void testCostBasis() throws ParseException, IOException {
    im = new InvestmentModelNew();
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im.createNewPortfolio("tech");
    im.createNewPortfolio("health");
    im.createNewPortfolio("retirement");
    im.createNewPortfolio("education");
    im.buyStocks("aapl", "2018-11-08", 10, "tech","5");
    im.buyStocks("msft", "2018-11-07", 32, "tech","10");
    im.buyStocks("goog", "2018-11-06", 27, "tech","12");
    p = im.evaluatePortfolio("tech", "2018-11-15");

    stk = p.getStocks();


    assertEquals("goog",stk.get(0).getTicker());
    assertEquals(new Integer(27),stk.get(0).getTotalNumberOfShares());
    assertEquals(new Double(28518.87),stk.get(0).getTotalInvestment());
    assertEquals("aapl",stk.get(1).getTicker());
    assertEquals(new Integer(10),stk.get(1).getTotalNumberOfShares());
    assertEquals(new Double(2089.9),stk.get(1).getTotalInvestment());
    assertEquals("msft",stk.get(2).getTicker());
    assertEquals(new Integer(32),stk.get(2).getTotalNumberOfShares());
    assertEquals(new Double(3592.72),stk.get(2).getTotalInvestment());

  }

  */
/**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   *//*


  @Test
  public void testValue() throws ParseException, IOException {
    im = new InvestmentModelNew();
    PortfolioTransferable p = new PortfolioTransferable();
    List<StockTransferable> stk = new ArrayList<StockTransferable>();
    im.createNewPortfolio("tech");
    im.createNewPortfolio("health");
    im.createNewPortfolio("retirement");
    im.createNewPortfolio("education");
    im.buyStocks("aapl", "2018-11-08", 10, "tech","5");
    im.buyStocks("msft", "2018-11-07", 32, "tech","10");
    im.buyStocks("goog", "2018-11-06", 27, "tech","12");

    p = im.evaluatePortfolio("tech", "2018-11-13");
    assertEquals(new Double(34201.49),p.getTotalInvestment());
    assertEquals(new Double(33317.729999999996),p.getTotalValue());


  }


}*/
