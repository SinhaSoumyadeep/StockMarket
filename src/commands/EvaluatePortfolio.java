package commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.InvestModelInterfaceNew;
import utility.DateUtility;
import utility.Options;
import view.InvestmentViewInterface;

/**
 * The class encapsulates the Evaluate portfolio command.
 */
public class EvaluatePortfolio extends AbstractCommand {

  private Options statement;

  /**
   * Instantiates a new Evaluate portfolio.
   *
   * @param im       the Investment model
   * @param iv       the Investment view
   * @param scan     the scan the scanner object.
   * @param statement the option to view mini statement or detailed statement.
   */
  public EvaluatePortfolio(InvestModelInterfaceNew im, InvestmentViewInterface iv, Scanner scan, Options statement, StringBuffer automate) {
    this.im = im;
    this.iv = iv;
    this.scan = scan;
    this.automate = automate;
    this.statement = statement;
  }

  /**
   * this method is used to execute the command.
   *
   * @throws IOException    if the view fails.
   * @throws ParseException if the date is invalid.
   */
  @Override
  public void execute() throws IOException,ParseException {
    DateUtility du = new DateUtility();

    iv.displayAllPortfolioNames(im.getPortfolioNames());
    iv.enterOptions();
    String portName = invalidityChecker(s -> new SelectPortfolio(Integer.parseInt(s.toString())).execute(im, iv));

    iv.viewEnterDate();
    String date = invalidityChecker(s -> {
      if (du.checkDateValidity(s.toString())) {
        return s.toString();
      } else {
        throw new IllegalArgumentException("invalid Date");
      }

    });

    iv.displayPortfolio(im.evaluatePortfolio(portName, date), this.statement);
  }
}
