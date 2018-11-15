package model;

import transferable.PortfolioTransferable;

public interface IPortfolio {

  void addStocksToPortfolio(Stock newStock);

  PortfolioTransferable valuationForPortfolio(String timestamp);
}
