package controller;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;


import model.InvestmentModel;
import model.InvestmentModelInterface;
import utility.DateUtility;
import utility.Options;
import view.InvestmentView;


public class StockMarketController implements IStockMarketController{

  private Readable readable;
  private InvestmentView iv;
  private InvestmentModelInterface im;
  private boolean quitFlag;
  private String filename;
  private StringBuffer automate = new StringBuffer();


  public StockMarketController(Readable readable, InvestmentView iv, InvestmentModelInterface im) {
    this.readable = readable;

    this.filename = "savedFile/savedata.txt";
    File f = new File(this.filename);
    if (f.exists() && !f.isDirectory()) {
      im = retrievePortfolio();
    } else {
      im = new InvestmentModel();
    }

    this.iv = iv;
    this.im = im;
    this.quitFlag = false;
  }


  public void startStockMarket() {
    try {
      iv.viewWelcomeMessage();
      getUserInput();
    } catch (IOException io) {
      io.printStackTrace();
    }

  }


  private void getUserInput() throws IOException {


    Scanner scan = new Scanner(this.readable);


    while (true) {

      iv.viewIntroMessage();


      String str = scan.next();
      automate.append(str+",");
      if(quitHelper(str))
      {
        generateTestCases();
        quitFlag = true;
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
          String portfolioName = "";
          try {
            portfolioName = selectPortfolio(scan);
            if(portfolioName == null && quitFlag == true){
              continue;
            }
          } catch (Exception e) {
            continue;
          }

          String date = getDate(scan);
          if(date == null && quitFlag == true){
            continue;
          }
          try {
            iv.displayPortfolio(im.evaluatePortfolio(portfolioName.trim(), date.trim()), Options.DETAILED_STATEMENT);
          }
          catch (IllegalArgumentException e){
            iv.printExceptions(e.getMessage());
            continue;
          }


          break;

        case 2:
          String portName = "";
          try {
            portName = selectPortfolio(scan);
            if(portName == null && quitFlag == true){
              continue;
            }
          } catch (Exception e) {
            continue;
          }

          String portdate = getDate(scan);
          if(portdate == null && quitFlag == true){
            continue;
          }
          try{

            iv.displayPortfolio(im.evaluatePortfolio(portName.trim(), portdate.trim()), Options.MINI_STATEMENT);
          }
          catch (IllegalArgumentException e){
            iv.printExceptions(e.getMessage());
            continue;
          }
          break;

        case 3:
          if(!buyStocks(scan) && quitFlag){
            continue;
          }else {
            iv.viewBuyStockAcknowledgement();
            savePortfolio(im);
          }
          break;


        case 4:
          //create new portfolio;
          if(!createNewPortfolio(scan) && quitFlag){
            continue;
          }else {
            iv.displayAllPortfolioNames(im.getPortfolioNames());
            savePortfolio(im);
          }
          break;

        default:
          iv.invalidOption();
          continue;


      }
    }

  }

  private boolean buyStocks(Scanner scan) throws IOException {
    iv.viewEnterTicker();//view for ticker!!!
    String ticker = getTicker(scan);
    if(ticker == null && quitFlag == true){
      return false;
    }
    String date1 = getDate(scan);
    if(date1 == null && quitFlag == true){
      return false;
    }
    Integer noOfStocks = getNoOfShares(scan);
    if(noOfStocks == null && quitFlag == true){
      return false;
    }
    String portfolio = enterOptions(im.getPortfolioNames(),scan);
    if (quitFlag && portfolio == null)
    {
      return false;
    }

    try {
      im.buyStocks(ticker.trim(), date1.trim(), noOfStocks, portfolio);
    }
    catch (IllegalArgumentException e)
    {
      iv.printExceptions(e.getMessage());
      quitFlag = true;
      return false;
    }
    return true;
  }


  private String enterOptions(List<String> listOfPortfolio, Scanner scan) throws IOException {

    while (true) {
      iv.choosePortfolio();
      String opt = scan.next().trim();
      automate.append(opt+",");
      if(quitHelper(opt))
      {
        quitFlag = true;
        return null;
      }

      if (opt.equals("1")) {
        break;

      } else if (opt.equals("2")) {
        createNewPortfolio(scan);
        break;
      } else {
        iv.invalidOption();
        continue;
      }
    }

    while(true) {

      try {
        iv.displayAllPortfolioNames(im.getPortfolioNames());
      }catch (Exception e)
      {
        createNewPortfolio(scan);
        iv.displayAllPortfolioNames(im.getPortfolioNames());
      }


      iv.enterOptions();
      String op = scan.next().trim();
      automate.append(op+",");
      if(quitHelper(op))
      {
        quitFlag = true;
        return null;
      }
      try {
        Integer option = Integer.parseInt(op);

        if(option <= im.getPortfolioNames().size())
        {
          return im.getPortfolioNames().get(option - 1);
        }
      }
      catch (Exception e)
      {
        iv.invalidOption();
        continue;
      }

    }


  }






