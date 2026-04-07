package com.beck.beck_demos.budget_app.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * DTO for the Category Performance scorecard.
 */
public class CategoryPerformanceDTO implements Serializable, Comparable<CategoryPerformanceDTO> {
  private static final long serialVersionUID = 1L;

  @JsonProperty("categoryName")
  private String categoryName;
  @JsonProperty("categoryID")
  private String categoryID;
  @JsonProperty("period")
  private String period;

  @JsonProperty("budgetedValue")
  private double budgetedValue;

  @JsonProperty("actualValue")
  private double actualValue;

  @JsonProperty("threshold")
  private double threshold;

  @JsonProperty("transaction_type")
  private String transactionType;

  public CategoryPerformanceDTO() {
  }

  public CategoryPerformanceDTO(String period, double budgetedValue, double actualValue, double threshold) {
    this.categoryName = "";
    this.categoryID = "";
    this.period = period;
    this.budgetedValue = budgetedValue;
    this.actualValue = actualValue;
    this.threshold = threshold;
  }

  /**
   * Helper to keep the code DRY.
   * Max raised to 1.2M to support Annual Mode logic (100k * 12).
   */
  private void validateAmount(double val, String codeHigh, String codeLow) {
    if (val > 1200000.0) {
      throw new IllegalArgumentException(codeHigh + ": Value exceeds maximum limit ($1.2M).");
    }
    if (val < 0.0) {
      throw new IllegalArgumentException(codeLow + ": Value cannot be negative.");
    }
  }

  // --- Getters & Standard Overrides ---
  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    if (categoryName == null || categoryName.trim().isEmpty()) {
      this.categoryName = "";
      return;
    }
    if (categoryName.length() > 100) {
      throw new IllegalArgumentException("CN-101: Category name exceeds 100 characters.");
    }
    if (categoryName.length() < 3) {
      throw new IllegalArgumentException("CN-102: Category name must be at least 3 characters.");
    }
    this.categoryName = categoryName;
  }

  public String getCategoryID() {
    return categoryID;
  }

  public void setCategoryID(String categoryID) {
    if (categoryID == null || categoryID.trim().isEmpty()) {
      this.categoryID = "";
      return;
    }
    if (categoryID.length() != 36) {
      throw new IllegalArgumentException("ID-201: CategoryID must be a 36-character UUID string.");
    }
    this.categoryID = categoryID;
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    // 1. Basic Structure
    if (period == null || !period.contains("/") || period.length() < 6 || period.length() > 10) {
      throw new IllegalArgumentException("PD-301: Period must be in MM/YYYY format. Received: " + period);
    }

    try {
      String[] parts = period.split("/");
      if (parts.length != 2) {
        throw new IllegalArgumentException("PD-302: Period format error. Expected one '/' separator.");
      }

      int month = Integer.parseInt(parts[0]);
      int year = Integer.parseInt(parts[1]);

      if (month < 1 || month > 12) {
        throw new IllegalArgumentException("PD-303: Invalid month (" + month + "). Must be 1-12.");
      }
      if (year < 2000 || year > 2050) {
        throw new IllegalArgumentException("PD-304: Year (" + year + ") out of supported range (2000-2050).");
      }

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("PD-305: Period contains non-numeric values: " + period);
    }

    this.period = period;
  }

  public double getBudgetedValue() {
    return budgetedValue;
  }

  public void setBudgetedValue(double budgetedValue) {
    validateAmount(budgetedValue, "BV-401", "BV-402");
    this.budgetedValue = budgetedValue;
  }

  public double getActualValue() {
    return actualValue;
  }

  public void setActualValue(double actualValue) {
    validateAmount(actualValue, "AV-501", "AV-502");
    this.actualValue = actualValue;
  }

  public double getThreshold() {
    return threshold;
  }

  public void setThreshold(double threshold) {
    validateAmount(threshold, "TH-601", "TH-602");
    this.threshold = threshold;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  @Override
  public String toString() {
    return "CategoryPerformanceDTO{" +
        "categoryName='" + categoryName + '\'' +
        ", categoryID='" + categoryID + '\'' +
        ", period='" + period + '\'' +
        ", actual=" + actualValue +
        ", threshold=" + threshold +
        ", transactionType=" + transactionType +
        '}';
  }

  @Override
  public int compareTo(@NotNull CategoryPerformanceDTO o) {
    if (this.transactionType.compareTo(o.getTransactionType()) < 0) {
      return -1;
    } else if (this.transactionType.compareTo(o.getTransactionType()) > 0) {
      return 1;
    }
    if (this.actualValue - o.actualValue < 0) {
      return -1;
    }
    if (this.actualValue - o.actualValue > 0) {
      return 1;
    }
    return 0;
  }
}