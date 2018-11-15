package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateUtility {


  public boolean checkDateValidity(String date) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setLenient(false);

    if (date == null) {
      return false;
    }
    try {
      df.parse(date);
    } catch (ParseException e) {
      //e.printStackTrace();
      return false;
    }
    return true;
  }


  public LocalDate stringToDateConverter(String date) {
//    System.out.println("the date received is date  "+date);
      LocalDate localDate = LocalDate.parse(date);
      return localDate;

  }

  public boolean isWeekDay(String timeStamp)
  {
    LocalDate now = stringToDateConverter(timeStamp);

    if(now.getDayOfWeek().toString().equals(DaysOfWeek.SUNDAY.name())||
            now.getDayOfWeek().toString().equals(DaysOfWeek.SATURDAY.name()))
    {
      return false;
    }
    return true;

  }



}
