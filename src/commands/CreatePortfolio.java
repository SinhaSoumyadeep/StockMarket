package commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.InvestModelInterfaceNew;
import view.InvestmentViewInterface;

/**
 * This class encapsulates the Create portfolio command.
 */
public class CreatePortfolio extends AbstractCommand {


  /**
   * Instantiates a new Create portfolio.
   *
   * @param im       the Investment model
   * @param iv       the Investment view
   * @param scan     the scan the scanner object.
   * @param automate the automate
   */
  public CreatePortfolio(InvestModelInterfaceNew im, InvestmentViewInterface iv, Scanner scan, StringBuffer automate) {
    this.im = im;
    this.iv = iv;
    this.scan = scan;
    this.automate = automate;
  }

  /**
   * this method is used to execute the command.
   *
   * @throws IOException    if the view fails.
   * @throws ParseException if the date is invalid.
   */
  @Override
  public void execute() throws IOException, ParseException {

    iv.enterPortfolio();
    String portfolioName = invalidityChecker(s -> s.toString());
    if (portfolioName.equals("quit")) {
      throw new IllegalArgumentException("Returning to Main Menu.");
    }
    im.createNewPortfolio(portfolioName);
    iv.displayAllPortfolioNames(im.getPortfolioNames());

  }
}
