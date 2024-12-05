package com.beck.beck_demos.budget_app.models;

import java.util.ArrayList;

public class Mortgage {
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
  public Integer getMortgage_ID() {
    return Mortgage_ID;
  }
  public void setMortgage_ID(Integer Mortgage_ID) {
    this.Mortgage_ID = Mortgage_ID;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public Double getPresent_Value() {
    return Present_Value;
  }
  public void setPresent_Value(Double Present_Value) {
    this.Present_Value = Present_Value;
  }
  public Double  calcPresent_Value() {
    //not implemented
    return 0d;
  }
  public Double getFuture_Value() {
    return Future_Value;
  }
  public void setFuture_Value(Double Future_Value) {
    this.Future_Value = Future_Value;
  }
  public Double  calcFuture_Value() {
    //not implemented
    return 0d;
  }
  public Double getInterest_Rate() {
    return Interest_Rate;
  }
  public void setInterest_Rate(Double Interest_Rate) {
    this.Interest_Rate = Interest_Rate;
  }
  public Double  calcInterest_Rate() {
    //not implemented
    return 0d;
  }
  public Double getMonthly_Payment() {
    return Monthly_Payment;
  }
  public void setMonthly_Payment(Double Monthly_Payment) {
    this.Monthly_Payment = Monthly_Payment;
  }
  public Double  calcMonthly_Payment() {
    //not implemented
    return 0d;
  }
  public Double getExtra_Payment() {
    return Extra_Payment;
  }
  public void setExtra_Payment(Double Extra_Payment) {
    this.Extra_Payment = Extra_Payment;
  }

  public Double  calcExtra_Payment() {
    //not implemented
    return 0d;
  }

  public Integer getRemaining_Term() {
    return Remaining_Term;
  }
  public void setRemaining_Term(Integer Remaining_Term) {
    this.Remaining_Term = Remaining_Term;
  }
  public int calcRemaining_Term() {
    //not implemented yet
    return 0;
  }


}