package commands;

import java.io.IOException;
import java.util.Scanner;

import model.InvestmentModelInterface;
import utility.DateUtility;
import utility.Options;
import view.InvestmentViewInterface;

public class EvaluatePortfolio extends AbstractCommand{

  private Options statement;

  public EvaluatePortfolio(InvestmentModelInterface im, InvestmentViewInterface iv, Scanner scan, Options statement)
  {
    this.im = im;
    this.iv = iv;
    this.scan = scan;
    this.statement = statement;
  }

  @Override
  public void execute() throws IOException {
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

    iv.displayPortfolio(im.evaluatePortfolio(portName, date),this.statement);
  }
}
