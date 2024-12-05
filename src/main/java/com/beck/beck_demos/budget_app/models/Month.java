package com.beck.beck_demos.budget_app.models;

public class Month {


  private int mortgage_id;
  private int month;
  private Double beginning_bal;
  private Double rate;
  private final Double payment;
  private Double ending_balance;

  public Month(int mortgage_ID,int month, Double beginning_bal, Double rate, Double payment) {
    this.mortgage_id=mortgage_ID;
    this.month=month;
    this.beginning_bal = beginning_bal;
    this.rate = rate;
    this.payment = payment;
    this.ending_balance = beginning_bal+(rate*beginning_bal/1200)-payment;
  }

  public Double getBeginning_bal() {
    return beginning_bal;
  }

  public void setBeginning_bal(Double beginning_bal) {
    this.beginning_bal = beginning_bal;
  }

  public Double getRate() {
    return rate;
  }

  public void setRate(Double rate) {
    this.rate = rate;
  }

  public Double getPayment() {
    return payment;
  }

  public void setPayment(Double payment) {
    payment = payment;
  }

  public Double getEnding_balance() {
    return ending_balance;
  }

  public void setEnding_balance(Double ending_balance) {
    this.ending_balance = ending_balance;
  }

  public int getMortgage_id() {
    return mortgage_id;
  }

  public void setMortgage_id(int mortgage_id) {
    this.mortgage_id = mortgage_id;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }
}
