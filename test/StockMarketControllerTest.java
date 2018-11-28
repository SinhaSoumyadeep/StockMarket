import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import controller.IStockMarketController;

import controller.NewController;
import model.InvestModelInterfaceNew;
import model.InvestmentModelNew;
import view.InvestmentView;

import static org.junit.Assert.assertEquals;

public class StockMarketControllerTest {

  private Readable in;
  private InvestModelInterfaceNew im;
  private InvestmentView iv;
  private IStockMarketController smc;

  @Before
  public void setUp() throws IOException {

    im = new InvestmentModelNew();

  }

  /**
   * Test to check whether exception is thrown when readable is null.
   */

  @Test
  public void testNullReadable() {
    try {
      Appendable out = new StringBuffer();
      iv = new InvestmentView(out);
      smc = new NewController(null, iv, im);
    } catch (Exception e) {
      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
    }

  }

  /**
   * Test to check whether exception is thrown when view is null.
   */

  @Test
  public void testNullView() {
    try {
      Appendable out = new StringBuffer();
      iv = new InvestmentView(out);
      smc = new NewController(in, null, im);
    } catch (Exception e) {
      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
    }

  }

  /**
   * Test to check whether exception is thrown when model is null.
   */

  @Test
  public void testNullModel() {
    try {
      Appendable out = new StringBuffer();
      iv = new InvestmentView(out);
      smc = new NewController(in, iv, null);
    } catch (Exception e) {
      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
    }

  }

  /**
   * Test to check whether exception is thrown when model is null.
   */

  @Test
  public void testNullAppendable() {
    try {
      iv = new InvestmentView(null);
    } catch (Exception e) {
      assertEquals("Appendable cannot be null.", e.getMessage());
    }

  }

  /**
   * Test to check whether exception is thrown when readble, view and model are null.
   */

  @Test
  public void testNullReadableViewModel() {
    try {
      Appendable out = new StringBuffer();
      iv = new InvestmentView(out);
      smc = new NewController(null, null, null);
    } catch (Exception e) {
      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
    }

  }

