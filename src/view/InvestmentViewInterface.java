package view;


import java.io.IOException;
import java.util.List;

import transferable.PortfolioTransferable;
import utility.Options;

public interface InvestmentViewInterface {

  void displayPortfolio(PortfolioTransferable pt, Options options) throws IOException;

  void displayAllPortfolioNames(List<String> portfolioNames) throws IOException;

  void viewIntroMessage() throws IOException;

  public void viewWelcomeMessage() throws IOException;

  void viewEnterPortfolioName() throws IOException;

  void viewEnterTicker() throws IOException;

  void viewEnterNumberOfShares() throws IOException;

  void viewBuyStockAcknowledgement() throws IOException;

  void viewEnterDate() throws IOException;

  void viewErrorInvalidDate() throws IOException;

  void viewErrorSharesInFraction() throws IOException;

  void enterFixedAmount() throws IOException;

  void choosePortfolioToInvest() throws IOException;

  void viewEnterStartDate() throws IOException;

  void viewEnterEndDate() throws IOException;

  void viewEnterFrequency() throws IOException;

  void choosePortfolioForStrategy() throws IOException;

  void viewEnterWeight(String stock) throws IOException;

  void chooseWeightOption() throws IOException;

  /**
   * Print exceptions.
   *
   * @param e the e
   * @throws IOException the io exception
   */
  public void printExceptions(String e) throws IOException;

  /**
   * Enter options.
   *
   * @throws IOException the io exception
   */
  public void enterOptions() throws IOException;

  /**
   * View Choose portfolio.
   *
   * @throws IOException the io exception
   */
  public void choosePortfolio() throws IOException;

  /**
   * View Create new portfolio.
   *
   * @throws IOException the io exception
   */
  public void createNewPortfolio() throws IOException;

  /**
   * View Enter portfolio.
   *
   * @throws IOException the io exception
   */
  public void enterPortfolio() throws IOException;

  /**
   * View Invalid option.
   *
   * @throws IOException the io exception
   */
  public void invalidOption() throws IOException;

  public void noPortfolio() throws IOException;

  public void enterCommission() throws IOException;

}
