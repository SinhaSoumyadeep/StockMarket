import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;

import controller.IStockMarketController;
import controller.StockMarketController;
import model.InvestmentModel;
import view.InvestmentView;

import static org.junit.Assert.assertEquals;

public class StockMarketControllerTest {

  private Readable in;
  private InvestmentModel im;
  private InvestmentView iv;
  private IStockMarketController smc;

  @Before
  public void setUp() throws IOException {

    im = new InvestmentModel();

  }

  /**
   * Test to check whether exception is thrown when readable is null.
   */

  @Test
  public void testNullReadable() {
    try {
      Appendable out = new StringBuffer();
      iv = new InvestmentView(out);
      smc = new StockMarketController(null, iv, im);
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
      smc = new StockMarketController(in, null, im);
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
      smc = new StockMarketController(in, iv, null);
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
      smc = new StockMarketController(null, null, null);
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
    smc = new StockMarketController(in, iv, im);
    smc.startStockMarket();
    System.out.println(out.toString());
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT ****************************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new Portfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
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
    smc = new StockMarketController(in, iv, im);
    smc.startStockMarket();
    assertEquals("\t\t******************************* WELCOME TO INVESTMENT **********" +
            "******************************\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new Port" +
            "folio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n" +
            " you have not created any portfolios yet!\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n" +
            "\n" +
            "1.View Portfolio\t\t\t2.Portfolio Valuation\t\t\t3.Buy Stocks\t\t\t4.Create new Por" +
            "tfolio\n" +
            "\n" +
            "\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n" +
            "\n" +
            "Enter your option:\n", out.toString());
  }


}