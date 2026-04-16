package com.beck.beck_demos.budget_app.models;

/**
 * @author Jonathan Beck
 * @version 1.0
 * @since 1.0
 */
public class SubCategory_VM extends SubCategory {

  // 1. Private Variables
  private int year;
  private String transactionType;
  private double amount;
  private int count;
  private String sign;

  // 2. Constructors
  public SubCategory_VM() {
    this.setIs_Locked(true);
  }

  public SubCategory_VM(String category_ID, double amount) {
    this.setCategory_ID(category_ID);
    this.amount = amount;
    this.sign = "";
  }

  public SubCategory_VM(SubCategory category) {
    super(category.getCategory_ID(), category.getParentCategoryId(),
        category.getprojection_strategy_ID(), category.getUser_ID(),
        category.getCategory_Name(), category.getcolor_id(),
        category.getTarget_Threshold(), category.getIs_Locked());
  }

  // 3. Getters and Setters
  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public int getCount() {
    return this.count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public String getSign() {
    return this.sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

}