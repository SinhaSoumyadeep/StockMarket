package commands;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Scanner;

import model.InvestModelInterfaceNew;
import utility.DateUtility;
import view.InvestmentViewInterface;

/**
 * The class encapsulates the Invest fixed amount command.
 */
public class InvestFixedAmount extends AbstractCommand {


  /**
   * Instantiates a new Invest fixed amount.
   *
   * @param im       the Investment model
   * @param iv       the Investment view
   * @param scan     the scan the scanner object.
   * @param automate the automate
   */
  public InvestFixedAmount(InvestModelInterfaceNew im, InvestmentViewInterface iv, Scanner scan, StringBuffer automate) {
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
    String portName = "";

    if (im.getPortfolioNames().isEmpty()) {
      iv.noPortfolio();
      return;
    } else {
      iv.displayAllPortfolioNames(im.getPortfolioNames());
    }
    iv.choosePortfolioForStrategy();

    portName = invalidityChecker(s -> new SelectPortfolio(Integer.parseInt(s.toString())).execute(im, iv));

    if (im.checkIfPortfolioIsEmpty(portName)) {
      iv.printExceptions("Portfolio has no contents.");
      return;
    }

    iv.enterFixedAmount();

    String fixedAmount = invalidityChecker(s -> {

      try {
        if (Double.parseDouble(s.toString()) > 0) {
          return s.toString();
        } else {
          throw new IllegalArgumentException("Enter a positive number");
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Fixed amount should be a number");
      }

    });

    iv.viewEnterDate();
    String date = invalidityChecker(s -> {
      if (du.checkDateValidity(s.toString())) {
        return s.toString();
      } else {
        throw new IllegalArgumentException("invalid Date");
      }

    });

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

    HashMap<String, Double> weights = new HashMap<String, Double>();
    iv.chooseWeightOption();
    String choice = invalidityChecker(s -> {

      try {
        if (Integer.parseInt(s.toString()) > 0 && Integer.parseInt(s.toString()) <= 2) {
          return s.toString();
        } else {
          throw new IllegalArgumentException("Enter 0 or a positive number");
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Option should be a number.");
      }

    });

    if(choice.equals("1")){
      for (String stock : im.getStocksInPortfolio(portName)) {

        iv.viewEnterWeight(stock);
        String weight = invalidityChecker(s -> {

          try {
            if (Double.parseDouble(s.toString()) >= 0) {
              return s.toString();
            } else {
              throw new IllegalArgumentException("enter 0 or a positive number");
            }
          } catch (Exception e) {
            throw new IllegalArgumentException("enter a number!");
          }

        });


        weights.put(stock, Double.parseDouble(weight));
      }
      im.investStocks(portName,Double.parseDouble(fixedAmount),weights,date,commission);
    }
    else if (choice.equals("2")){
      im.investStocks(portName,Double.parseDouble(fixedAmount),date,commission);
    }

    else{
      throw new IllegalArgumentException("Invalid Option.");
    }

  }
}
