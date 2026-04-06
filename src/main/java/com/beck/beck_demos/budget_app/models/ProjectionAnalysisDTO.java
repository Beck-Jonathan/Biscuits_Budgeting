package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProjectionAnalysisDTO implements Comparable<ProjectionAnalysisDTO> {
  private String monthDate; // yyyy-MM-dd
  private Double historicalTruth;
  private Double regression;
  private Double avgStrict;
  private Double lvcf;
  private Double inflation;
  private Double alphaSpike;
  private Double zeroSum;

  public ProjectionAnalysisDTO() {
  }

  public ProjectionAnalysisDTO(String monthDate, Double historicalTruth, Double regression, Double avgStrict, Double lvcf, Double inflation, Double alphaSpike, Double zeroSum) {

    this.monthDate = monthDate;
    this.historicalTruth = historicalTruth;
    this.regression = regression;
    this.avgStrict = avgStrict;
    this.lvcf = lvcf;
    this.inflation = inflation;
    this.alphaSpike = alphaSpike;
    this.zeroSum = zeroSum;
  }

  public String getMonthDate() {
    return monthDate;
  }

  public void setMonthDate(String monthDate) {
    try {
      Calendar cal = Calendar.getInstance();
// Set Lower Limit: Jan 1, 2000
      cal.set(2000, Calendar.JANUARY, 1, 0, 0, 0);
      Date lowerLimit = cal.getTime();
// Set Upper Limit: Dec 31, 2050
      cal.set(2050, Calendar.DECEMBER, 31, 23, 59, 59);
      Date upperLimit = cal.getTime();
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate = dateFormat.parse(monthDate);
      if (parsedDate.before(lowerLimit) || parsedDate.after(upperLimit)) {
        throw new IllegalArgumentException("Invalid month date");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid month date");
    }
    this.monthDate = monthDate;
  }

  /**
   * <p> Gets the historicalTruth of the associated projectionanalysisdto object </p>
   *
   * @return the historicalTruth of this projectionanalysisdto object.
   */
  public Double getHistoricalTruth() {
    return historicalTruth;
  }

  /**
   * <p> Sets the historicalTruth of the associated projectionanalysisdto object </p>
   *
   * @param historicalTruth the historicaltruth of the projectionanalysisdto,
   *                        throws IllegalArgumentException if historicalTruth is outside of a logical range
   */
  public void setHistoricalTruth(Double historicalTruth) {
    if (historicalTruth == null) {
      this.historicalTruth = null;
      return;
    }
    if (historicalTruth < -60000 || historicalTruth > 60000) {
      throw new IllegalArgumentException("historicalTruth Can Not Be Negative");
    }
    this.historicalTruth = historicalTruth;
  }

  /**
   * <p> Gets the regression of the associated projectionanalysisdto object </p>
   *
   * @return the regression of this projectionanalysisdto object.
   */
  public Double getRegression() {
    return regression;
  }

  /**
   * <p> Sets the regression of the associated projectionanalysisdto object </p>
   *
   * @param regression the regression of the projectionanalysisdto,
   *                   throws IllegalArgumentException if regression is outside of a logical range
   */
  public void setRegression(Double regression) {
    if (regression == null) {
      this.regression = null;
      return;
    }
    if (regression < -60000 || regression > 45000) {
      throw new IllegalArgumentException("regression Can Not Be Negative");
    }
    this.regression = regression;
  }

  /**
   * <p> Gets the avgStrict of the associated projectionanalysisdto object </p>
   *
   * @return the avgStrict of this projectionanalysisdto object.
   */
  public Double getAvgStrict() {
    return avgStrict;
  }

  /**
   * <p> Sets the avgStrict of the associated projectionanalysisdto object </p>
   *
   * @param avgStrict the avgstrict of the projectionanalysisdto,
   *                  throws IllegalArgumentException if avgStrict is outside of a logical range
   */
  public void setAvgStrict(Double avgStrict) {
    if (avgStrict == null) {
      this.avgStrict = null;
      return;
    }
    if (avgStrict < -60000 || avgStrict > 45000) {
      throw new IllegalArgumentException("avgStrict Can Not Be Negative");
    }
    this.avgStrict = avgStrict;
  }

  /**
   * <p> Gets the lvcf of the associated projectionanalysisdto object </p>
   *
   * @return the lvcf of this projectionanalysisdto object.
   */
  public Double getLvcf() {
    return lvcf;
  }

  /**
   * <p> Sets the lvcf of the associated projectionanalysisdto object </p>
   *
   * @param lvcf the lvcf of the projectionanalysisdto,
   *             throws IllegalArgumentException if lvcf is outside of a logical range
   */
  public void setLvcf(Double lvcf) {
    if (lvcf == null) {
      this.lvcf = null;
      return;
    }
    if (lvcf < -60000 || lvcf > 45000) {
      throw new IllegalArgumentException("lvcf Can Not Be Negative");
    }
    this.lvcf = lvcf;
  }

  /**
   * <p> Gets the inflation of the associated projectionanalysisdto object </p>
   *
   * @return the inflation of this projectionanalysisdto object.
   */
  public Double getInflation() {
    return inflation;
  }

  /**
   * <p> Sets the inflation of the associated projectionanalysisdto object </p>
   *
   * @param inflation the inflation of the projectionanalysisdto,
   *                  throws IllegalArgumentException if inflation is outside of a logical range
   */
  public void setInflation(Double inflation) {
    if (inflation == null) {
      this.inflation = null;
      return;
    }
    if (inflation < -60000 || inflation > 45000) {
      throw new IllegalArgumentException("inflation Can Not Be Negative");
    }
    this.inflation = inflation;
  }

  /**
   * <p> Gets the alphaSpike of the associated projectionanalysisdto object </p>
   *
   * @return the alphaSpike of this projectionanalysisdto object.
   */
  public Double getAlphaSpike() {
    return alphaSpike;
  }

  /**
   * <p> Sets the alphaSpike of the associated projectionanalysisdto object </p>
   *
   * @param alphaSpike the alphaspike of the projectionanalysisdto,
   *                   throws IllegalArgumentException if alphaSpike is outside of a logical range
   */
  public void setAlphaSpike(Double alphaSpike) {
    if (alphaSpike == null) {
      this.alphaSpike = null;
      return;
    }
    if (alphaSpike < -60000 || alphaSpike > 45000) {
      throw new IllegalArgumentException("alphaSpike Can Not Be Negative");
    }
    this.alphaSpike = alphaSpike;
  }

  /**
   * <p> Gets the zeroSum of the associated projectionanalysisdto object </p>
   *
   * @return the zeroSum of this projectionanalysisdto object.
   */
  public Double getZeroSum() {
    return zeroSum;
  }

  /**
   * <p> Sets the zeroSum of the associated projectionanalysisdto object </p>
   *
   * @param zeroSum the zerosum of the projectionanalysisdto,
   *                throws IllegalArgumentException if zeroSum is outside of a logical range
   */
  public void setZeroSum(Double zeroSum) {
    if (zeroSum == null) {
      this.zeroSum = null;
      return;
    }
    if (zeroSum != 0) {
      throw new IllegalArgumentException("zeroSum Must be zero!");
    }
    this.zeroSum = zeroSum;
  }

  @Override
  public int compareTo(@NotNull ProjectionAnalysisDTO o) {

    if (this.monthDate.compareTo(o.monthDate) < 0) {
      return -1;
    } else if (this.monthDate.compareTo(o.monthDate) > 0) {
      return 1;
    }
    if (this.historicalTruth.compareTo(o.historicalTruth) < 0) {
      return -1;
    } else if (this.historicalTruth.compareTo(o.historicalTruth) > 0) {
      return 1;
    }
    if (this.regression.compareTo(o.regression) < 0) {
      return -1;
    } else if (this.regression.compareTo(o.regression) > 0) {
      return 1;
    }
    if (this.avgStrict.compareTo(o.avgStrict) < 0) {
      return -1;
    } else if (this.avgStrict.compareTo(o.avgStrict) > 0) {
      return 1;
    }
    if (this.lvcf.compareTo(o.lvcf) < 0) {
      return -1;
    } else if (this.lvcf.compareTo(o.lvcf) > 0) {
      return 1;
    }
    if (this.inflation.compareTo(o.inflation) < 0) {
      return -1;
    } else if (this.inflation.compareTo(o.inflation) > 0) {
      return 1;
    }
    if (this.alphaSpike.compareTo(o.alphaSpike) < 0) {
      return -1;
    } else if (this.alphaSpike.compareTo(o.alphaSpike) > 0) {
      return 1;
    }
    if (this.zeroSum.compareTo(o.zeroSum) < 0) {
      return -1;
    } else if (this.zeroSum.compareTo(o.zeroSum) > 0) {
      return 1;
    }
    return 0;
  }

}

