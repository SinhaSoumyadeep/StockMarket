
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;

import controller.IStockMarketController;
import controller.NewController;
//import controller.StockMarketController;
import model.DollarCostAverageStrategy;
import model.InvestModelInterfaceNew;
import model.InvestmentModel;
import model.InvestmentModelInterface;
import model.InvestmentModelNew;
import model.InvestmentStrategyInterface;
import transferable.PortfolioTransferable;
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
    try {
      im.buyStocks("aapl", "2018-10-22", 10, "abc","10");
      im.buyStocks("GOOG", "2018-10-22", 100, "abc","100");
    } catch (ParseException e) {
      e.printStackTrace();
    }
//
    HashMap<String,Double> weights = new HashMap<String,Double>(){{
      put("aapl",60.0);
      put("GOOG",40.0);
    }};
//    im.investStocks("abc",5000.0,weights,"2018-10-15");
//
//
    InvestmentStrategyInterface i = new DollarCostAverageStrategy(5000.0,"2018-11-21","2019-01-21",2,"10" );
    im.registerStrategy(i,"abc",weights);
    im.evaluatePortfolio("abc","2018-11-26");
    System.out.println(im);


    /*InvestmentModelInterface im = new InvestmentModelNew();
    InvestmentViewInterface iv = new InvestmentView(System.out);

//    IStockMarketController sm = new StockMarketController(new InputStreamReader(System.in), iv, im);
      IStockMarketController sm = new NewController(new InputStreamReader(System.in), iv, im);
    try {
      sm.startStockMarket();
    } catch (ParseException e) {
      e.printStackTrace();
    }*/

  }


}

