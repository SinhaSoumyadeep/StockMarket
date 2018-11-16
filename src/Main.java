
import java.io.InputStreamReader;
import java.text.ParseException;

import controller.IStockMarketController;
import controller.StockMarketController;
import model.InvestmentModel;
import model.InvestmentModelInterface;
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

    InvestmentModelInterface im = new InvestmentModel();
    InvestmentViewInterface iv = new InvestmentView(System.out);

    IStockMarketController sm = new StockMarketController(new InputStreamReader(System.in), iv, im);
    try {
      sm.startStockMarket();
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }


}

