package service;

public class StockMarketDAOFactory {

  public static StockMarketDAO getStockMarketDAO(String type)
  {
    if(type.equals("API"))
    {
      return new StockMarketDAOImpl();
    }else{
      return new StockMarketDAOImpl();
    }
  }
}
