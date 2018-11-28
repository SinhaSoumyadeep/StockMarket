package controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import commands.BuyStocks;
import commands.CreatePortfolio;
import commands.EvaluatePortfolio;
import commands.DollarCostAverageCommand;
import commands.InvestFixedAmount;
import model.InvestModelInterfaceNew;
import utility.Options;
import view.InvestmentViewInterface;


/**
 * This is the concrete implementation of the IStockMarketController interface. This method provides
 * a method where a user can start the application and buy stocks, view composition of portfolios
 * and create portfolios.
 *
 * <p>If, the input is either the letter 'q' or the letter 'Q', the controller
 * will return to the main menu if the key is pressed in sub menu else it will quit the
 * application.
 *
 * <p>The controller also lets the user view the composition of the portfolio.</p>
 *
 * <p>The composition of the portfolio can  be seen in detail where in the details of the stocks
 * purchased can also be viewed.</p>
 *
 * <p>Create new portfolio.</p>
 *
 * <p>buy stocks and add to existing portfolio.</p>
 *
 * <p>This controller takes a batch of user input in the form of a string. It validates the string
 * and if an input is unexpected. Anything that cannot be parsed or the controller deems it to to
 * invalid, it asks the user to input again. If the user enters a wrong input for a  field, it asks
 * the user to input again from the same point. for example if the user enters an invalid date , it
 * will ask the user to enter the date repetitively until the user enters a valid date or
 * quits.</p>
 *
 * <p>If the move was invalid as signaled by the model, the controller transmits a message to
 * the  Appendable object “Invalid Options” plus any informative message about why the option was
 * invalid, and resume waiting for valid input.</p>
 */
public class NewController implements IStockMarketController {

  private Readable readable;
  private InvestmentViewInterface iv;
  private InvestModelInterfaceNew im;
  private Scanner scan;
  private StringBuffer automate = new StringBuffer();


  /**
   * Instantiates a new Stock market controller.
   *
   * @param readable the readable object.
   * @param iv       the InverstmentView  Object
   * @param im       the object of InvestmentModelInterface
   */
  public NewController(Readable readable, InvestmentViewInterface iv, InvestModelInterfaceNew im) {


    this.readable = readable;
    this.iv = iv;
    this.im = im;

    if (this.readable == null || this.iv == null || this.im == null) {
      throw new IllegalArgumentException("Readable or View or Model cannot be Null.");

    }
    this.scan = new Scanner(this.readable);

  }

  /**
   * Start the stock market where the user of the application can buy stocks, create portfolio, view
   * the composition of the portfolio. the user can quit the application at any point.
   *
   * @throws ParseException if the date comparision fails.
   */
  @Override
  public void startStockMarket() throws ParseException {
    try {
      iv.viewWelcomeMessage();
      getUserInput();
    } catch (IOException io) {
      io.printStackTrace();
    }

  }


  /**
   * This method takes continuous inputs from the user. It validates the string  and if an input is
   * unexpected. Anything that cannot be parsed or the controller deems it to to invalid, it asks
   * the user to input again. If the user enters a wrong input for a  field, it asks the user to
   * input again from the same point. for example if the user enters an invalid date , it will ask
   * the user to enter the date repetitively until the user enters a valid date or  quits.
   *
   * @throws IOException    if the Appendable object fails.
   * @throws ParseException if the date validation fails.
   */
  private void getUserInput() throws IOException, ParseException {


    while (true) {

      iv.viewIntroMessage();


      String str = scan.next();
      automate.append(str+"\\n");
      if (str.equals("q")|| str.equals("Q")) {
        generateTestCases();
        return;
      }
      if (str.isEmpty()) {
        continue;
      }
      int userOption = 0;
      try {
        userOption = Integer.parseInt(str);

      } catch (Exception e) {
        iv.invalidOption();
        continue;
      }

      switch (userOption) {
        case 1:
          try {
            new EvaluatePortfolio(im, iv, scan, Options.DETAILED_STATEMENT).execute();
          } catch (Exception e) {
            iv.printExceptions(e.getMessage());
            continue;
          }
          break;
        case 2:
          try {
            new EvaluatePortfolio(im, iv, scan, Options.MINI_STATEMENT).execute();
          } catch (Exception e) {
            iv.printExceptions(e.getMessage());
            continue;
          }
          break;
        case 3:
          try {
            new BuyStocks(im, iv, scan,automate).execute();
          } catch (Exception e) {
            iv.printExceptions(e.getMessage());
            continue;
          }
          break;
        case 4:
          try {
          new CreatePortfolio(im, iv, scan, automate).execute();
          } catch (Exception e) {
            iv.printExceptions(e.getMessage());
            continue;
          }
          break;
        case 5:
          try {
            new DollarCostAverageCommand(im, iv, scan, automate).execute();
          }catch (Exception e){
            iv.printExceptions(e.getMessage());
            continue;
          }
          break;

        case 6:
          try {
            new InvestFixedAmount(im, iv, scan, automate).execute();
          }catch (Exception e){
            iv.printExceptions(e.getMessage());
            continue;
          }

          break;
        default:
          iv.invalidOption();

      }
    }

  }

  public void generateTestCases() {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("savedFile/generateTests.txt", true))) {


      String replacement = automate.toString();
      replacement = replacement.substring(0, automate.toString().length() - 1);


      String a = "{in = new StringReader(\"" + replacement + "\");\n" +
              " Appendable out = new StringBuffer();\n" +
              " iv = new InvestmentView(out);\n" +
              " smc = new StockMarketController(in, iv, im);\n" +
              " smc.startStockMarket();\n" +
              " assertEquals(\"\", out.toString());\n}";
      bw.write(a + "\n\n");

      System.out.println("Done");

    } catch (IOException e) {

      e.printStackTrace();

    }
  }





}
