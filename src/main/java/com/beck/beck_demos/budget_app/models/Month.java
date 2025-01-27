package com.beck.beck_demos.budget_app.models;
import org.jetbrains.annotations.NotNull;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Month implements Comparable<Month> {
  private Integer Mortgage_ID;
  private Integer Month;
  private Double Begin_Bal;
  private Double Rate;
  private Double Payment;
  private Double Ending_Bal;

  public Month(){}

  public Month(Integer Mortgage_ID, Integer Month, Double Begin_Bal, Double Rate, Double Payment, Double Ending_Bal) {

    this.Mortgage_ID = Mortgage_ID;
    this.Month = Month;
    this.Begin_Bal = Begin_Bal;
    this.Rate = Rate;
    this.Payment = Payment;

    this.Ending_Bal = Begin_Bal+(Rate*Begin_Bal/1200)-Payment;
  }

  public Month(Integer Mortgage_ID, Integer Month) {

    this.Mortgage_ID = Mortgage_ID;
    this.Month = Month;
  }
  public Integer getMortgage_ID() {
    return Mortgage_ID;
  }
  public void setMortgage_ID(Integer Mortgage_ID) {
    if (Mortgage_ID<0||Mortgage_ID>10000){
      throw new IllegalArgumentException("Mortgage_ID Can Not Be Negative");
    }
    this.Mortgage_ID = Mortgage_ID;
  }
  public Integer getMonth() {
    return Month;
  }
  public void setMonth(Integer Month) {
    if (Month<0||Month>10000){
      throw new IllegalArgumentException("Month Can Not Be Negative");
    }
    this.Month = Month;
  }
  public Double getBegin_Bal() {
    return Begin_Bal;
  }
  public void setBegin_Bal(double Begin_Bal) {
    if (Begin_Bal<0||Begin_Bal>10000){
      throw new IllegalArgumentException("Begin_Bal Can Not Be Negative");
    }
    this.Begin_Bal = Begin_Bal;
  }
  public Double getRate() {
    return Rate;
  }
  public void setRate(double Rate) {
    if (Rate<0||Rate>10000){
      throw new IllegalArgumentException("Rate Can Not Be Negative");
    }
    this.Rate = Rate;
  }
  public Double getPayment() {
    return Payment;
  }
  public void setPayment(double Payment) {
    if (Payment<0||Payment>10000){
      throw new IllegalArgumentException("Payment Can Not Be Negative");
    }
    this.Payment = Payment;
  }
  public Double getEnding_Bal() {
    return Ending_Bal;
  }
  public void setEnding_Bal(double Ending_Bal) {
    if (Ending_Bal<0||Ending_Bal>10000){
      throw new IllegalArgumentException("Ending_Bal Can Not Be Negative");
    }
    this.Ending_Bal = Ending_Bal;
  }
  @Override
  public int compareTo(@NotNull Month o) {
    if (this.Mortgage_ID.compareTo(o.Mortgage_ID)<0){
      return -1;
    }
    else if(this.Mortgage_ID.compareTo(o.Mortgage_ID) > 0){
      return 1;
    }
    if (this.Month.compareTo(o.Month)<0){
      return -1;
    }
    else if(this.Month.compareTo(o.Month) > 0){
      return 1;
    }
    if (this.Begin_Bal.compareTo(o.Begin_Bal)<0){
      return -1;
    }
    else if(this.Begin_Bal.compareTo(o.Begin_Bal) > 0){
      return 1;
    }
    if (this.Rate.compareTo(o.Rate)<0){
      return -1;
    }
    else if(this.Rate.compareTo(o.Rate) > 0){
      return 1;
    }
    if (this.Payment.compareTo(o.Payment)<0){
      return -1;
    }
    else if(this.Payment.compareTo(o.Payment) > 0){
      return 1;
    }
    if (this.Ending_Bal.compareTo(o.Ending_Bal)<0){
      return -1;
    }
    else if(this.Ending_Bal.compareTo(o.Ending_Bal) > 0){
      return 1;
    }
    return 0;
  }

}

