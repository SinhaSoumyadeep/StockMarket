package service;

/**
 * This is a StockMarket DAo factory pattern which will make our application flexible to use
 * multiple api.
 */
public class StockMarketDAOFactory {

  /**
   * this static method is used to point to the required database. currently it points to the Alpha
   * Vantage API
   *
   * @param type which type of API is required.
   * @return the corresponding DAO object against the type passed.
   */
  public static StockMarketDAO getStockMarketDAO(String type) {
    if (type.equals("API")) {
      return new StockMarketDAOImpl();
    } else {
      return new StockMarketDAOImpl();
    }
  }
}
