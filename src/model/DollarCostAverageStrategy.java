package model;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import utility.DateUtility;


public class DollarCostAverageStrategy implements InvestmentStrategyInterface {

  private Double fixedAmount;
  private String startDate;
  private String endDate;
  private Integer frequency;
  private List<String> transactionHistory;

  public DollarCostAverageStrategy(Double fixedAmount, String startDate, String endDate,
                                   Integer frequency) {
    this.fixedAmount = fixedAmount;
    this.startDate = startDate;
    this.endDate = endDate;
    this.frequency = frequency;
    this.transactionHistory = new ArrayList<>();
  }

  @Override
  public void exceuteStrategyOnPortfolio(IPortfolio portfolio, InvestModelInterfaceNew im, String timestamp) throws ParseException {

    DateUtility d = new DateUtility();

    if(transactionHistory.contains(timestamp)){
      return;
    }
    else{
      LocalDate transactionEndDateForSession = d.stringToDateConverter(timestamp);
      LocalDate beginDate = d.stringToDateConverter(startDate);

      LocalDate nextDate = beginDate;

      while(nextDate.isBefore(transactionEndDateForSession)|| nextDate.isEqual(transactionEndDateForSession) )
      {
        System.out.println(nextDate);

        nextDate = nextDate.plusDays(frequency);
      }


    }










//    IPortfolio p = portfolio;
//
//    System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
//    System.out.println(p.lastestTransactionDate());
//    System.out.println("Portfolio>>>>>>>>>>>>>>>>>>>"+p);


    //im.investStocks(portfolioName,this.fixedAmount,im.viewWeights(portfolioName), timestamp);
  }
}
