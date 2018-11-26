//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.io.StringReader;
//import java.text.ParseException;
//
//import controller.IStockMarketController;
////import controller.StockMarketController;
//import model.InvestmentModel;
//import view.InvestmentView;
//
//import static org.junit.Assert.assertEquals;
//
//public class StockMarketControllerTest {
//
//  private Readable in;
//  private InvestmentModel im;
//  private InvestmentView iv;
//  private IStockMarketController smc;
//
//  @Before
//  public void setUp() throws IOException {
//
//    im = new InvestmentModel();
//
//  }
//
//  /**
//   * Test to check whether exception is thrown when readable is null.
//   */
//
//  @Test
//  public void testNullReadable() {
//    try {
//      Appendable out = new StringBuffer();
//      iv = new InvestmentView(out);
//      smc = new StockMarketController(null, iv, im);
//    } catch (Exception e) {
//      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
//    }
//
//  }
//
//  /**
//   * Test to check whether exception is thrown when view is null.
//   */
//
//  @Test
//  public void testNullView() {
//    try {
//      Appendable out = new StringBuffer();
//      iv = new InvestmentView(out);
//      smc = new StockMarketController(in, null, im);
//    } catch (Exception e) {
//      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
//    }
//
//  }
//
//  /**
//   * Test to check whether exception is thrown when model is null.
//   */
//
//  @Test
//  public void testNullModel() {
//    try {
//      Appendable out = new StringBuffer();
//      iv = new InvestmentView(out);
//      smc = new StockMarketController(in, iv, null);
//    } catch (Exception e) {
//      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
//    }
//
//  }
//
//  /**
//   * Test to check whether exception is thrown when model is null.
//   */
//
//  @Test
//  public void testNullAppendable() {
//    try {
//      iv = new InvestmentView(null);
//    } catch (Exception e) {
//      assertEquals("Appendable cannot be null.", e.getMessage());
//    }
//
//  }
//
//  /**
//   * Test to check whether exception is thrown when readble, view and model are null.
//   */
//
//  @Test
//  public void testNullReadableViewModel() {
//    try {
//      Appendable out = new StringBuffer();
//      iv = new InvestmentView(out);
//      smc = new StockMarketController(null, null, null);
//    } catch (Exception e) {
//      assertEquals("Readable or View or Model cannot be Null.", e.getMessage());
//    }
//
//  }
//
//  /**
//   * Test to check whether correct menu is displayed when startStockMarket method is called.
//   */
//  @Test
//  public void testMenu() throws ParseException {
//    in = new StringReader("q");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    System.out.println(out.toString());
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *************" +
//            "***************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//
//  /**
//   * Test to check if correct output is displayed when user tries to create portfolio when no
//   * portfolio is created.
//   */
//  @Test
//  public void testFirstOption() throws ParseException {
//    in = new StringReader("1\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            " you have not created any portfolios yet!\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if correct output is displayed when user chooses to view portfolio valuation
//   * before adding any stocks.
//   */
//  @Test
//  public void testPortfolioValuationAfterStartingStockMarket() throws ParseException {
//    in = new StringReader("2\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    System.out.println(out.toString());
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            " you have not created any portfolios yet!\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter date again when trying to enter alphabets in date
//   * field.
//   */
//  @Test
//  public void testBuyStockInvalidDate1() throws ParseException {
//    in = new StringReader("3\ngoog\nabc\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    System.out.println(out.toString());
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ***********" +
//            "***" +
//            "**************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Entered date is invalid.\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter date again when trying to enter invalid date.
//   */
//  @Test
//  public void testBuyStockInvalidDate2() throws ParseException {
//    in = new StringReader("3\ngoog\n11-12-2018\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    System.out.println(out.toString());
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
//            "****" +
//            "**************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Entered date is invalid.\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if valid date is accepted.
//   */
//  @Test
//  public void testBuyStockValidDate() throws ParseException {
//    in = new StringReader("3\nmsft\n2018-11-14\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    System.out.println(out.toString());
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
//            ":\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter number of shares again when entering 0.
//   */
//  @Test
//  public void testBuyStockZeroShares() throws ParseException {
//    in = new StringReader("3\nmsft\n2018-11-14\n0\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
//            "******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
//            ":\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main menu" +
//            ":\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter number of shares again when entering negative integer.
//   */
//  @Test
//  public void testBuyStockNegativeShares() throws ParseException {
//    in = new StringReader("3\ngoog\n2018-11-14\n-10\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter number of shares again when entering decimals.
//   */
//  @Test
//  public void testBuyStockDecimalShares() throws ParseException {
//    in = new StringReader("3\ngoog\n2018-11-14\n1.5\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ************" +
//            "****************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "Shares should be an Integer.\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter number of shares again when entering decimals.
//   */
//  @Test
//  public void testBuyStockNonNumericShares() throws ParseException {
//    in = new StringReader("3\ngoog\n2018-11-14\nab\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
//            "******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "Shares should be an Integer.\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//
//  /**
//   * Test to check if user is able to buy stocks successfully.
//   */
//  @Test
//  public void testBuyStock() throws ParseException {
//    in = new StringReader("3\ngoog\n2018-11-14\n14\n2\nabc1\n1\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. abc1\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is able to create portfolio successfully using buy stock option.
//   */
//  @Test
//  public void testBuyStockCreatePortfolio() throws ParseException {
//    in = new StringReader("3\nmsft\n2018-11-14\n10\ntech\n2\ntech\nq\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ******" +
//            "**********************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "You have entered Invalid Option\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is able to view portfolio after creating one.
//   */
//  @Test
//  public void testViewPortfolioAfterCreatePortfolio() throws ParseException {
//    in = new StringReader("3\ngoog\n2018-11-14\n10\n2\ntech\n1\n1\n1\n2018-11-15\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
//            "******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Ticker:\tGOOG\n" +
//            "Total Number of Shares for GOOG:\t10\n" +
//            "Current Price for a Stock GOOG on 2018-11-15:\t1044.71\n" +
//            "Total Valuation for GOOG:\t10447.1\n" +
//            "Total Investment for GOOG:\t10500.0\n" +
//            "Total Return for GOOG:\t-52.899999999999636\n" +
//            "\n" +
//            "Total Investment:\t10500.0\n" +
//            "Total Portfolio Valuation on 2018-11-15:\t10447.1\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is asked to enter option again when using invalid format.
//   */
//  @Test
//  public void testInvalidOptionFormat() throws ParseException {
//    in = new StringReader("-1\na\nab\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
//            "******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "You have entered Invalid Option\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "You have entered Invalid Option\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "You have entered Invalid Option\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is able to view portfolio after creating one.
//   */
//  @Test
//  public void testViewPortfolioAfterCreateMultiplePortfolio() throws ParseException {
//    in = new StringReader("3\ngoog\n2018-11-13\n5\n2\nsoftware\n1\n3\nmsft\n2018-11-14\n10" +
//            "\n1\n1\n1\n1\n2018-11-15\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ********" +
//            "********************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. software\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "1. software\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. software\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Ticker:\tMSFT\n" +
//            "Total Number of Shares for MSFT:\t10\n" +
//            "Current Price for a Stock MSFT on 2018-11-15:\t104.99\n" +
//            "Total Valuation for MSFT:\t1049.8999999999999\n" +
//            "Total Investment for MSFT:\t1081.0\n" +
//            "Total Return for MSFT:\t-31.100000000000136\n" +
//            "\n" +
//            "Ticker:\tGOOG\n" +
//            "Total Number of Shares for GOOG:\t5\n" +
//            "Current Price for a Stock GOOG on 2018-11-15:\t1044.71\n" +
//            "Total Valuation for GOOG:\t5223.55\n" +
//            "Total Investment for GOOG:\t5216.45\n" +
//            "Total Return for GOOG:\t7.100000000000364\n" +
//            "\n" +
//            "Total Investment:\t6297.45\n" +
//            "Total Portfolio Valuation on 2018-11-15:\t6273.45\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check if user is able to add stock to existing portfolio.
//   */
//  @Test
//  public void testBuyStockExistingPortfolio() throws ParseException {
//    in = new StringReader("3\naapl\n2018-11-14\n50\n2\ntech\n1\n3\nmsft\n2018-11-14\n10\n1" +
//            "\n1\n1\n1\n2018-11-15\nQ");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
//            "******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Ticker:\tMSFT\n" +
//            "Total Number of Shares for MSFT:\t10\n" +
//            "Current Price for a Stock MSFT on 2018-11-15:\t104.99\n" +
//            "Total Valuation for MSFT:\t1049.8999999999999\n" +
//            "Total Investment for MSFT:\t1081.0\n" +
//            "Total Return for MSFT:\t-31.100000000000136\n" +
//            "\n" +
//            "Ticker:\tAAPL\n" +
//            "Total Number of Shares for AAPL:\t50\n" +
//            "Current Price for a Stock AAPL on 2018-11-15:\t188.39\n" +
//            "Total Valuation for AAPL:\t9419.5\n" +
//            "Total Investment for AAPL:\t9695.0\n" +
//            "Total Return for AAPL:\t-275.5\n" +
//            "\n" +
//            "Total Investment:\t10776.0\n" +
//            "Total Portfolio Valuation on 2018-11-15:\t10469.4\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check portfolio valuation.
//   */
//  @Test
//  public void testPortfolioValuation() throws ParseException {
//    in = new StringReader("3\nmsft\n2018-11-08\n25\n2\ntech\n1\n3\ngoog\n2018-11-07\n35\n1" +
//            "\n1\n2\n1\n2018-11-15\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Total Investment:\t40210.0\n" +
//            "Total Portfolio Valuation on 2018-11-15:\t39189.6\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//  }
//
//  /**
//   * Test to check portfolio valuation for portfolio with no stock.
//   */
//  @Test
//  public void testPortfolioValuationForPortfolioWithNoStocks() throws ParseException {
//    in = new StringReader("4\nabc\n1\n1\n2018-11-15\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ********" +
//            "********************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. abc\t\t\t\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. abc\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Total Investment:\t0.0\n" +
//            "Total Portfolio Valuation on 2018-11-15:\t0.0\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//
//  }
//
//  /**
//   * Test to check all functionalities.
//   */
//  @Test
//  public void testAllFunctions() throws ParseException {
//    in = new StringReader("1\n3\naapl\n2018-11-08\n11\n2\ntech\n1\n1\n1\n2018-11-14\n4\nhealth" +
//            "\n2\n1\n2018-11-09\nq");
//    Appendable out = new StringBuffer();
//    iv = new InvestmentView(out);
//    smc = new StockMarketController(in, iv, im);
//    smc.startStockMarket();
//    assertEquals("\t\t******************************* WELCOME TO INVESTMENT *********" +
//            "*******************************\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            " you have not created any portfolios yet!\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Enter Ticker or Press Q to go back to the Main menu:\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "\n" +
//            "Enter Number of Stocks you want to purchase or Press Q to go back to the Main " +
//            "menu:\n" +
//            "\n" +
//            "1. Add to existing portfolio \t\t\t  2. Create a new one\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Thank you for buying these stocks!\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. tech\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Ticker:\tAAPL\n" +
//            "Total Number of Shares for AAPL:\t11\n" +
//            "Current Price for a Stock AAPL on 2018-11-14:\t193.9\n" +
//            "Total Valuation for AAPL:\t2132.9\n" +
//            "Total Investment for AAPL:\t2309.7799999999997\n" +
//            "Total Return for AAPL:\t-176.87999999999965\n" +
//            "\n" +
//            "Total Investment:\t2309.7799999999997\n" +
//            "Total Portfolio Valuation on 2018-11-14:\t2132.9\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "Create new portfolio:\n" +
//            "\n" +
//            "Enter the portfolio name:\n" +
//            "\n" +
//            "1. tech\t\t\t2. health\t\t\t\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n" +
//            "\n" +
//            "1. tech\t\t\t2. health\t\t\t\n" +
//            "\n" +
//            "\n" +
//            "Enter the one of the above options!\n" +
//            "\n" +
//            "Enter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n" +
//            "Total Investment:\t2309.7799999999997\n" +
//            "Total Portfolio Valuation on 2018-11-09:\t2261.05\n" +
//            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
//            "\n" +
//            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new " +
//            "Portfolio\n" +
//            "\n" +
//            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
//            "\n" +
//            "Enter your option:\n", out.toString());
//
//  }
//
//
//}