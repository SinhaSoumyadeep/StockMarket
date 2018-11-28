package view;


import java.io.IOException;
import java.util.List;

import transferable.PortfolioTransferable;
import utility.Options;

/**
 * The interface Investment view interface.
 */
public interface InvestmentViewInterface {

  /**
   * Display portfolio.
   *
   * @param pt      the pt
   * @param options the options
   * @throws IOException the io exception
   */
  void displayPortfolio(PortfolioTransferable pt, Options options) throws IOException;

  /**
   * Display all portfolio names.
   *
   * @param portfolioNames the portfolio names
   * @throws IOException the io exception
   */
  void displayAllPortfolioNames(List<String> portfolioNames) throws IOException;

  /**
   * View intro message.
   *
   * @throws IOException the io exception
   */
  void viewIntroMessage() throws IOException;

  /**
   * View welcome message.
   *
   * @throws IOException the io exception
   */
  public void viewWelcomeMessage() throws IOException;

  /**
   * View enter portfolio name.
   *
   * @throws IOException the io exception
   */
  void viewEnterPortfolioName() throws IOException;

  /**
   * View enter ticker.
   *
   * @throws IOException the io exception
   */
  void viewEnterTicker() throws IOException;

  /**
   * View enter number of shares.
   *
   * @throws IOException the io exception
   */
  void viewEnterNumberOfShares() throws IOException;

  /**
   * View buy stock acknowledgement.
   *
   * @throws IOException the io exception
   */
  void viewBuyStockAcknowledgement() throws IOException;

  /**
   * View enter date.
   *
   * @throws IOException the io exception
   */
  void viewEnterDate() throws IOException;

  /**
   * View error invalid date.
   *
   * @throws IOException the io exception
   */
  void viewErrorInvalidDate() throws IOException;

  /**
   * View error shares in fraction.
   *
   * @throws IOException the io exception
   */
  void viewErrorSharesInFraction() throws IOException;

  /**
   * Enter fixed amount.
   *
   * @throws IOException the io exception
   */
  void enterFixedAmount() throws IOException;

  /**
   * Choose portfolio to invest.
   *
   * @throws IOException the io exception
   */
  void choosePortfolioToInvest() throws IOException;

  /**
   * View enter start date.
   *
   * @throws IOException the io exception
   */
  void viewEnterStartDate() throws IOException;

  /**
   * View enter end date.
   *
   * @throws IOException the io exception
   */
  void viewEnterEndDate() throws IOException;

  /**
   * View enter frequency.
   *
   * @throws IOException the io exception
   */
  void viewEnterFrequency() throws IOException;

  /**
   * Choose portfolio for strategy.
   *
   * @throws IOException the io exception
   */
  void choosePortfolioForStrategy() throws IOException;

  /**
   * View enter weight.
   *
   * @param stock the stock
   * @throws IOException the io exception
   */
  void viewEnterWeight(String stock) throws IOException;

  /**
   * Choose weight option.
   *
   * @throws IOException the io exception
   */
  void chooseWeightOption() throws IOException;

  /**
   * Choose end date option.
   *
   * @throws IOException the io exception
   */
  void chooseEndDateOption() throws IOException;

  /**
   * Ongoing end date.
   *
   * @throws IOException the io exception
   */
  void ongoingEndDate() throws IOException;

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

  /**
   * No portfolio.
   *
   * @throws IOException the io exception
   */
  public void noPortfolio() throws IOException;

  /**
   * Enter commission.
   *
   * @throws IOException the io exception
   */
  public void enterCommission() throws IOException;

}
