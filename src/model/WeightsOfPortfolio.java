package model;

import java.util.HashMap;

/**
 * This class is a representation of the weights associated with a particular portfolio. thisis an
 * immutable class.
 */
public final class WeightsOfPortfolio {


  private final HashMap<String, Double> weight;

  /**
   * Instantiates a new Weights of portfolio.
   *
   * @param weight the weight
   */
  public WeightsOfPortfolio(HashMap<String, Double> weight) {
    this.weight = weight;
  }

  /**
   * Gets weight.
   *
   * @return the weight
   */
  public HashMap<String, Double> getWeight() {
    return weight;
  }

}
