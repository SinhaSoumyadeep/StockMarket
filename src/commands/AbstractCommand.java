package commands;

import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

import model.InvestModelInterfaceNew;
import view.InvestmentViewInterface;

/**
 * This abstract class implements the command interface and provides various operations such as if
 * the values entered by the used is invalid or method to tke input from the user or to check if the
 * user pressed quit.
 */
public abstract class AbstractCommand implements Commands {


  protected InvestmentViewInterface iv;
  protected InvestModelInterfaceNew im;
  protected StringBuffer automate;
  protected Scanner scan;


  /**
   * this method takes in a function which can be any function that validates the input enter by the
   * user.
   *
   * @param f   the validity function.
   * @param <T> can be of any type.
   * @param <R> returning type can be of any type.
   * @return whatever is the returning type of the vaidity function.
   */
  protected <T, R> R invalidityChecker(Function<T, R> f) throws IOException {

    String input = takeInput();
    try {
      return f.apply((T) input);

    } catch (Exception e) {
      iv.printExceptions(e.getMessage() + ", Try Again.");

      return invalidityChecker(f);
    }

  }

  /**
   * This method is used to take inputs from the user.
   *
   * @return the string that has the user input.
   */
  protected String takeInput() {
    String input = scan.next().trim();
    automate.append(input + "\\n");

    if (!quitHelper(input)) {
      return input;

    } else {
      throw new IllegalArgumentException("Returning to Main Menu.");
    }
  }


  /**
   * This method is used to check if the user wants to quit.
   *
   * @param str the uer input.
   * @return true if the user entered quit false otherwise.
   */
  protected boolean quitHelper(String str) {
    if (str.equals("q") || str.equals("Q")) {
      return true;
    }
    return false;
  }
}
