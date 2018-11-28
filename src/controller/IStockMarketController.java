package controller;

import java.text.ParseException;

/**
 * This class represents a StockMarket controller and performs all the operations.
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
 * the  Appendable object Invalid Options plus any informative message about why the option was
 * invalid, and resume waiting for valid input.</p>
 *
 * <p>Conditions:
 * "Q" or "q" can be used only to return to the main menu or to quit the application. It cannot be
 * entered as aticker symbol or create a new portfolio.
 *
 * The portfolio name cannot be space separated. It needs to be in a single word. The portfolio can
 * accept single letter or number except "Q" or "q" However, it is not recommended.
 *
 * </p>
 */
public interface IStockMarketController {
  /**
   * Start the stock market where the user of the application can buy stocks, create portfolio, view
   * the composition of the portfolio. the user can quit the application at any point.
   *
   * @throws ParseException if the date comparision fails.
   */
  public void startStockMarket() throws ParseException;
}
