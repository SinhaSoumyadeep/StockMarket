package manager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import properties.PropertiesLoader;
import service.StockMarketDAO;
import service.StockMarketDAOFactory;
import service.StockMarketDAOImpl;

/**
 * This Class adds a level of abstraction or facade to the StockMarket DAO.
 */
public class StockMarketServiceManager {

  private StockMarketDAO dao;

  /**
   * This method initialize the DAO object.
   */
  public StockMarketServiceManager() {

    PropertiesLoader load = new PropertiesLoader();
    dao = StockMarketDAOFactory.getStockMarketDAO(load.getValue("DATABASE"));
  }

  /**
   * This method is used to get Company Listing form the DAO Object.
   *
   * @param ticker the ticker symbol passed.
   * @return the company listing.
   */
  public String getCompanyListing(String ticker) {
    return dao.getCompanyListing(ticker);
  }

}
