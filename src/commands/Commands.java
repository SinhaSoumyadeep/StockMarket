package commands;

import java.io.IOException;
import java.text.ParseException;

/**
 * This interface is used to encapsulate the commands for the controller.
 */
public interface Commands {

  /**
   * this method is used to execute the command.
   *
   * @throws IOException    if the view fails.
   * @throws ParseException if the date is invalid.
   */
  void execute() throws IOException, ParseException;
}
