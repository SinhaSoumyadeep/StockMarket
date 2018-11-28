package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import utility.DateUtility;


public class DollarCostAverageStrategy implements InvestmentStrategyInterface {

  private Double fixedAmount;
  private String startDate;
  private String endDate;
  private Integer frequency;
  private static HashMap<String, List<String>> transactionHistory;
  private String commission;
  static {
    transactionHistory = new HashMap<String,List<String>>();
  }



  public DollarCostAverageStrategy(Double fixedAmount, String startDate, String endDate,
                                   Integer frequency, String commission) {
    DateUtility du = new DateUtility();
    if(du.stringToDateConverter(startDate).isAfter(du.stringToDateConverter(endDate))){
      throw new IllegalArgumentException("Start date cannot be after end date or end date cannot " +
              "be before start date");
    }
    this.fixedAmount = fixedAmount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frequency = frequency;
    this.commission = commission;

  }

  @Override
  public void exceuteStrategyOnPortfolio(String portfolioName, InvestModelInterfaceNew im, String timestamp, HashMap<String, Double> weights) throws ParseException {

    if(im.checkIfPortfolioIsEmpty(portfolioName)){
      throw new IllegalArgumentException("Portfolio has no contents.");
    }

    System.out.println("\n\n Dollar Cost Average \n\n");

    DateUtility d = new DateUtility();

    if(d.stringToDateConverter(timestamp).isAfter(d.stringToDateConverter(this.endDate)))
    {
      timestamp = this.endDate;
    }


    System.out.println(transactionHistory);

    if(transactionHistory.containsKey(portfolioName)) {
      if (!transactionHistory.get(portfolioName).isEmpty()) {
        for (String endDate : transactionHistory.get(portfolioName)) {
          if (d.stringToDateConverter(endDate).isAfter(d.stringToDateConverter(this.startDate))) {
            throw new IllegalArgumentException("strategy is ongoing");
          }
        }
      }
    }

      LocalDate transactionEndDateForSession = d.stringToDateConverter(timestamp);
      LocalDate beginDate = d.stringToDateConverter(startDate);

      LocalDate nextDate = beginDate;

      while(nextDate.isBefore(transactionEndDateForSession)|| nextDate.isEqual(transactionEndDateForSession) )
      {
        System.out.println(">>>>>Buying Stock On:"+nextDate);
        try {
          if (d.isWeekDay(nextDate.toString())) {
            im.investStocks(portfolioName, fixedAmount, weights, nextDate.toString(), commission);
            nextDate = nextDate.plusDays(frequency);
          } else {
            nextDate = nextDate.plusDays(1);
            // im.investStocks(portfolioName,fixedAmount,weights,nextDate.toString(),commission);
          }
        } catch (Exception e){
          /*if(e.getMessage().equals("")){


          }*/
          System.out.println(e.getMessage());
          System.out.println("%%%%%%%%%%%%%%%%%%Holiday%%%%%%%%%%%%%%%%%%%%");
          nextDate = nextDate.plusDays(1);
        }
        System.out.println("**************************************************************************************\n\n\n");
//        im.investStocks(portfolioName,fixedAmount,weights,nextDate.toString(),commission);



    }










//    IPortfolio p = portfolio;
//
//    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
//    System.out.println(p.lastestTransactionDate());
//    System.out.println("Portfolio>>>>>>>>>>>>>>>>>>>"+p);


    //im.investStocks(portfolioName,this.fixedAmount,im.viewWeights(portfolioName), timestamp);
    if(!transactionHistory.containsKey(portfolioName))
    {
      List<String> transact = new ArrayList<String>();
      transact.add(this.endDate);
      transactionHistory.put(portfolioName,transact);
    }
    else {
      transactionHistory.get(portfolioName).add(this.endDate);
    }
  }
}
