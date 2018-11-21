
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;

import controller.IStockMarketController;
import controller.StockMarketController;
import model.InvestModelInterfaceNew;
import model.InvestmentModel;
import model.InvestmentModelInterface;
import model.InvestmentModelNew;
import view.InvestmentView;
import view.InvestmentViewInterface;

/**
 * This is the driver class for the stock market program.
 */
public class Main {

  /**
   * Main.
   *
   * @param args the args
   */
  public static void main(String[] args) {

    InvestModelInterfaceNew im = new InvestmentModelNew();
    try {
      im.buyStocks("aapl", "2018-11-13", 10, "abc");
      im.buyStocks("GOOG", "2018-11-13", 100, "abc");
    } catch (ParseException e) {
      e.printStackTrace();
    }
    im.investStocks("abc",5000.0,"2018-11-13");


    /*InvestmentModelInterface im = new InvestmentModel();
    InvestmentViewInterface iv = new InvestmentView(System.out);

    IStockMarketController sm = new StockMarketController(new InputStreamReader(System.in), iv, im);
    try {
      sm.startStockMarket();
    } catch (ParseException e) {
      e.printStackTrace();
    }*/
  }


}

