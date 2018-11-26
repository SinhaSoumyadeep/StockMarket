package commands;

import java.io.IOException;
import java.util.List;

import model.InvestmentModelInterface;
import view.InvestmentViewInterface;

public class SelectPortfolio {

  Integer choice;

  public SelectPortfolio(Integer choice) {
    this.choice = choice;
  }

  public String execute(InvestmentModelInterface m, InvestmentViewInterface v) {

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
