import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import model.InvestmentModel;

import static org.junit.Assert.*;

public class InvestmentModelTest {

  private InvestmentModel im;

  /**
   * Test to check if exception is thrown when buyStock method parameters are entered as null.
   */

  @Test
  public void testBuyStockNullParameter() {
    im = new InvestmentModel();
    try {
      im.buyStocks(null, "2018-11-14", 10, "abc");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }
    try {
      im.buyStocks("aapl", "2018-11-14", null, "abc");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10, null);
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", null, 10, "abc");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or NoOfShares or Portfolio Name cannot be Null.",
              e.getMessage());
    }

  }

  /**
   * Test to check if exception is thrown when string parameters of buyStock method are passed as
   * empty.
   */

  @Test
  public void testBuyStockEmptyParameter() {
    im = new InvestmentModel();
    try {
      im.buyStocks("", "2018-11-14", 10, "abc");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "", 10, "abc");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name cannot be Empty.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 10, "");
    } catch (Exception e) {
      assertEquals("Ticker or Timestamp or Portfolio Name cannot be Empty.",
              e.getMessage());
    }

  }

  /**
   * Test to check if exception is thrown when number of shares entered is either negative or 0.
   */

  @Test
  public void testBuyStockNoOfShares() {
    im = new InvestmentModel();
    try {
      im.buyStocks("aapl", "2018-11-14", -1, "abc");
    } catch (Exception e) {
      assertEquals("Number of Shares cannot be 0 or negative.",
              e.getMessage());
    }

    try {
      im.buyStocks("aapl", "2018-11-14", 0, "abc");
    } catch (Exception e) {
      assertEquals("Number of Shares cannot be 0 or negative.",
              e.getMessage());
    }
  }

  /**
   * Test to check if exception is thrown when timestamp entered is of future date.
   */

  @Test
  public void testBuyStockDate() {
    im = new InvestmentModel();
    try {
      im.buyStocks("aapl", "2019-11-20", 10, "abc");
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
    im = new InvestmentModel();
    try {
      im.buyStocks("aapl", "2018-11-11", 10, "abc");
    } catch (Exception e) {
      assertEquals("Stock Market Closed! It's a Weekend!",
              e.getMessage());
    }

  }

  /**
   * Test to check if exception is thrown when timestamp entered is a date way back in the past
   * and for which data does not exist.
   */

  @Test
  public void testBuyStockDatePast() {
    im = new InvestmentModel();
    try {
      im.buyStocks("msft", "1950-11-13", 10, "abc");
    } catch (Exception e) {
      assertEquals("",
              e.getMessage());
    }

  }

  /**
   * Test to check if buyStock method works correctly.
   */

  @Test
  public void testBuyStockMethod() throws ParseException {
    im = new InvestmentModel();
    im.buyStocks("aapl", "2018-11-13", 10, "abc");
    im.buyStocks("goog", "2018-11-13", 20, "abc");

    assertEquals(2, im.evaluatePortfolio("abc", "2018-11-14")
            .getStocks().size());

  }

  /**
   * Test to check if correct portfolio name is returned when user asks to getPortfolioName for the
   * first time.
   */

  @Test
  public void testGetPortfolionNameFirstCall() {
    im = new InvestmentModel();
    im.getPortfolioNames();

    assertEquals(0, im.getPortfolioNames().size());

  }


  /**
   * Test to check if correct portfolio name is returned when user asks to getPortfolioName after
   * buying stocks and adding to portfolio.
   */

  @Test
  public void testGetPortfolionName() throws ParseException {
    im = new InvestmentModel();
    im.buyStocks("aapl", "2018-11-13", 10, "abc");

    assertEquals("[abc]", im.getPortfolioNames().toString());
  }

  /**
   * Test to check if create portfolio works correctly when user creates one portfolio.
   */

  @Test
  public void testCreateOnePortfolio() throws ParseException {
    im = new InvestmentModel();
    im.createNewPortfolio("tech");

    assertEquals("[tech]", im.getPortfolioNames().toString());
  }

  /**
   * Test to check if create portfolio works correctly when user creates multiple portfolios.
   */

  @Test
  public void testCreateMultiplePortfolio() throws ParseException {
    im = new InvestmentModel();
    im.createNewPortfolio("tech");
    im.createNewPortfolio("health");
    im.createNewPortfolio("retirement");
    im.createNewPortfolio("education");

    assertEquals("[tech, education, health, retirement]", im.getPortfolioNames().
            toString());
  }




}