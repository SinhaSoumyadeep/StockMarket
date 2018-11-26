//package controller;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.text.ParseException;
//import java.util.List;
//import java.util.Scanner;
//
//import model.InvestmentModel;
//import model.InvestmentModelInterface;
//import properties.PropertiesLoader;
//import utility.DateUtility;
//import utility.Options;
//import view.InvestmentViewInterface;
//
//
///**
// * This is the concrete implementation of the IStockMarketController interface. This method provides
// * a method where a user can start the application and buy stocks, view composition of portfolios
// * and create portfolios.
// *
// * <p>If, the input is either the letter 'q' or the letter 'Q', the controller
// * will return to the main menu if the key is pressed in sub menu else it will quit the
// * application.
// *
// * <p>The controller also lets the user view the composition of the portfolio.</p>
// *
// * <p>The composition of the portfolio can  be seen in detail where in the details of the stocks
// * purchased can also be viewed.</p>
// *
// * <p>Create new portfolio.</p>
// *
// * <p>buy stocks and add to existing portfolio.</p>
// *
// * <p>This controller takes a batch of user input in the form of a string. It validates the string
// * and if an input is unexpected. Anything that cannot be parsed or the controller deems it to to
// * invalid, it asks the user to input again. If the user enters a wrong input for a  field, it asks
// * the user to input again from the same point. for example if the user enters an invalid date , it
// * will ask the user to enter the date repetitively until the user enters a valid date or
// * quits.</p>
// *
// * <p>If the move was invalid as signaled by the model, the controller transmits a message to
// * the  Appendable object “Invalid Options” plus any informative message about why the option was
// * invalid, and resume waiting for valid input.</p>
// */
//public class StockMarketController implements IStockMarketController {
//
//  private Readable readable;
//  private InvestmentViewInterface iv;
//  private InvestmentModelInterface im;
//  private boolean quitFlag;
//  private Scanner scan;
//  private PropertiesLoader loader;
//
//
//  /**
//   * Instantiates a new Stock market controller.
//   *
//   * @param readable the readable object.
//   * @param iv       the InverstmentView  Object
//   * @param im       the object of InvestmentModelInterface
//   */
//  public StockMarketController(Readable readable, InvestmentViewInterface iv, InvestmentModelInterface im) {
//
//
//    this.readable = readable;
//    loader = new PropertiesLoader();
//    File f = new File(loader.getValue("SAVEPORTFOLIO"));
//    if (f.exists() && !f.isDirectory()) {
//      im = (InvestmentModelInterface) retrievePortfolio(loader.getValue("SAVEPORTFOLIO"));
//
//    } else {
//      im = new InvestmentModel();
//    }
//
//    this.iv = iv;
//    this.im = im;
//
//    if (this.readable == null || this.iv == null || this.im == null) {
//      throw new IllegalArgumentException("Readable or View or Model cannot be Null.");
//
//    }
//    this.quitFlag = false;
//    this.scan = new Scanner(this.readable);
//
//  }
//
//  /**
//   * Start the stock market where the user of the application can buy stocks, create portfolio, view
//   * the composition of the portfolio. the user can quit the application at any point.
//   *
//   * @throws ParseException if the date comparision fails.
//   */
//  @Override
//  public void startStockMarket() throws ParseException {
//    try {
//      iv.viewWelcomeMessage();
//      getUserInput();
//    } catch (IOException io) {
//      io.printStackTrace();
//    }
//
//  }
//
//
//  /**
//   * This method takes continuous inputs from the user. It validates the string  and if an input is
//   * unexpected. Anything that cannot be parsed or the controller deems it to to invalid, it asks
//   * the user to input again. If the user enters a wrong input for a  field, it asks the user to
//   * input again from the same point. for example if the user enters an invalid date , it will ask
//   * the user to enter the date repetitively until the user enters a valid date or  quits.
//   *
//   * @throws IOException    if the Appendable object fails.
//   * @throws ParseException if the date validation fails.
//   */
//  private void getUserInput() throws IOException, ParseException {
//
//
//    while (true) {
//
//      iv.viewIntroMessage();
//
//
//      String str = scan.next();
//      if (quitHelper(str)) {
//        quitFlag = true;
//        return;
//      }
//      if (str.isEmpty()) {
//        continue;
//      }
//      int userOption = 0;
//      try {
//        userOption = Integer.parseInt(str);
//
//      } catch (Exception e) {
//        iv.invalidOption();
//        continue;
//      }
//
//      switch (userOption) {
//        case 1:
//          String portfolioName = "";
//          try {
//            portfolioName = selectPortfolio(scan);
//            if (portfolioName == null && quitFlag == true) {
//              continue;
//            }
//          } catch (Exception e) {
//            continue;
//          }
//
//          String date = getDate(scan);
//          if (date == null && quitFlag == true) {
//            continue;
//          }
//          try {
//            iv.displayPortfolio(im.evaluatePortfolio(portfolioName.trim(), date.trim()), Options.DETAILED_STATEMENT);
//          } catch (IllegalArgumentException e) {
//            iv.printExceptions(e.getMessage());
//            continue;
//          }
//
//
//          break;
//
//        case 2:
//          String portName = "";
//          try {
//            portName = selectPortfolio(scan);
//            if (portName == null && quitFlag == true) {
//              continue;
//            }
//          } catch (Exception e) {
//            continue;
//          }
//
//          String portdate = getDate(scan);
//          if (portdate == null && quitFlag == true) {
//            continue;
//          }
//          try {
//
//            iv.displayPortfolio(im.evaluatePortfolio(portName.trim(), portdate.trim()), Options.MINI_STATEMENT);
//          } catch (IllegalArgumentException e) {
//            iv.printExceptions(e.getMessage());
//            continue;
//          }
//          break;
//
//        case 3:
//          if (!buyStocks(scan) && quitFlag) {
//            continue;
//          } else {
//            iv.viewBuyStockAcknowledgement();
//            savePortfolio(im, loader.getValue("SAVEPORTFOLIO"));
//          }
//          break;
//
//
//        case 4:
//          //create new portfolio;
//          if (!createNewPortfolio(scan) && quitFlag) {
//            continue;
//          } else {
//            iv.displayAllPortfolioNames(im.getPortfolioNames());
//            savePortfolio(im, loader.getValue("SAVEPORTFOLIO"));
//          }
//          break;
//
//        default:
//          iv.invalidOption();
//          continue;
//
//
//      }
//    }
//
//  }
//
//  /**
//   * This method takes in a scanner object. this method lets the user buy stocks.
//   *
//   * @param scan the scanner object.
//   * @return true if the method executes successfully, and the user is able to buy stocks.
//   * @throws IOException    if the Appendable object fails.
//   * @throws ParseException if the date validation fails.
//   */
//  private boolean buyStocks(Scanner scan) throws IOException, ParseException {
//    iv.viewEnterTicker();//view for ticker!!!
//    String ticker = getTicker(scan);
//    if (ticker == null && quitFlag == true) {
//      return false;
//    }
//    String date1 = getDate(scan);
//    if (date1 == null && quitFlag == true) {
//      return false;
//    }
//    Integer noOfStocks = getNoOfShares(scan);
//    if (noOfStocks == null && quitFlag == true) {
//      return false;
//    }
//    String portfolio = enterOptions(im.getPortfolioNames(), scan);
//    if (quitFlag && portfolio == null) {
//      return false;
//    }
//
//    try {
//      im.buyStocks(ticker.trim(), date1.trim(), noOfStocks, portfolio, );
//    } catch (IllegalArgumentException e) {
//      iv.printExceptions(e.getMessage());
//      quitFlag = true;
//      return false;
//    }
//    return true;
//  }
//
//  /**
//   * This method lets user choose an existing portfolio while buying new stocks or lets the user
//   * create a new portfolio while in the process of buying stocks. and return the chosen portfolio
//   * name.
//   *
//   * @param listOfPortfolio this method takes in a list of portfolio names.
//   * @param scan            this is the scanner object.
//   * @return the chosen portfolio name.
//   * @throws IOException when the Appendable object fails.
//   */
//  private String enterOptions(List<String> listOfPortfolio, Scanner scan) throws IOException {
//
//    while (true) {
//      iv.choosePortfolio();
//      String opt = scan.next().trim();
//      if (quitHelper(opt)) {
//        quitFlag = true;
//        return null;
//      }
//
//      if (opt.equals("1")) {
//        break;
//
//      } else if (opt.equals("2")) {
//        createNewPortfolio(scan);
//        break;
//      } else {
//        iv.invalidOption();
//        continue;
//      }
//    }
//
//    while (true) {
//
//      try {
//        iv.displayAllPortfolioNames(im.getPortfolioNames());
//      } catch (Exception e) {
//        createNewPortfolio(scan);
//        iv.displayAllPortfolioNames(im.getPortfolioNames());
//      }
//
//
//      iv.enterOptions();
//      String op = scan.next().trim();
//      if (quitHelper(op)) {
//        quitFlag = true;
//        return null;
//      }
//      try {
//        Integer option = Integer.parseInt(op);
//
//        if (option <= im.getPortfolioNames().size()) {
//          return im.getPortfolioNames().get(option - 1);
//        }
//      } catch (Exception e) {
//        iv.invalidOption();
//        continue;
//      }
//
//    }
//
//
//  }
//
//  /**
//   * This method lets the user create a new portfolio.
//   *
//   * @param scan the scanner object.
//   * @return true if the method executes successfully,the user can create new portfolio.
//   * @throws IOException when the Appendable fails.
//   */
//  private boolean createNewPortfolio(Scanner scan) throws IOException {
//    iv.createNewPortfolio();
//    boolean methodExecutedProperly = false;
//    while (true) {
//      iv.enterPortfolio();
//      String portfolioName = scan.next().trim();
//      if (quitHelper(portfolioName)) {
//        quitFlag = true;
//        return false;
//      }
//
//      if (!portfolioName.isEmpty()) {
//
//        try {
//          im.createNewPortfolio(portfolioName);
//        } catch (IllegalArgumentException e) {
//          iv.printExceptions(e.getMessage());
//          continue;
//        }
//        methodExecutedProperly = true;
//        break;
//      } else {
//        createNewPortfolio(scan);
//      }
//    }
//    return methodExecutedProperly;
//  }
//
//
//  /**
//   * This method takes a user input and checks if the user want to quit the application or return to
//   * the main menu.
//   *
//   * @param str the user input.
//   * @return a true if the user wants to quit the application or return to the main menu.
//   */
//  private boolean quitHelper(String str) {
//    if (str.contains("q") || str.contains("Q")) {
//      return true;
//    }
//    return false;
//  }
//
//  /**
//   * This method lets the user select a portfolio form a list of portfolios.
//   *
//   * @param scan the scanner object.
//   * @return the name of the selected portfolio.
//   * @throws IOException when the Appendable object fails.
//   */
//  private String selectPortfolio(Scanner scan) throws IOException {
//    String choice = "";
//    while (true) {
//      List<String> options = im.getPortfolioNames();
//      iv.displayAllPortfolioNames(options);
//
//      iv.enterOptions();
//      String option = scan.next();
//      if (quitHelper(option)) {
//        quitFlag = true;
//        return null;
//      }
//
//
//      Integer temp;
//      try {
//        temp = Integer.parseInt(option);
//
//      } catch (Exception e) {
//        iv.invalidOption();
//        continue;
//      }
//      if (temp - 1 < options.size()) {
//
//        choice = options.get(temp - 1);
//        break;
//      } else {
//        iv.invalidOption();
//        return selectPortfolio(scan);
//      }
//    }
//
//    return choice;
//  }
//
//  /**
//   * This method is used to take the ticker symbol from the user.
//   *
//   * @param scan the scanner object.
//   * @return the ticker symbol from the user.
//   */
//  private String getTicker(Scanner scan) {
//    String ticker = scan.next();
//    if (quitHelper(ticker)) {
//      quitFlag = true;
//      return null;
//    }
//
//    return ticker.trim().toUpperCase();
//  }
//
//
//  /**
//   * This method gets the date from the user.
//   *
//   * @param scan the scanner object.
//   * @return the date in string format.
//   * @throws IOException the Appendable object fails.
//   */
//  private String getDate(Scanner scan) throws IOException {
//    DateUtility ut = new DateUtility();
//    String date = "";
//    while (true) {
//      iv.viewEnterDate();
//
//
//      date = scan.next();
//      if (quitHelper(date)) {
//        quitFlag = true;
//        return null;
//      }
//      if (ut.checkDateValidity(date)) {
//        break;
//      } else {
//
//        iv.viewErrorInvalidDate();
//        return getDate(scan);
//      }
//
//    }
//
//    return date.trim();
//  }
//
//  /**
//   * This method takes the number of shares that the user wants to buy.
//   *
//   * @param scan Scanner object.
//   * @return the number of shares as Integer object.
//   * @throws IOException when the Appendable object fails.
//   */
//  private Integer getNoOfShares(Scanner scan) throws IOException {
//    String noOfStocks = "";
//
//    while (true) {
//
//      iv.viewEnterNumberOfShares();
//
//      noOfStocks = scan.next();
//      if (quitHelper(noOfStocks)) {
//        quitFlag = true;
//        return null;
//      }
//
//
//      try {
//        Integer numberOfStocks = Integer.parseInt(noOfStocks);
//        if (numberOfStocks <= 0) {
//          continue;
//        }
//        break;
//      } catch (Exception e) {
//
//        iv.viewErrorSharesInFraction();
//
//        return getNoOfShares(scan);
//      }
//
//    }
//    return Integer.parseInt(noOfStocks);
//
//  }
//
//
//  /**
//   * This method serializes the Investment model that is sent to this method.
//   *
//   * @param fc the InvestmentModelInterface object.
//   * @throws IOException when the Appendable object fails.
//   */
//  private void savePortfolio(Object fc, String filename) throws IOException {
//
//    try {
//
//      FileOutputStream file = new FileOutputStream(filename);
//      ObjectOutputStream out = new ObjectOutputStream(file);
//
//      out.writeObject(fc);
//
//      out.close();
//      file.close();
//
//
//    } catch (IOException ex) {
//      iv.printExceptions("Unable to serialize");
//    }
//  }
//
//  /**
//   * Deserialize the Investment model which was serialize.
//   *
//   * @return The object of InvestmentModel.
//   */
//  private Object retrievePortfolio(String filename) {
//    Object savedObject = null;
//
//    try {
//
//      FileInputStream file = new FileInputStream(filename);
//      ObjectInputStream in = new ObjectInputStream(file);
//
//      savedObject = in.readObject();
//
//      in.close();
//      file.close();
//
//      return savedObject;
//
//    } catch (IOException ex) {
//      System.out.println("IOException is caught");
//      ex.printStackTrace();
//      System.exit(0);
//    } catch (ClassNotFoundException ex) {
//      System.out.println("ClassNotFoundException is caught");
//      ex.printStackTrace();
//      System.exit(0);
//    }
//
//    return savedObject;
//
//  }
//
//
//}
