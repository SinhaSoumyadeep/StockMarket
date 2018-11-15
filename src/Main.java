//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.concurrent.TimeUnit;
//
//import model.InvestmentModel;
//import model.Portfolio;
//import server.StockUpdaterServer;
//import service.StockMarketDAO;
//import service.StockMarketDAOImpl;
//import utlity.Options;
//import view.InvestmentView;
//
//public class Main {
//
//  public static void main(String[] args) throws IOException{
//
//
//    Runnable runnable = new StockUpdaterServer(); // or an anonymous class, or lambda...
//    Thread thread = new Thread(runnable);
//    thread.start();
//
//    //Implement saving option in Controller.
//    InvestmentModel im;
//    File f = new File("savedFile/gamedata.txt");
//    if(f.exists() && !f.isDirectory()) {
//      im = retrieveGame();
//    }
//    else {
//      im = new InvestmentModel();
//    }
//
//    InvestmentView iv = new InvestmentView(System.out);
//    System.out.println("********************  WELCOME TO INVESTMENT PORTAL ************************\n\n");
//    while (true) {
//
//      Scanner scanner = new Scanner(System.in);
//
//      System.out.println("Choose E to Evaluate Portfolio, B to buy Stocks\n");
//
//      String option = scanner.next();
//      switch (option.toUpperCase()) {
//        case "E":
//          Scanner scanner1 = new Scanner(System.in);
//          System.out.println("Enter portfolio name: \n");
//          String portfolioName = scanner1.next();
//          System.out.println("Enter the date in yyyy-mm-dd format: \n");
//          String date = scanner1.next();
//          System.out.println("Enter D for detailed statement or M for Mini Statement: \n");
//          String optionStatement = scanner1.next();
//          if (optionStatement.toUpperCase().equals("D")) {
//            iv.displayPortfolio(im.evaluatePortfolio(portfolioName.trim(), date.trim()), Options.DETAILED_STATEMENT);
//          } else {
//            iv.displayPortfolio(im.evaluatePortfolio(portfolioName.trim(), date.trim()), Options.MINI_STATEMENT);
//          }
//          break;
//        case "B":
//          Scanner scanner2 = new Scanner(System.in);
//          System.out.println("Enter the ticker: ");
//          String ticker = scanner2.next();
//
//          System.out.println("Enter the date in yyyy-mm-dd format: ");
//          String date1 = scanner2.next();
//
//          System.out.println("Enter the number of stocks you want to buy: ");
//          Integer shares = scanner2.nextInt();
//
//          System.out.println("Enter the portfolio name: ");
//          String portfolioName1 = scanner2.next();
//
//          im.buyStocks(ticker.trim(), date1.trim(), shares, portfolioName1.trim());
//          saveGame(im);
//          break;
//        case "V":
//          iv.displayAllPortfolioNames(im.getPortfolioNames());
//          break;
//
//
//
//      }
//
//
//    }
//
//
//
//
//
//
//
//
//
//  /*  InvestmentModel im = new InvestmentModel();
////    im.buyStocks("GOOG","2018-11-07",10,"abc");
////    im.buyStocks("GOOG","2018-11-08",90,"abc");
//    im.buyStocks("AAPL","2018-11-06",5,"abc");
//    im.buyStocks("AAPL","2018-11-07",5,"abc");
//    im.buyStocks("AAPL","2018-11-13",5,"abc");
//    im.buyStocks("GOOG","2018-11-08",90,"bcd");
//    im.buyStocks("MSFT","2018-11-13",10,"abc");
//    im.buyStocks("MSFT","2018-11-07",5,"abc");
//    im.buyStocks("GOOG","2018-11-07",5,"abc");
//
//    System.out.println(im.evaluatePortfolio("abc","2018-11-11", Options.DETAILED_STATEMENT));*/
//}
//
//  public static void saveGame(InvestmentModel fc)
//  {
//    String filename = "savedFile/gamedata.txt";
//    // Serialization
//    try
//    {
//      //Saving of object in a file
//      FileOutputStream file = new FileOutputStream(filename);
//      ObjectOutputStream out = new ObjectOutputStream(file);
//
//      // Method for serialization of object
//      out.writeObject(fc);
//
//      out.close();
//      file.close();
//
//      System.out.println("Object has been serialized");
//
//    }
//
//    catch(IOException ex)
//    {
//      ex.printStackTrace();
//      System.out.println("IOException is caught");
//    }
//  }
//
//  public static InvestmentModel retrieveGame(){
//    InvestmentModel savedObject = null;
//    String filename = "savedFile/gamedata.txt";
//    // Deserialization
//    try {
//      // Reading the object from a file
//      FileInputStream file = new FileInputStream(filename);
//      ObjectInputStream in = new ObjectInputStream(file);
//
//      // Method for deserialization of object
//      savedObject = (InvestmentModel) in.readObject();
//
//      in.close();
//      file.close();
//
//      return savedObject;
//
//    }
//
//    catch(IOException ex)
//    {
//      System.out.println("IOException is caught");
//      ex.printStackTrace();
//      System.exit(0);
//    }
//
//    catch(ClassNotFoundException ex)
//    {
//      System.out.println("ClassNotFoundException is caught");
//      ex.printStackTrace();
//      System.exit(0);
//    }
//
//    return savedObject;
//
//  }
//
//}
