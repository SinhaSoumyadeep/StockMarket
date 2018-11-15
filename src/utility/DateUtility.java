package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * This class is used to perform the date validation, check the day of the week for a particular
 * date and conversion.
 */
public class DateUtility {

  /**
   * This method checks the validity of the date.
   *
   * @param date takes date as a string.
   * @return if the date is a valid date.
   */
  public boolean checkDateValidity(String date) {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    df.setLenient(false);

    if (date == null) {
      return false;
    }
    try {
      df.parse(date);
    } catch (ParseException e) {

      return false;
    }
    return true;
  }

  /**
   * This method converts a string date to a local date.
   *
   * @param date the date in string format.
   * @return the converted date in local date format.
   */
  public LocalDate stringToDateConverter(String date) {
    LocalDate localDate = LocalDate.parse(date);
    return localDate;

  }

  /**
   * This method yields true if the date paseed is a  weekday.
   *
   * @param timeStamp time stamp as a string.
   * @return true if its a week day else false.
   */
  public boolean isWeekDay(String timeStamp) {
    LocalDate now = stringToDateConverter(timeStamp);

    if (now.getDayOfWeek().toString().equals(DaysOfWeek.SUNDAY.name()) ||
            now.getDayOfWeek().toString().equals(DaysOfWeek.SATURDAY.name())) {
      return false;
    }
    return true;

  }


}
