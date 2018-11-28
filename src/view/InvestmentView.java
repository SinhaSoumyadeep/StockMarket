package view;

import java.io.IOException;
import java.util.List;

import transferable.PortfolioTransferable;
import transferable.StockTransferable;
import utility.Options;

/**
 * This class is the view that will contain everything that is to be displayed by the application.
 */
public class InvestmentView implements InvestmentViewInterface {

  private Appendable view;
  private Readable read;


  /**
   * Instantiates a new Investment view.
   *
   * @param view the view
   */
  public InvestmentView(Appendable view) {
    this.view = view;
    if (this.view == null) {
      throw new IllegalArgumentException("Appendable cannot be null.");
    }
  }

  /**
   * Display portfolio.
   *
   * @param pt      the pt
   * @param options the options
   * @throws IOException the io exception
   */
  public void displayPortfolio(PortfolioTransferable pt, Options options) throws IOException {

    if (options.name().equals(Options.DETAILED_STATEMENT.name())) {
      for (StockTransferable st : pt.getStocks()) {
        view.append("Ticker:\t" + st.getTicker() + "\n");
        view.append("Total Number of Shares for " + st.getTicker() + ":\t" + st.getTotalNumberOfShares() + "\n");
        view.append("Current Price for a Stock " + st.getTicker() + " on " + pt.getTimeStamp() + ":\t" + st.getCurrentPrice() + "\n");
        view.append("Total Valuation for " + st.getTicker() + ":\t" + st.getTotalValue() + "\n");
        view.append("Total Investment for " + st.getTicker() + ":\t" + st.getTotalInvestment() + "\n");
        view.append("Total Return for " + st.getTicker() + ":\t" + st.getTotalReturn() + "\n\n");
      }
    }
    view.append("Total Investment:\t" + pt.getTotalInvestment() + "\n");
    view.append("Total Portfolio Valuation on " + pt.getTimeStamp() + ":\t" + pt.getTotalValue() + "\n");

  }


  /**
   * Display all portfolio names.
   *
   * @param portfolioNames the portfolio names
   * @throws IOException the io exception
   */
  public void displayAllPortfolioNames(List<String> portfolioNames) throws IOException {

    if (portfolioNames.size() == 0) {
      view.append(" you have not created any portfolios yet!");
      throw new IllegalArgumentException("no portfolio!");
    }

    view.append("\n");
    for (int i = 0; i < portfolioNames.size(); i++) {
      view.append(i + 1 + ". " + portfolioNames.get(i) + "\t\t\t");
    }
    view.append("\n\n");
  }

  /**
   * View welcome message.
   *
   * @throws IOException the io exception
   */
  public void viewWelcomeMessage() throws IOException {

    view.append("\t\t******************************* WELCOME TO INVESTMENT ****************************************\n\n");

  }

  /**
   * View intro message.
   *
   * @throws IOException the io exception
   */
  public void viewIntroMessage() throws IOException {
    String intro = "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n\n1.View Portfolio" + "\t\t\t" +
            "2.Portfolio Valuation" + "\t\t\t" + "3.Buy Stocks" + "\t\t\t" +
            "4.Create new Portfolio" + "\t\t\t" + "5.Dollar Cost Average" + "\t\t\t" +
            "6.Invest Fixed Amount\n\n\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n\n" + "Enter your option:\n";
    view.append(intro);
  }

  /**
   * View enter portfolio name.
   *
   * @throws IOException the io exception
   */
  public void viewEnterPortfolioName() throws IOException {
    view.append("\nEnter Portfolio Name or Press Q to go back to the Main menu:\n");
  }

  /**
   * View enter ticker.
   *
   * @throws IOException the io exception
   */
  public void viewEnterTicker() throws IOException {
    view.append("\nEnter Ticker or Press Q to go back to the Main menu:\n");
  }

  /**
   * View enter number of shares.
   *
   * @throws IOException the io exception
   */
  public void viewEnterNumberOfShares() throws IOException {
    view.append("\nEnter Number of Stocks you want to purchase or Press Q to go back to the Main menu:\n");
  }