  private boolean createNewPortfolio(Scanner scan) throws IOException {
    iv.createNewPortfolio();
    boolean methodExecutedProperly = false;
    while (true) {
      iv.enterPortfolio();
      String portfolioName = scan.next().trim();
      automate.append(portfolioName+",");
      if(quitHelper(portfolioName))
      {
        quitFlag = true;
        return false;
      }

      if (!portfolioName.isEmpty()) {

        try {
          im.createNewPortfolio(portfolioName);
        }
        catch (IllegalArgumentException e)
        {
          iv.printExceptions(e.getMessage());
          continue;
        }
        methodExecutedProperly = true;
        break;
      }
      else {
        createNewPortfolio(scan);
      }
    }
    return methodExecutedProperly;
  }


  private boolean quitHelper(String str){
    if (str.contains("q") || str.contains("Q")) {
      return  true;
    }
    return false;
  }

  private String selectPortfolio(Scanner scan) throws IOException {
    String choice = "";
    while (true) {
      List<String> options = im.getPortfolioNames();
      iv.displayAllPortfolioNames(options);

      iv.enterOptions();
      String option = scan.next();
      automate.append(option+",");
      if(quitHelper(option))
      {
        quitFlag = true;
        return null;
      }



      Integer temp;
      try {
        temp = Integer.parseInt(option);

      } catch (Exception e) {
        iv.invalidOption();
        continue;
      }
      if (temp - 1 < options.size()) {

        choice = options.get(temp - 1);
        break;
      } else {
        iv.invalidOption();
        return selectPortfolio(scan);
      }
    }

    return choice;
  }


  private String getTicker(Scanner scan) {
    String ticker = scan.next();
    automate.append(ticker+",");
    if(quitHelper(ticker))
    {
      quitFlag = true;
      return null;
    }

    return ticker.trim().toUpperCase();
  }


  private String getDate(Scanner scan) throws IOException {
    DateUtility ut = new DateUtility();
    String date = "";
    while (true) {
      iv.viewEnterDate();


      date = scan.next();
      automate.append(date+",");
      if(quitHelper(date))
      {
        quitFlag = true;
        return null;
      }
      if (ut.checkDateValidity(date)) {
        break;
      } else {

        iv.viewErrorInvalidDate();
        return getDate(scan);
      }

    }

    return date.trim();
  }

  private Integer getNoOfShares(Scanner scan) throws IOException {
    String noOfStocks = "";

    while (true) {

      iv.viewEnterNumberOfShares();

      noOfStocks = scan.next();
      automate.append(noOfStocks+",");
      if(quitHelper(noOfStocks))
      {
        quitFlag = true;
        return null;
      }

      try {
        Integer numberOfStocks = Integer.parseInt(noOfStocks);
        break;
      } catch (Exception e) {

        iv.viewErrorSharesInFraction();

        return getNoOfShares(scan);
      }

    }
    return Integer.parseInt(noOfStocks);

  }


  public void savePortfolio(InvestmentModelInterface fc) throws IOException {

    try {

      FileOutputStream file = new FileOutputStream(this.filename);
      ObjectOutputStream out = new ObjectOutputStream(file);

      out.writeObject(fc);

      out.close();
      file.close();


    } catch (IOException ex) {
      iv.printExceptions("Unable to serialize");
    }
  }

  public InvestmentModel retrievePortfolio() {
    InvestmentModel savedObject = null;

    try {

      FileInputStream file = new FileInputStream(this.filename);
      ObjectInputStream in = new ObjectInputStream(file);


      savedObject = (InvestmentModel) in.readObject();

      in.close();
      file.close();

      return savedObject;

    } catch (IOException ex) {
      System.out.println("IOException is caught");
      ex.printStackTrace();
      System.exit(0);
    } catch (ClassNotFoundException ex) {
      System.out.println("ClassNotFoundException is caught");
      ex.printStackTrace();
      System.exit(0);
    }

    return savedObject;

  }


  public void generateTestCases()
  {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("savedFile/generateTests.txt", true))) {


      String replacement = automate.toString();
      replacement = replacement.substring(0, automate.toString().length() - 1);


      String a = "{in = new StringReader(\""+replacement+"\");\n" +
              " Appendable out = new StringBuffer();\n" +
              " iv = new InvestmentView(out);\n" +
              " smc = new StockMarketController(in, iv, im);\n" +
              " smc.startStockMarket();\n" +
              " System.out.println(out.toString());\n" +
              " assertEquals(\"\", out.toString());\n}";
      bw.write(a+"\n\n");

      System.out.println("Done");

    } catch (IOException e) {

      e.printStackTrace();

    }
  }



  public static void main(String args[]) {
//    Runnable runnable = new StockUpdaterServer(); // or an anonymous class, or lambda...
//    Thread thread = new Thread(runnable);
//    thread.start();


    InvestmentModelInterface im = new InvestmentModel();
    InvestmentView iv = new InvestmentView(System.out);


    IStockMarketController sm = new StockMarketController(new InputStreamReader(System.in), iv, im);
    sm.startStockMarket();
  }

}
