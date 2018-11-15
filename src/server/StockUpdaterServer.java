package server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;

public class StockUpdaterServer implements Runnable {


  private List<String> listOfTicker = new ArrayList<String>(){{
    add("GOOG");
    add("AAPL");
    add("MSFT");
    add("AMZN");
    add("BABA");
    add("BA");
    add("IBM");
    add("AES");
    add("AET");
    add("INFY");
    add("TWTR");
    add("ORCL");
    add("WMT");
    add("DIS");
    add("MCD");
    add("BAC");
  }};



  /**
   * When an object implementing interface <code>Runnable</code> is used to create a thread,
   * starting the thread causes the object's
   * <code>run</code> method to be called in that separately executing
   * thread.
   * <p>
   * The general contract of the method <code>run</code> is that it may take any action whatsoever.
   *
   * @see Thread#run()
   */
  @Override
  public void run() {


    IStockMarketSimulation sm = StockMarketSimulation.getInstance();

    for(String tickerSymbol: listOfTicker) {

        //sm.updateListing(tickerSymbol);
      try {
        TimeUnit.SECONDS.sleep(30);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

  }
}
