package model;

import java.util.HashMap;


public final class WeightsOfPortfolio {


  private final HashMap<String, Double> weight;

  public WeightsOfPortfolio(HashMap<String, Double> weight) {
    this.weight = weight;
  }

  public HashMap<String, Double> getWeight() {
    return weight;
  }

}
