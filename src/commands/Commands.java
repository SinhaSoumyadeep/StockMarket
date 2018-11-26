package commands;

import java.io.IOException;
import java.text.ParseException;

import model.InvestmentModelInterface;
import view.InvestmentViewInterface;

public interface Commands {

  void execute() throws IOException, ParseException;
}