  /**
   * Test to check whether correct menu is displayed when startStockMarket method is called.
   */
  @Test
  public void testMenu() throws ParseException {
    in = new StringReader("q");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    System.out.println(out.toString());
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT ***" +
            "*************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }


  /**
   * Test to check if correct output is displayed when user tries to create portfolio when no
   * portfolio is created.
   */
  @Test
  public void testFirstOption() throws ParseException {
    in = new StringReader("1\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT ***" +
            "*************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfol" +
            "io\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "\n" +
            "*********************************  no portfolio!  *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if correct output is displayed when user chooses to view portfolio valuation
   * before adding any stocks.
   */
  @Test
  public void testPortfolioValuationAfterStartingStockMarket() throws ParseException {
    in = new StringReader("2\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT **" +
            "**************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "\n" +
            "*********************************  no portfolio!  ********************************" +
            "*\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is asked to enter date again when trying to enter alphabets in date
   * field.
   */
  @Test
  public void testBuyStockInvalidDate1() throws ParseException {
    in = new StringReader("3\ngoog\nabc\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT " +
            "****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "\n" +
            "*********************************  invalid Date, Try Again.  *********************" +
            "************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  *********************" +
            "************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is asked to enter date again when trying to enter invalid date.
   */
  @Test
  public void testBuyStockInvalidDate2() throws ParseException {
    in = new StringReader("3\ngoog\n11-12-2018\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT ***" +
            "*************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "\n" +
            "*********************************  invalid Date, Try Again.  **********************" +
            "***********\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  ************************" +
            "*********\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if valid date is accepted.
   */
  @Test
  public void testBuyStockValidDate() throws ParseException {
    in = new StringReader("3\nmsft\n2018-11-14\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT " +
            "***" +
            "*************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  *********************" +
            "************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is asked to enter number of shares again when entering 0.
   */
  @Test
  public void testBuyStockZeroShares() throws ParseException {
    in = new StringReader("3\nmsft\n2018-11-14\n0\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT " +
            "****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new " +
            "Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "\n" +
            "*********************************  enter positive number for share, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  *******************" +
            "**************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is asked to enter number of shares again when entering negative integer.
   */
  @Test
  public void testBuyStockNegativeShares() throws ParseException {
    in = new StringReader("3\ngoog\n2018-11-14\n-10\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT " +
            "****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "\n" +
            "*********************************  enter positive number for share, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  ********************" +
            "*************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }


  /**
   * Test to check if user is asked to enter number of shares again when entering decimals.
   */
  @Test
  public void testBuyStockNonNumericShares() throws ParseException {
    in = new StringReader("3\ngoog\n2018-11-14\nab\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT" +
            " ****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "\n" +
            "*********************************  enter positive number for share, Try Again.  " +
            "*********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu. " +
            " *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }


  /**
   * Test to check if user is able to buy stocks successfully.
   */
  @Test
  public void testBuyStock() throws ParseException {
    in = new StringReader("3\ngoog\n2018-10-22\n12\n1\nabc\n1\n12\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT " +
            "****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "No Portfolio, Create a new one.\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is able to create portfolio successfully using buy stock option.
   */
  @Test
  public void testBuyStockCreatePortfolio() throws ParseException {
    in = new StringReader("3\nmsft\n2018-11-14\n12\n1\nabc\n2\nabc\nq\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT" +
            " ****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "No Portfolio, Create a new one.\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Invalid Option, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  For input string: \"abc\", Try Again.  " +
            "*********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  " +
            "*********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is able to view portfolio after creating one.
   */
  @Test
  public void testViewPortfolioAfterCreatePortfolio() throws ParseException {
    in = new StringReader("3\ngoog\n2018-11-14\n10\n2\ntech\n1\n1\n1\n2018-11-15\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
            "******************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t$10\n" +
            "Current Price for a Stock GOOG on 2018-11-15:\t1044.71\n" +
            "Total Valuation for GOOG:\t10447.1\n" +
            "Total Investment for GOOG:\t10500.0\n" +
            "Total Return for GOOG:\t-52.899999999999636\n" +
            "\n" +
            "Total Investment:\t10500.0\n" +
            "Total Portfolio Valuation on 2018-11-15:\t10447.1\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is asked to enter option again when using invalid format.
   */
  @Test
  public void testInvalidOptionFormat() throws ParseException {
    in = new StringReader("-1\na\nab\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
            "******************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "You have entered Invalid Option\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "You have entered Invalid Option\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "You have entered Invalid Option\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is able to view portfolio after creating one.
   */
  @Test
  public void testViewPortfolioAfterCreateMultiplePortfolio() throws ParseException {
    in = new StringReader("3\ngoog\n2018-11-13\n5\n2\nsoftware\n1\n3\nmsft\n2018-11-14\n10" +
            "\n1\n1\n1\n1\n2018-11-15\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ********" +
            "********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. software\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. software\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. software\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tMSFT\n" +
            "Total Number of Shares for MSFT:\t10\n" +
            "Current Price for a Stock MSFT on 2018-11-15:\t104.99\n" +
            "Total Valuation for MSFT:\t1049.8999999999999\n" +
            "Total Investment for MSFT:\t1081.0\n" +
            "Total Return for MSFT:\t-31.100000000000136\n" +
            "\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t5\n" +
            "Current Price for a Stock GOOG on 2018-11-15:\t1044.71\n" +
            "Total Valuation for GOOG:\t5223.55\n" +
            "Total Investment for GOOG:\t5216.45\n" +
            "Total Return for GOOG:\t7.100000000000364\n" +
            "\n" +
            "Total Investment:\t6297.45\n" +
            "Total Portfolio Valuation on 2018-11-15:\t6273.45\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check if user is able to add stock to existing portfolio.
   */
  @Test
  public void testBuyStockExistingPortfolio() throws ParseException {
    in = new StringReader("3\naapl\n2018-11-14\n50\n2\ntech\n1\n3\nmsft\n2018-11-14\n10\n1" +
            "\n1\n1\n1\n2018-11-15\nQ");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
            "******************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tMSFT\n" +
            "Total Number of Shares for MSFT:\t10\n" +
            "Current Price for a Stock MSFT on 2018-11-15:\t104.99\n" +
            "Total Valuation for MSFT:\t1049.8999999999999\n" +
            "Total Investment for MSFT:\t1081.0\n" +
            "Total Return for MSFT:\t-31.100000000000136\n" +
            "\n" +
            "Ticker:\tAAPL\n" +
            "Total Number of Shares for AAPL:\t50\n" +
            "Current Price for a Stock AAPL on 2018-11-15:\t188.39\n" +
            "Total Valuation for AAPL:\t9419.5\n" +
            "Total Investment for AAPL:\t9695.0\n" +
            "Total Return for AAPL:\t-275.5\n" +
            "\n" +
            "Total Investment:\t10776.0\n" +
            "Total Portfolio Valuation on 2018-11-15:\t10469.4\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check portfolio valuation.
   */
  @Test
  public void testPortfolioValuation() throws ParseException {
    in = new StringReader("3\nmsft\n2018-11-08\n25\n2\ntech\n1\n3\ngoog\n2018-11-07\n35\n1" +
            "\n1\n2\n1\n2018-11-15\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
            "*******************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Total Investment:\t40210.0\n" +
            "Total Portfolio Valuation on 2018-11-15:\t39189.6\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }

  /**
   * Test to check portfolio valuation for portfolio with no stock.
   */
  @Test
  public void testPortfolioValuationForPortfolioWithNoStocks() throws ParseException {
    in = new StringReader("4\nabc\n1\n1\n2018-11-15\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ********" +
            "********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. abc\t\t\t\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. abc\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Total Investment:\t0.0\n" +
            "Total Portfolio Valuation on 2018-11-15:\t0.0\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());

  }

  /**
   * Test to check all functionalities.
   */
  @Test
  public void testAllFunctions() throws ParseException {
    in = new StringReader("1\n3\naapl\n2018-11-08\n11\n2\ntech\n1\n1\n1\n2018-11-14\n4\nhealth" +
            "\n2\n1\n2018-11-09\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
            "*******************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            " you have not created any portfolios yet!\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
            "menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. tech\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tAAPL\n" +
            "Total Number of Shares for AAPL:\t11\n" +
            "Current Price for a Stock AAPL on 2018-11-14:\t193.9\n" +
            "Total Valuation for AAPL:\t2132.9\n" +
            "Total Investment for AAPL:\t2309.7799999999997\n" +
            "Total Return for AAPL:\t-176.87999999999965\n" +
            "\n" +
            "Total Investment:\t2309.7799999999997\n" +
            "Total Portfolio Valuation on 2018-11-14:\t2132.9\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Create new portfolio:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. tech\t\t\t2. health\t\t\t\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. tech\t\t\t2. health\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Total Investment:\t2309.7799999999997\n" +
            "Total Portfolio Valuation on 2018-11-09:\t2261.05\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
            "Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());

  }


  /**
   * Test to check all functionalities of fixed investment.
   */
  @Test
  public void testinvestfixedamount() throws ParseException {
    in = new StringReader("6\n4\nabc\n6\n1\n3\ngoog\n2018-11-14\n12\n1\n1\n15\n6\n1\n" +
            "1256\n2018-11-21\n11\n1\n23\n6\n1\n1234\n2018-11-21\n11\n1\n100\n1\n1\n2018-11-21" +
            "\n6\n2\na\n1\nhj\n0\n-12\n12\n2018-11-21\nf\nhjk\n-12\n0\n2\n1\n1\n2018-11-21\n6\n1" +
            "\n23\n2018-11-24\n23\n2\n6\n1\n34\n1954-11-24\n34\n2\n6\nq\n4\nxyz\n6\n2\n4\nq\n3\n" +
            "aapl\n2018-11-13\n34\n1\n2\n21\n6\n2\n34\n2018-11-13\n34\n1\n100\n1\n2\n2018-11-13" +
            "\nq");
    Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT" +
            " ****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "No Portfolio, Create a new one.\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "\n" +
            "\n" +
            "*********************************  Portfolio has no contents. " +
            " *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "1. Assign individual weights \t\t\t  2. Assign equal weights\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\n" +
            "\n" +
            "*********************************  Weights should add up to 100.  " +
            "*********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "1. Assign individual weights \t\t\t  2. Assign equal weights\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t13.189271498925415\n" +
            "Current Price for a Stock GOOG on 2018-11-21:\t$1037.61\n" +
            "Total Valuation for GOOG:\t$13685.319999999998\n" +
            "Total Investment for GOOG:\t$13783.920000000002\n" +
            "Total Return for GOOG:\t$-98.600000000004\n" +
            "\n" +
            "Total Investment:\t$13783.920000000002\n" +
            "Total Portfolio Valuation on 2018-11-21:\t$13685.319999999998\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "\n" +
            "\n" +
            "*********************************  Invalid Option, Try Again.  " +
            "*********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  For input string: \"a\", Try Again. " +
            " *********************************\n" +
            "\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "\n" +
            "*********************************  Fixed amount should be a number, Try Again." +
            "  *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Fixed amount should be a number, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  Fixed amount should be a number, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "\n" +
            "*********************************  enter a number!, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  enter a number!, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "\n" +
            "*********************************  enter a number!, Try Again. " +
            " *********************************\n" +
            "\n" +
            "\n" +
            "1. Assign individual weights \t\t\t  2. Assign equal weights\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t13.200836537812858\n" +
            "Current Price for a Stock GOOG on 2018-11-21:\t$1037.61\n" +
            "Total Valuation for GOOG:\t$13697.319999999998\n" +
            "Total Investment for GOOG:\t$13795.920000000002\n" +
            "Total Return for GOOG:\t$-98.600000000004\n" +
            "\n" +
            "Total Investment:\t$13795.920000000002\n" +
            "Total Portfolio Valuation on 2018-11-21:\t$13697.319999999998\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Portfolio" +
            "\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "1. Assign individual weights \t\t\t  2. Assign equal weights\n" +
            "\n" +
            "\n" +
            "*********************************  Stock Market Closed! It's a Weekend!  " +
            "*********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "1. Assign individual weights \t\t\t  2. Assign equal weights\n" +
            "\n" +
            "\n" +
            "*********************************  Data Not Available for This Date! " +
            " *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu. " +
            " *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks" +
            "\t\t4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "\n" +
            "\n" +
            "*********************************  Portfolio has no contents." +
            "  *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks" +
            "\t\t4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "\n" +
            "*********************************  Returning to Main Menu.  " +
            "*********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks" +
            "\t\t4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
            ":\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t" +
            "\t4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "1. Assign individual weights \t\t\t  2. Assign equal weights\n" +
            "\n" +
            "Enter Weight for AAPL or Press Q to go back to the Main menu\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tAAPL\n" +
            "Total Number of Shares for AAPL:\t34.17687145606825\n" +
            "Current Price for a Stock AAPL on 2018-11-13:\t$192.23\n" +
            "Total Valuation for AAPL:\t$6569.819999999999\n" +
            "Total Investment for AAPL:\t$6624.82\n" +
            "Total Return for AAPL:\t$-55.00000000000091\n" +
            "\n" +
            "Total Investment:\t$6624.82\n" +
            "Total Portfolio Valuation on 2018-11-13:\t$6569.819999999999\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t" +
            "\t4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }


  /**
   * Test to check all functionalities of Dollar cost averaging.
   */
  @Test
  public void testDCA() throws ParseException {in = new StringReader("5\n4\nabc\n5\n1\n3" +
          "\ngoog" +
          "\n2018-11-13\n1\n1\n1\n1\n1\n1\n2018-11-13\n5\n1\n1234\n2018-11-21\n1" +
          "\n2018-12-21\n2\n1\n1\n5\n1\n1234\n2018-11-21\n1\n2018-12-21\n2\n1" +
          "\n100\n1\n1\n2018-11-28\n5\n1\n1234\n2018-11-22\n1\n2018-12-21\n2\n12" +
          "\n100\n4\nxyz\n3\ngoog\n2018-12-21\n23\n1\n2\n12\n3\ngoog\n2018-10-13" +
          "\n23\n1\n2\n12\n3\ngoog\n2018-10-22\n23\n1\n2\n12\n5\n2\n1234\n2018-11-13" +
          "\n1\n2018-10-29\n2\n12\n100\n5\n2\n1234\n2018-11-13\n1\n2018-11-15\n1\n1" +
          "\n100\n1\n2\n2018-11-15\n5\n2\n2134\n2018-11-17\n2\n2\n1\n100\n1\n2\n2018-11-28\nq");
          Appendable out = new StringBuffer();
    iv = new InvestmentView(out);
    smc = new NewController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t\t\t\t\t\t******************************* WELCOME TO INVESTMENT" +
            " ****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create" +
            " new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "No Portfolio, Create a new one.\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create" +
            " new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create" +
            " new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "\n" +
            "\n" +
            "*********************************  Portfolio has no contents.  " +
            "*********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t" +
            "\t4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to " +
            "the Main menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create" +
            " new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t1.0\n" +
            "Current Price for a Stock GOOG on 2018-11-13:\t$1036.05\n" +
            "Total Valuation for GOOG:\t$1036.05\n" +
            "Total Investment for GOOG:\t$1037.05\n" +
            "Total Return for GOOG:\t$-1.0\n" +
            "\n" +
            "Total Investment:\t$1037.05\n" +
            "Total Portfolio Valuation on 2018-11-13:\t$1036.05\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4." +
            "Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "1. Provide end date \t\t\t  2. Allow ongoing strategy\n" +
            "\n" +
            "Enter End Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Frequency (in days) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\n" +
            "\n" +
            "*********************************  Weights should add up to 100.  *****" +
            "****************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new P" +
            "ortfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "1. Provide end date \t\t\t  2. Allow ongoing strategy\n" +
            "\n" +
            "Enter End Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Frequency (in days) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new P" +
            "ortfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t5.714545673139279\n" +
            "Current Price for a Stock GOOG on 2018-11-28:\t$1079.36\n" +
            "Total Valuation for GOOG:\t$6168.052017759612\n" +
            "Total Investment for GOOG:\t$5977.05\n" +
            "Total Return for GOOG:\t$191.0020177596116\n" +
            "\n" +
            "Total Investment:\t$5977.05\n" +
            "Total Portfolio Valuation on 2018-11-28:\t$6168.052017759612\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create ne" +
            "w Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "1. Provide end date \t\t\t  2. Allow ongoing strategy\n" +
            "\n" +
            "Enter End Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Frequency (in days) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\n" +
            "\n" +
            "*********************************  strategy is ongoing  ***************" +
            "******************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4." +
            "Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter the portfolio name:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Creat" +
            "e new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to th" +
            "e Main menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "\n" +
            "*********************************  Date passed is in the future.  *******" +
            "**************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new Port" +
            "folio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the " +
            "Main menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "\n" +
            "*********************************  Stock Market Closed! It's a Weekend! " +
            " *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create ne" +
            "w Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "Enter Ticker or Press Q to go back to the Main menu:\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Number of Stocks you want to purchase or Press Q to go back to the Ma" +
            "in menu:\n" +
            "\n" +
            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Thank you for buying these stocks!\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new P" +
            "ortfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "1. Provide end date \t\t\t  2. Allow ongoing strategy\n" +
            "\n" +
            "Enter End Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Frequency (in days) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\n" +
            "\n" +
            "*********************************  Start date cannot be after end date or" +
            " end date cannot be before start date  *********************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Create new" +
            " Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "1. Provide end date \t\t\t  2. Allow ongoing strategy\n" +
            "\n" +
            "Enter End Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "Enter Frequency (in days) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t26.532440652562208\n" +
            "Current Price for a Stock GOOG on 2018-11-15:\t$1064.71\n" +
            "Total Valuation for GOOG:\t$28249.35488718951\n" +
            "Total Investment for GOOG:\t$29043.68\n" +
            "Total Return for GOOG:\t$-794.3251128104894\n" +
            "\n" +
            "Total Investment:\t$29043.68\n" +
            "Total Portfolio Valuation on 2018-11-15:\t$28249.35488718951\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "Choose portfolio to apply strategy on:\n" +
            "Enter fixed amount to invest in a portfolio:\n" +
            "\n" +
            "Enter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "\n" +
            "1. Provide end date \t\t\t  2. Allow ongoing strategy\n" +
            "\n" +
            " The strategy will be ongoing\n" +
            "\n" +
            "Enter Frequency (in days) or Press Q to go back to the Main menu\n" +
            "Enter commission for this transaction:\n" +
            "\n" +
            "Enter Weight for GOOG or Press Q to go back to the Main menu\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t" +
            "4.Create new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            "\n" +
            "1. ABC\t\t\t2. XYZ\t\t\t\n" +
            "\n" +
            "\n" +
            "Enter the one of the above options!\n" +
            "\n" +
            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
            "Ticker:\tGOOG\n" +
            "Total Number of Shares for GOOG:\t36.77762868749468\n" +
            "Current Price for a Stock GOOG on 2018-11-28:\t$1079.36\n" +
            "Total Valuation for GOOG:\t$39696.301300134255\n" +
            "Total Investment for GOOG:\t$39718.68\n" +
            "Total Return for GOOG:\t$-22.378699865745148\n" +
            "\n" +
            "Total Investment:\t$39718.68\n" +
            "Total Portfolio Valuation on 2018-11-28:\t$39696.301300134255\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t2.Portfolio Valuation\t\t3.Buy Stocks\t\t4.Creat" +
            "e new Portfolio\t\t5.Dollar Cost Average\t\t6.Invest Fixed Amount\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }


}