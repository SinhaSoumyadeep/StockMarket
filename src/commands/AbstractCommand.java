package commands;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

import model.InvestModelInterfaceNew;
import model.InvestmentModelInterface;
import view.InvestmentViewInterface;

public abstract class AbstractCommand implements Commands {


  protected InvestmentViewInterface iv;
  protected InvestModelInterfaceNew im;
  protected StringBuffer automate;
  protected Scanner scan;


  protected <T, R> R invalidityChecker(Function<T, R> f) throws IOException {

    String input = takeInput();
    try {
      return f.apply((T) input);

    } catch (Exception e) {
      iv.printExceptions(e.getMessage() + ", Try Again.");

      return invalidityChecker(f);
    }

  }


  protected String takeInput() {
    String input = scan.next().trim();
    automate.append(input+"\\n");

    if (!quitHelper(input)) {
      return input;

    } else {
      throw new IllegalArgumentException("Returning to Main Menu.");
    }
  }


  protected boolean quitHelper(String str) {
    if (str.equals("q") || str.equals("Q")) {
      return true;
    }
    return false;
  }
}
