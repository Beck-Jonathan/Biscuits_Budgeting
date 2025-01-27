package com.beck.beck_demos.budget_app.models;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Mortgage implements Comparable<Mortgage> {
  private Integer Mortgage_ID;
  private Integer User_ID;
  private Double Present_Value;
  private Double Future_Value;
  private Double Interest_Rate;
  private Double Monthly_Payment;
  private Double Extra_Payment;
  private Integer Remaining_Term;

  public Mortgage(){}

  public Mortgage(Integer Mortgage_ID, Integer User_ID, Double Present_Value, Double Future_Value, Double Interest_Rate, Double Monthly_Payment, Double Extra_Payment, Integer Remaining_Term) {

    this.Mortgage_ID = Mortgage_ID;
    this.User_ID = User_ID;
    this.Present_Value = Present_Value;
    this.Future_Value = Future_Value;
    this.Interest_Rate = Interest_Rate;
    this.Monthly_Payment = Monthly_Payment;
    this.Extra_Payment = Extra_Payment;
    this.Remaining_Term = Remaining_Term;
  }

  public Mortgage(Integer Mortgage_ID) {

    this.Mortgage_ID = Mortgage_ID;
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
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    if (User_ID<0||User_ID>10000){
      throw new IllegalArgumentException("User_ID Can Not Be Negative");
    }
    this.User_ID = User_ID;
  }
  public Double getPresent_Value() {
    return Present_Value;
  }
  public void setPresent_Value(double Present_Value) {
    if (Present_Value<0||Present_Value>10000){
      throw new IllegalArgumentException("Present_Value Can Not Be Negative");
    }
    this.Present_Value = Present_Value;
  }
  public Double getFuture_Value() {
    return Future_Value;
  }
  public void setFuture_Value(double Future_Value) {
    if (Future_Value<0||Future_Value>10000){
      throw new IllegalArgumentException("Future_Value Can Not Be Negative");
    }
    this.Future_Value = Future_Value;
  }
  public Double getInterest_Rate() {
    return Interest_Rate;
  }
  public void setInterest_Rate(double Interest_Rate) {
    if (Interest_Rate<0||Interest_Rate>10000){
      throw new IllegalArgumentException("Interest_Rate Can Not Be Negative");
    }
    this.Interest_Rate = Interest_Rate;
  }
  public Double getMonthly_Payment() {
    return Monthly_Payment;
  }
  public void setMonthly_Payment(double Monthly_Payment) {
    if (Monthly_Payment<0||Monthly_Payment>10000){
      throw new IllegalArgumentException("Monthly_Payment Can Not Be Negative");
    }
    this.Monthly_Payment = Monthly_Payment;
  }
  public Double getExtra_Payment() {
    return Extra_Payment;
  }
  public void setExtra_Payment(double Extra_Payment) {
    if (Extra_Payment<0||Extra_Payment>10000){
      throw new IllegalArgumentException("Extra_Payment Can Not Be Negative");
    }
    this.Extra_Payment = Extra_Payment;
  }
  public Integer getRemaining_Term() {
    return Remaining_Term;
  }
  public void setRemaining_Term(Integer Remaining_Term) {
    if (Remaining_Term<0||Remaining_Term>10000){
      throw new IllegalArgumentException("Remaining_Term Can Not Be Negative");
    }
    this.Remaining_Term = Remaining_Term;
  }
  @Override
  public int compareTo(@NotNull Mortgage o) {
    if (this.Mortgage_ID.compareTo(o.Mortgage_ID)<0){
      return -1;
    }
    else if(this.Mortgage_ID.compareTo(o.Mortgage_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Present_Value.compareTo(o.Present_Value)<0){
      return -1;
    }
    else if(this.Present_Value.compareTo(o.Present_Value) > 0){
      return 1;
    }
    if (this.Future_Value.compareTo(o.Future_Value)<0){
      return -1;
    }
    else if(this.Future_Value.compareTo(o.Future_Value) > 0){
      return 1;
    }
    if (this.Interest_Rate.compareTo(o.Interest_Rate)<0){
      return -1;
    }
    else if(this.Interest_Rate.compareTo(o.Interest_Rate) > 0){
      return 1;
    }
    if (this.Monthly_Payment.compareTo(o.Monthly_Payment)<0){
      return -1;
    }
    else if(this.Monthly_Payment.compareTo(o.Monthly_Payment) > 0){
      return 1;
    }
    if (this.Extra_Payment.compareTo(o.Extra_Payment)<0){
      return -1;
    }
    else if(this.Extra_Payment.compareTo(o.Extra_Payment) > 0){
      return 1;
    }
    if (this.Remaining_Term.compareTo(o.Remaining_Term)<0){
      return -1;
    }
    else if(this.Remaining_Term.compareTo(o.Remaining_Term) > 0){
      return 1;
    }
    return 0;
  }

}

