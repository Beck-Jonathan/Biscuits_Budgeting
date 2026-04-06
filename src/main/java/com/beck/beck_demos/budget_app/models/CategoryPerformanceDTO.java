package com.beck.beck_demos.budget_app.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * DTO for the Category Performance scorecard.
 * Represents a single month's Budget vs. Actual comparison.
 */
public class CategoryPerformanceDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("period")
  private String period; // Format: "MM/YYYY" (e.g., "03/2026")

  @JsonProperty("budgetedValue")
  private double budgetedValue;

  @JsonProperty("actualValue")
  private double actualValue;

  @JsonProperty("threshold")
  private double threshold;

  // Default constructor for Jackson
  public CategoryPerformanceDTO() {
  }

  public CategoryPerformanceDTO(String period, double budgetedValue, double actualValue, double threshold) {
    this.period = period;
    this.budgetedValue = budgetedValue;
    this.actualValue = actualValue;
    this.threshold = threshold;
  }

  // Getters and Setters
  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public double getBudgetedValue() {
    return budgetedValue;
  }

  public void setBudgetedValue(double budgetedValue) {
    this.budgetedValue = budgetedValue;
  }

  public double getActualValue() {
    return actualValue;
  }

  public void setActualValue(double actualValue) {
    this.actualValue = actualValue;
  }

  public double getThreshold() {
    return threshold;
  }

  public void setThreshold(double threshold) {
    this.actualValue = threshold;
  }

  @Override
  public String toString() {
    return "CategoryPerformanceDTO{" +
        "period='" + period + '\'' +
        ", budgeted=" + budgetedValue +
        ", actual=" + actualValue +
        ", threshold=" + threshold +
        '}';
  }
}