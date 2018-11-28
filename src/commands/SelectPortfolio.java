package commands;

import java.util.List;

import model.InvestModelInterfaceNew;
import view.InvestmentViewInterface;

/**
 * The command encapsulates the Select portfolio command.
 */
public class SelectPortfolio {

  /**
   * The Choice.
   */
  Integer choice;

  /**
   * Instantiates a new Select portfolio.
   *
   * @param choice the choice
   */
  public SelectPortfolio(Integer choice) {
    this.choice = choice;
  }

  /**
   * Execute the command.
   *
   * @param m the the Investment model
   * @param v the the investment view
   * @return the string that returns the portfolio.
   */
  public String execute(InvestModelInterfaceNew m, InvestmentViewInterface v) {

    List<String> options = m.getPortfolioNames();
    if (options.isEmpty()) {
      throw new IllegalArgumentException("you have not created any portfolios yet");
    }
    if (this.choice - 1 >= options.size()) {
      throw new IllegalArgumentException("Invalid Option");
    } else {
      return options.get(this.choice - 1);
    }

  }

}