  /**
   * View buy stock acknowledgement.
   *
   * @throws IOException the io exception
   */
  public void viewBuyStockAcknowledgement() throws IOException {
    view.append("\nThank you for buying these stocks!\n");
  }

  /**
   * View enter date.
   *
   * @throws IOException the io exception
   */
  public void viewEnterDate() throws IOException {
    view.append("\nEnter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n");
  }

  /**
   * View error invalid date.
   *
   * @throws IOException the io exception
   */
  public void viewErrorInvalidDate() throws IOException {
    view.append("\nEntered date is invalid.\n");
  }

  /**
   * View error shares in fraction.
   *
   * @throws IOException the io exception
   */
  public void viewErrorSharesInFraction() throws IOException {
    view.append("\nShares should be an Integer.\n");
  }

  /**
   * Print exceptions.
   *
   * @param e the e
   * @throws IOException the io exception
   */
  public void printExceptions(String e) throws IOException {
    view.append("\n\n*********************************  " + e + "  *********************************\n\n");
  }

  /**
   * Enter options.
   *
   * @throws IOException the io exception
   */
  public void enterOptions() throws IOException {
    view.append("\nEnter the one of the above options!\n");
  }

  /**
   * View Choose portfolio.
   *
   * @throws IOException the io exception
   */
  public void choosePortfolio() throws IOException {
    view.append("\n1. Add to existing portfolio \t\t\t  2. Create a new one\n");
  }

  /**
   * View Create new portfolio.
   *
   * @throws IOException the io exception
   */
  public void createNewPortfolio() throws IOException {
    view.append("\nCreate new portfolio:\n");
  }

  /**
   * View Enter portfolio.
   *
   * @throws IOException the io exception
   */
  public void enterPortfolio() throws IOException {
    view.append("\nEnter the portfolio name:\n");
  }

  /**
   * View Invalid option.
   *
   * @throws IOException the io exception
   */
  public void invalidOption() throws IOException {
    view.append("\nYou have entered Invalid Option\n");
  }

  /**
   * View display no portfolio.
   *
   * @throws IOException the io exception
   */
  public void noPortfolio() throws IOException {
    view.append("\nNo Portfolio, Create a new one.\n");
  }

  /**
   * View enter commission message.
   *
   * @throws IOException the io exception
   */

  public void enterCommission() throws IOException {
    view.append("Enter commission for this transaction:\n");
  }

  /**
   * View enter fixed amount message.
   *
   * @throws IOException the io exception
   */

  public void enterFixedAmount() throws IOException {
    view.append("Enter fixed amount to invest in a portfolio:\n");
  }

  /**
   * View choose weight option for investment.
   *
   * @throws IOException the io exception
   */

  public void chooseWeightOption() throws IOException {
    view.append("\n1. Assign individual weights \t\t\t  2. Assign equal weights\n");
  }

  /**
   * View choose portfolio for investment.
   *
   * @throws IOException the io exception
   */

  public void choosePortfolioToInvest() throws IOException {
    view.append("Choose portfolio to invest fixed amount:\n");
  }

  /**
   * View choose portfolio for strategy.
   *
   * @throws IOException the io exception
   */

  public void choosePortfolioForStrategy() throws IOException {
    view.append("Choose portfolio to apply strategy on:\n");
  }


  /**
   * View enter start date.
   *
   * @throws IOException the io exception
   */
  public void viewEnterStartDate() throws IOException {
    view.append("\nEnter Start Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n");
  }

  /**
   * View enter end date.
   *
   * @throws IOException the io exception
   */
  public void viewEnterEndDate() throws IOException {
    view.append("\nEnter End Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n");
  }

  /**
   * View enter frequency.
   *
   * @throws IOException the io exception
   */
  public void viewEnterFrequency() throws IOException {
    view.append("\nEnter Frequency (in days) or Press Q to go back to the Main menu\n");
  }

  /**
   * View enter weights.
   *
   * @throws IOException the io exception
   */
  public void viewEnterWeight(String stock) throws IOException {
    view.append("\nEnter Weight for " + stock + " or Press Q to go back to the Main menu\n");
  }


}
