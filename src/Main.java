
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
import model.Portfolio;
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

    /*InvestModelInterfaceNew im = new InvestmentModelNew();
    try {
//      im.buyStocks("aapl", "2018-10-22", 10, "abc","10");
      im.buyStocks("aapl", "2018-10-22", 100.0, "abc","100");
      im.buyStocks("GOOG", "2018-10-22", 100.0, "abc","100");
      im.buyStocks("msft", "2018-10-22", 100.0, "abc","100");
    } catch (ParseException e) {
      e.printStackTrace();
    }
//
    HashMap<String,Double> weights = new HashMap<String,Double>(){{
      put("aapl",60.0);
      put("GOOG",20.0);
      put("msft",20.0);
    }};
    im.investStocks("abc",10000.0,weights,"2018-10-22","20");
//
//
    InvestmentStrategyInterface i = new DollarCostAverageStrategy(5000.0,"2018-11-21","2018-11-23",1,"10" );

    im.registerStrategy(i,"abc",weights);*/
/*    InvestmentStrategyInterface il = new DollarCostAverageStrategy(5000.0,"2018-11-24","2018-11-29",1,"10" );
    im.registerStrategy(il,"abc",weights);

    InvestmentStrategyInterface il2 = new DollarCostAverageStrategy(5000.0,"2018-11-25","2018-12-30",1,"10" );
    im.registerStrategy(il2,"abc",weights);*/
//    System.out.println(im.evaluatePortfolio("abc","2018-11-28"));


//    System.out.println(im);


    InvestModelInterfaceNew im = new InvestmentModelNew();

    InvestmentViewInterface iv = new InvestmentView(System.out);

//    IStockMarketController sm = new StockMarketController(new InputStreamReader(System.in), iv, im);
      IStockMarketController sm = new NewController(new InputStreamReader(System.in), iv, im);
    try {
      sm.startStockMarket();
    } catch (ParseException e) {
      e.printStackTrace();
    }

  }


}

