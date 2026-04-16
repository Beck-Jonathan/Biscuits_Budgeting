package com.beck.beck_demos.budget_app.models;

import java.util.List;

/**
 * ViewModel for ParentCategory to include aggregate statistics for UI headers.
 * Extends the core model to maintain validation and basic properties.
 */
public class ParentCategory_VM extends ParentCategory {
  private int subcategoryCount;
  private double totalMonthlyTarget;
  private double currentMonthSpent;
  private List<SubCategory> subcategories;

  public ParentCategory_VM() {
    super();
  }

  public ParentCategory_VM(ParentCategory parentCategory) {
    super(parentCategory.getparent_category_id(), parentCategory.getsuper_category_name(), parentCategory.getuser_id(), parentCategory.getcolor_id(), parentCategory.gettransaction_type());
  }

  // Insightful Getters
  public double getRemainingBudget() {
    return totalMonthlyTarget - currentMonthSpent;
  }

  public double getPercentUsed() {
    if (totalMonthlyTarget <= 0) return 0;
    return (currentMonthSpent / totalMonthlyTarget) * 100;
  }

  // Getters and Setters for the new fields
  public int getSubcategoryCount() {
    return subcategoryCount;
  }

  public void setSubcategoryCount(int subcategoryCount) {
    this.subcategoryCount = subcategoryCount;
  }

  public double getTotalMonthlyTarget() {
    return totalMonthlyTarget;
  }

  public void setTotalMonthlyTarget(double totalMonthlyTarget) {
    this.totalMonthlyTarget = totalMonthlyTarget;
  }

  public double getCurrentMonthSpent() {
    return currentMonthSpent;
  }

  public void setCurrentMonthSpent(double currentMonthSpent) {
    this.currentMonthSpent = currentMonthSpent;
  }

  public List<SubCategory> getSubcategories() {
    return subcategories;
  }

  public void setSubcategories(List<SubCategory> subcategories) {
    this.subcategories = subcategories;
  }
}