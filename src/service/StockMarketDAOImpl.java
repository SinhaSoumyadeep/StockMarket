package service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class StockMarketDAOImpl implements StockMarketDAO {

  URL url = null;

  private String fetchData(String ticker){

    ArrayList<String> apiKey = new ArrayList<String>(){
      {
        add("IH89Q5ULXTKFGJDN");
        add("KDFVUFANR0SKZ5TW");
      }
    };

    Integer api_counter = 0;
    try {
     /*
     create the URL. This is the query to the web service. The query string
     includes the type of query (DAILY stock prices), stock symbol to be
     looked up, the API key and the format of the returned
     data (comma-separated values:csv). This service also supports JSON
     which you are welcome to use.
      */
      //https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo


      url = new URL("https://www.alphavantage"
              + ".co/query?function=TIME_SERIES_DAILY"
              + "&outputsize=full"
              + "&symbol"
              + "=" + ticker + "&apikey="+apiKey.get(0)+"&datatype=csv");
    }
    catch (MalformedURLException e) {
      throw new RuntimeException("the alphavantage API has either changed or "
              + "no longer works");
    }


    InputStream in = null;
    StringBuilder output = new StringBuilder();

    try {
     /*
     Execute this query. This returns an InputStream object.
     In the csv format, it returns several lines, each line being separated
     by commas. Each line contains the date, price at opening time, highest
     price for that date, lowest price for that date, price at closing time
     and the volume of trade (no. of shares bought/sold) on that date.

     This is printed below.
      */
      in = url.openStream();
      int b;

      while ((b=in.read())!=-1) {
        output.append((char)b);
      }
    }
    catch (IOException e) {
      throw new IllegalArgumentException("No price data found for "+ticker);
    }


    if(output.toString().contains("Our standard API call frequency is 5 calls per minute and 500 calls per day"))
    {
      throw new IllegalArgumentException("5 calls per minute and 500 calls per day");
    }
   // System.out.println(output);
    return output.toString().substring(output.toString().indexOf('\n')+1);
  }


  @Override
  public String getCompanyListing(String ticker) {

    String data = fetchData(ticker);
    return data.trim();
  }




}

