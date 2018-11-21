package model;

import java.text.ParseException;
import java.util.HashMap;

import service.IStockMarketSimulation;
import service.StockMarketSimulation;

public class InvestmentModelNew extends InvestmentModel implements InvestModelInterfaceNew {

    private HashMap<String,PortfolioWallet> wallet;
    private HashMap<String,WeightsOfPortfolio> listOfWeights;



    public InvestmentModelNew()
    {
      wallet = new HashMap<String,PortfolioWallet>();
      listOfWeights = new HashMap<String,WeightsOfPortfolio>();

    }

    @Override
    public void buyStocks(String ticker, String timeStamp, Integer noOfShares, String portfolioName)
            throws IllegalArgumentException, ParseException {





    }

  @Override
  public void investStocks(String portfolioName, Double fixedAmount, HashMap<String, Double> weights, String timeStamp) throws ParseException {
    IPortfolio investingPortfolio = this.listOfPortfolio.get(portfolioName);
    this.listOfWeights.put(portfolioName,new WeightsOfPortfolio(weights));
    for (String key : investingPortfolio.getStockNamesInPortfolio()) {
      Double moneyForEachStock = (weights.get(key) / 100) * fixedAmount;
      investStockhelper(moneyForEachStock,key,portfolioName,timeStamp);
    }


  }

  @Override
  public void investStocks(String portfolioName, Double fixedAmount, String timeStamp) throws ParseException {

    IPortfolio investingPortfolio = this.listOfPortfolio.get(portfolioName);

    Double moneyForEachStock = fixedAmount / investingPortfolio.getStockNamesInPortfolio().size();

    for (String key : investingPortfolio.getStockNamesInPortfolio()) {

        investStockhelper(moneyForEachStock,key,portfolioName,timeStamp);
    }

  }


  private void investStockhelper(Double moneyForEachStock, String key, String portfolioName, String timeStamp) throws ParseException {
    IStockMarketSimulation stockMarket = StockMarketSimulation.getInstance();
    Double currentStockPrice = stockMarket.priceOfAStockAtACertainDate(key, timeStamp);


    Double partialNumberOfShares = (moneyForEachStock/currentStockPrice);
    Integer wholeShares = partialNumberOfShares.intValue();

    Double remaningAmountInWallet = moneyForEachStock - (wholeShares*currentStockPrice);

    System.out.println("ticker: "+key);
    System.out.println("money available for each stock: "+moneyForEachStock);
    System.out.println("current stock Price: "+currentStockPrice);
    System.out.println("whole shares: "+wholeShares);
    System.out.println("the remaining amount in wallet :"+remaningAmountInWallet);

    if(wallet.containsKey(portfolioName))
    {
      remaningAmountInWallet = wallet.get(portfolioName).getRemainingAmount()+remaningAmountInWallet;
      wallet.put(portfolioName,new PortfolioWallet(remaningAmountInWallet));
    }
    else{
      wallet.put(portfolioName,new PortfolioWallet(remaningAmountInWallet));
    }



    buyStocks(key,timeStamp,wholeShares,portfolioName);
  }

  public String toString()
  {
    return listOfPortfolio+"\n"+wallet;
  }
}
