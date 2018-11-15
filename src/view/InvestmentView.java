package view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import transferable.PortfolioTransferable;
import transferable.StockTransferable;
import utility.Options;

public class InvestmentView {

  Appendable view;
  Readable read;


  public InvestmentView(Appendable view) {
    this.view = view;
    if(this.view == null){
      throw new IllegalArgumentException("Appendable cannot be null.");
    }
  }

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

  public void viewWelcomeMessage() throws IOException {

      view.append("\t\t******************************* WELCOME TO INVESTMENT ****************************************\n\n");

  }

  public void viewIntroMessage() throws IOException {
    String intro = "\t\t\t\t\t\t\t\t\t\t\t\tMAIN MENU\n\n1.View Portfolio" + "\t\t\t" + "2.Portfolio Valuation" + "\t\t\t" + "3.Buy Stocks" + "\t\t\t" +
            "4.Create new Portfolio\n\n\t\t\t\t\t\t\t\t\t\t\tPress Q to Quit.\n\n" + "Enter your option:\n";
    view.append(intro);
  }

  public void viewEnterPortfolioName() throws IOException {
    view.append("\nEnter Portfolio Name or Press Q to go back to the Main menu:\n");
  }

  public void viewEnterTicker() throws IOException {
    view.append("\nEnter Ticker or Press Q to go back to the Main menu:\n");
  }

  public void viewEnterNumberOfShares() throws IOException {
    view.append("\nEnter Number of Stocks you want to purchase or Press Q to go back to the Main menu:\n");
  }

  public void viewBuyStockAcknowledgement() throws IOException {
    view.append("\nThank you for buying these stocks!\n");
  }

  public void viewEnterDate() throws IOException {
    view.append("\nEnter Date (Format: YYYY-MM-DD) or Press Q to go back to the Main menu\n");
  }

  public void viewErrorInvalidDate() throws IOException {
    view.append("\nEntered date is invalid.\n");
  }

  public void viewErrorSharesInFraction() throws IOException {
    view.append("\nShares should be an Integer.\n");
  }

  public void printExceptions(String e) throws IOException {
    view.append("\n\n*********************************  " + e + "  *********************************\n\n");
  }

  public void enterOptions() throws IOException {
    view.append("\nEnter the one of the above options!\n");
  }

  public void choosePortfolio() throws IOException {
    view.append("\n1. Add to existing portfolio \t\t\t  2. Create a new one\n");
  }

  public void createNewPortfolio() throws IOException {
    view.append("\nCreate new portfolio:\n");
  }

  public void enterPortfolio() throws IOException {
    view.append("\nEnter the portfolio name:\n");
  }

  public void invalidOption()  throws IOException {
    view.append("\nYou have entered Invalid Option\n");
  }




}
