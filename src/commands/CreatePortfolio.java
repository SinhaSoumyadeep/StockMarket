package commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.InvestModelInterfaceNew;
import model.InvestmentModelInterface;
import view.InvestmentViewInterface;

public class CreatePortfolio extends AbstractCommand {

  public CreatePortfolio(InvestModelInterfaceNew im, InvestmentViewInterface iv, Scanner scan) {
    this.im = im;
    this.iv = iv;
    this.scan = scan;
  }
  @Override
  public void execute() throws IOException, ParseException {

    iv.enterPortfolio();
    String portfolioName = invalidityChecker(s ->s.toString());
    if(portfolioName.equals("quit"))
    {
      throw new IllegalArgumentException("Returning to Main Menu.");
    }
    im.createNewPortfolio(portfolioName);
    iv.displayAllPortfolioNames(im.getPortfolioNames());

  }
}
