
import java.io.InputStreamReader;
import java.text.ParseException;


import controller.IStockMarketController;
import controller.NewController;
import model.InvestModelInterfaceNew;
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
  public static void main(String[] args) throws ParseException {

    InvestModelInterfaceNew im = new InvestmentModelNew();

    InvestmentViewInterface iv = new InvestmentView(System.out);

    IStockMarketController sm = new NewController(new InputStreamReader(System.in), iv, im);
    try {
      sm.startStockMarket();
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }


}

