package commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import model.InvestModelInterfaceNew;
import utility.DateUtility;
import view.InvestmentViewInterface;

/**
 * This class encapsulates the buy stock command.
 */
public class BuyStocks extends AbstractCommand {


  /**
   * Instantiates a new Buy stocks.
   *
   * @param im       the Investment model
   * @param iv       the Investment view
   * @param scan     the scan the scanner object.
   * @param automate the automate
   */
  public BuyStocks(InvestModelInterfaceNew im, InvestmentViewInterface iv, Scanner scan, StringBuffer automate) {
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
    DateUtility du = new DateUtility();
    iv.viewEnterTicker();

    String ticker = invalidityChecker(s -> s.toString());

    iv.viewEnterDate();
    String date = invalidityChecker(s -> {
      if (du.checkDateValidity(s.toString())) {
        return s.toString();
      } else {
        throw new IllegalArgumentException("invalid Date");
      }

    });

    iv.viewEnterNumberOfShares();
    Double numberOfShares = invalidityChecker(s -> Double.parseDouble(s.toString()));

    iv.choosePortfolio();

    String portName = "";

    String choice = takeInput().trim();
    if (choice.equals("1")) {

      if (im.getPortfolioNames().isEmpty()) {
        iv.noPortfolio();
        new CreatePortfolio(im, iv, scan, automate).execute();
      } else {
        iv.displayAllPortfolioNames(im.getPortfolioNames());
      }
      portName = invalidityChecker(s -> new SelectPortfolio(Integer.parseInt(s.toString())).execute(im, iv));
    } else if (choice.equals("2")) {
      new CreatePortfolio(im, iv, scan, automate).execute();
      portName = invalidityChecker(s -> new SelectPortfolio(Integer.parseInt(s.toString())).execute(im, iv));
    } else {
      throw new IllegalArgumentException("Invalid Option");
    }
    iv.enterCommission();
    String commission = invalidityChecker(s -> {

      try {
        if (Integer.parseInt(s.toString()) >= 0) {
          return s.toString();
        } else {
          throw new IllegalArgumentException("enter 0 or a positive number");
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("enter a number!");
      }

    });


    im.buyStocks(ticker.toUpperCase(), date, numberOfShares, portName, commission);
    iv.viewBuyStockAcknowledgement();
  }

}
