package commands;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

import model.DollarCostAverageStrategy;
import model.InvestModelInterfaceNew;
import model.InvestmentStrategyInterface;
import utility.DateUtility;
import view.InvestmentViewInterface;

public class DollarCostAverageCommand extends AbstractCommand {

  public DollarCostAverageCommand(InvestModelInterfaceNew im, InvestmentViewInterface iv, Scanner scan) {
    this.im = im;
    this.iv = iv;
    this.scan = scan;
  }

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


    iv.viewEnterStartDate();
    String startDate = invalidityChecker(s -> {
      if (du.checkDateValidity(s.toString())) {
        return s.toString();
      } else {
        throw new IllegalArgumentException("invalid Date");
      }

    });


    iv.chooseEndDateOption();
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

    String endDate = "";

    if(choice.equals("1")) {
      iv.viewEnterEndDate();
      endDate = invalidityChecker(s -> {
        if (du.checkDateValidity(s.toString())) {
          return s.toString();
        } else {
          throw new IllegalArgumentException("invalid Date");
        }

      });
    }
    else if(choice.equals("2")){
      endDate = LocalDate.now().toString();
      iv.ongoingEndDate();
    }

    iv.viewEnterFrequency();
    String frequency = invalidityChecker(s -> {

      try {
        if (Integer.parseInt(s.toString()) > 0) {
          return s.toString();
        } else {
          throw new IllegalArgumentException("Enter a positive number");
        }
      } catch (Exception e) {
        throw new IllegalArgumentException("Frequency should be a whole number");
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
    try {
      InvestmentStrategyInterface da = new DollarCostAverageStrategy(Double.parseDouble(fixedAmount),
              startDate, endDate, Integer.parseInt(frequency), commission);
      im.registerStrategy(da, portName, weights);
    } catch (Exception e) {
      System.out.println("Exception thrown)))))))))))))))))))))))))");
      iv.printExceptions(e.getMessage());

    }


  }

}
