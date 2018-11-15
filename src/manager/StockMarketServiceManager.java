package manager;

import service.StockMarketDAO;
import service.StockMarketDAOImpl;

public class StockMarketServiceManager {

  private StockMarketDAO dao;

  public StockMarketServiceManager()
  {
    dao = new StockMarketDAOImpl();
  }

  public String getCompanyListing(String ticker)
  {
    return dao.getCompanyListing(ticker);
  }

}
