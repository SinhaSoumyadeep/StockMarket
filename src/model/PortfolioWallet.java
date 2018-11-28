package model;

public final class PortfolioWallet {
  private final Double remainingAmount;

  public PortfolioWallet(Double remainingAmount) {
    this.remainingAmount = remainingAmount;
  }

  public Double getRemainingAmount() {
    return remainingAmount;
  }

  public String toString() {
    return ":" + remainingAmount;
  }
}
