package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Mortgage implements Comparable<Mortgage> {
  private String Mortgage_ID;
  private Integer User_ID;
  private String Nickname;
  private Double Present_Value;
  private Double Future_Value;
  private Double Interest_Rate;
  private Double Monthly_Payment;
  private Double Extra_Payment;
  private Integer Remaining_Term;

  public Mortgage(){}

  public Mortgage(String Mortgage_ID, Integer User_ID, String Nickname, Double Present_Value, Double Future_Value, Double Interest_Rate, Double Monthly_Payment, Double Extra_Payment, Integer Remaining_Term) {

    this.Mortgage_ID = Mortgage_ID;
    this.User_ID = User_ID;
    this.Nickname = Nickname;
    this.Present_Value = Present_Value;
    this.Future_Value = Future_Value;
    this.Interest_Rate = Interest_Rate;
    this.Monthly_Payment = Monthly_Payment;
    this.Extra_Payment = Extra_Payment;
    this.Remaining_Term = Remaining_Term;
  }

  public Mortgage(String Mortgage_ID) {

    this.Mortgage_ID = Mortgage_ID;
  }

  /**
   * <p> Gets the Mortgage_ID of the associated Mortgage object </p>
   * @return the Mortgage_ID of this Mortgage object.
   */
  public String getMortgage_ID() {
    return Mortgage_ID;
  }

  /**
   * <p> Sets the Mortgage_ID of the associated Mortgage object </p>
   * @param Mortgage_ID the mortgage_id of the mortgage,
   * throws IllegalArgumentException if Mortgage_ID under 3 characters or longer than 36 characters
   */
  public void setMortgage_ID(String Mortgage_ID) {
    if(Mortgage_ID.length()<36){
      throw new IllegalArgumentException("Mortgage_ID is too short.");
    }
    if(Mortgage_ID.length()>36){
      throw new IllegalArgumentException("Mortgage_ID is too long.");
    }
    this.Mortgage_ID = Mortgage_ID;
  }

  /**
   * <p> Gets the User_ID of the associated Mortgage object </p>
   * @return the User_ID of this Mortgage object.
   */
  public Integer getUser_ID() {
    return User_ID;
  }

  /**
   * <p> Sets the User_ID of the associated Mortgage object </p>
   * @param User_ID the user_id of the mortgage,
   * throws IllegalArgumentException if User_ID is negative or greater than 10,000
   */
  public void setUser_ID(Integer User_ID) {
    if (User_ID<0||User_ID>10000){
      throw new IllegalArgumentException("User_ID Can Not Be Negative");
    }
    this.User_ID = User_ID;
  }

  /**
   * <p> Gets the Nickname of the associated Mortgage object </p>
   * @return the Nickname of this Mortgage object.
   */
  public String getNickname() {
    return Nickname;
  }

  /**
   * <p> Sets the Nickname of the associated Mortgage object </p>
   * @param Nickname the nickname of the mortgage,
   * throws IllegalArgumentException if Nickname under 3 characters or longer than 255 characters
   */
  public void setNickname(String Nickname) {
    Nickname = Nickname.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Nickname.length()<4){
      throw new IllegalArgumentException("Nickname is too short.");
    }
    if(Nickname.length()>255){
      throw new IllegalArgumentException("Nickname is too long.");
    }
    this.Nickname = Nickname;
  }

  /**
   * <p> Gets the Present_Value of the associated Mortgage object </p>
   * @return the Present_Value of this Mortgage object.
   */
  public Double getPresent_Value() {
    return Present_Value;
  }

  /**
   * <p> Sets the Present_Value of the associated Mortgage object </p>
   * @param Present_Value the present_value of the mortgage,
   * throws IllegalArgumentException if Present_Value is outside of a logical range
   */
  public void setPresent_Value(Double Present_Value) {
    if (Present_Value<0||Present_Value>10000000){
      throw new IllegalArgumentException("Present_Value Can Not Be Negative");
    }
    this.Present_Value = Present_Value;
  }

  /**
   * <p> Gets the Future_Value of the associated Mortgage object </p>
   * @return the Future_Value of this Mortgage object.
   */
  public Double getFuture_Value() {
    return Future_Value;
  }

  /**
   * <p> Sets the Future_Value of the associated Mortgage object </p>
   * @param Future_Value the future_value of the mortgage,
   * throws IllegalArgumentException if Future_Value is outside of a logical range
   */
  public void setFuture_Value(Double Future_Value) {
    if (Future_Value<0||Future_Value>1000000){
      throw new IllegalArgumentException("Future_Value Can Not Be Negative");
    }
    this.Future_Value = Future_Value;
  }

  /**
   * <p> Gets the Interest_Rate of the associated Mortgage object </p>
   * @return the Interest_Rate of this Mortgage object.
   */
  public Double getInterest_Rate() {
    return Interest_Rate;
  }

  /**
   * <p> Sets the Interest_Rate of the associated Mortgage object </p>
   * @param Interest_Rate the interest_rate of the mortgage,
   * throws IllegalArgumentException if Interest_Rate is outside of a logical range
   */
  public void setInterest_Rate(Double Interest_Rate) {
    if (Interest_Rate<0||Interest_Rate>10000){
      throw new IllegalArgumentException("Interest_Rate Can Not Be Negative");
    }
    this.Interest_Rate = Interest_Rate;
  }

  /**
   * <p> Gets the Monthly_Payment of the associated Mortgage object </p>
   * @return the Monthly_Payment of this Mortgage object.
   */
  public Double getMonthly_Payment() {
    return Monthly_Payment;
  }

  /**
   * <p> Sets the Monthly_Payment of the associated Mortgage object </p>
   * @param Monthly_Payment the monthly_payment of the mortgage,
   * throws IllegalArgumentException if Monthly_Payment is outside of a logical range
   */
  public void setMonthly_Payment(Double Monthly_Payment) {
    if (Monthly_Payment<0||Monthly_Payment>10000){
      throw new IllegalArgumentException("Monthly_Payment Can Not Be Negative");
    }
    this.Monthly_Payment = Monthly_Payment;
  }

  /**
   * <p> Gets the Extra_Payment of the associated Mortgage object </p>
   * @return the Extra_Payment of this Mortgage object.
   */
  public Double getExtra_Payment() {
    return Extra_Payment;
  }

  /**
   * <p> Sets the Extra_Payment of the associated Mortgage object </p>
   * @param Extra_Payment the extra_payment of the mortgage,
   * throws IllegalArgumentException if Extra_Payment is outside of a logical range
   */
  public void setExtra_Payment(Double Extra_Payment) {
    if (Extra_Payment<0||Extra_Payment>10000){
      throw new IllegalArgumentException("Extra_Payment Can Not Be Negative");
    }
    this.Extra_Payment = Extra_Payment;
  }

  /**
   * <p> Gets the Remaining_Term of the associated Mortgage object </p>
   * @return the Remaining_Term of this Mortgage object.
   */
  public Integer getRemaining_Term() {
    return Remaining_Term;
  }

  /**
   * <p> Sets the Remaining_Term of the associated Mortgage object </p>
   * @param Remaining_Term the remaining_term of the mortgage,
   * throws IllegalArgumentException if Remaining_Term is negative or greater than 10,000
   */
  public void setRemaining_Term(Integer Remaining_Term) {
    if (Remaining_Term<0||Remaining_Term>10000){
      throw new IllegalArgumentException("Remaining_Term Can Not Be Negative");
    }
    this.Remaining_Term = Remaining_Term;
  }
  public boolean validate(){
    double desired_balance = this.Future_Value;
    double first= Present_Value*(Math.pow(1+Interest_Rate/1200,this.Remaining_Term));
    double second =(Monthly_Payment+Extra_Payment)*(Math.pow(1+Interest_Rate/1200,this.Remaining_Term)-1) /(Interest_Rate/1200);
    double calculated_balance = first - second;
    return (Math.abs(desired_balance-calculated_balance)<.05);
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
    if (this.Nickname.compareTo(o.Nickname)<0){
      return -1;
    }
    else if(this.Nickname.compareTo(o.Nickname) > 0){
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

