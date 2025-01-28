package com.beck.beck_demos.budget_app.models;

import java.util.Date;
import org.jetbrains.annotations.NotNull;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 * @ updated 1/22/2025 to allow accounts to be foreign keyed
 */


public class Transaction implements Comparable<Transaction> {
  private Integer Transaction_ID;
  private Integer User_ID;
  private String Category_ID;
  private String Bank_Account_ID;
  private Date Post_Date;
  private Integer Check_No;
  private String Description;
  private Double Amount;
  private String Type;
  private String Status;

  public Transaction(){}

  public Transaction(Integer Transaction_ID, Integer User_ID, String Category_ID, String Bank_Account_ID, Date Post_Date, Integer Check_No, String Description, Double Amount, String Type, String Status) {

    this.Transaction_ID = Transaction_ID;
    this.User_ID = User_ID;
    this.Category_ID = Category_ID;
    this.Bank_Account_ID = Bank_Account_ID;
    this.Post_Date = Post_Date;
    this.Check_No = Check_No;
    this.Description = Description;
    this.Amount = Amount;
    this.Type = Type;
    this.Status = Status;
  }

  public Transaction(Integer Transaction_ID) {

    this.Transaction_ID = Transaction_ID;
  }
  public Integer getTransaction_ID() {
    return Transaction_ID;
  }
  public void setTransaction_ID(Integer Transaction_ID) {
    if (Transaction_ID<0||Transaction_ID>10000000){
      throw new IllegalArgumentException("Transaction_ID Can Not Be Negative");
    }
    this.Transaction_ID = Transaction_ID;
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
  public String getCategory_ID() {
    return Category_ID;
  }
  public void setCategory_ID(String Category_ID) {
    Category_ID = Category_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Category_ID.length()<2){
      throw new IllegalArgumentException("Category_ID is too short.");
    }
    if(Category_ID.length()>100){
      throw new IllegalArgumentException("Category_ID is too long.");
    }
    this.Category_ID = Category_ID;
  }
  public String getBank_Account_ID() {
    return Bank_Account_ID;
  }
  public void setBank_Account_ID(String Bank_Account_ID) {
    Bank_Account_ID = Bank_Account_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Bank_Account_ID.length()<4){
      throw new IllegalArgumentException("Bank_Account_ID is too short.");
    }
    if(Bank_Account_ID.length()>100){
      throw new IllegalArgumentException("Bank_Account_ID is too long.");
    }
    this.Bank_Account_ID = Bank_Account_ID;
  }
  public Date getPost_Date() {
    return Post_Date;
  }
  public void setPost_Date(Date Post_Date) {
    this.Post_Date = Post_Date;
  }
  public Integer getCheck_No() {
    return Check_No;
  }
  public void setCheck_No(Integer Check_No) {
    if (Check_No<0||Check_No>10000){
      throw new IllegalArgumentException("Check_No Can Not Be Negative");
    }
    this.Check_No = Check_No;
  }
  public String getDescription() {
    return Description;
  }
  public void setDescription(String Description) {
    Description = Description.replaceAll("[^A-Za-z0-9 - ]","");
    if(Description.length()<4){
      throw new IllegalArgumentException("Description is too short.");
    }
    if(Description.length()>255){
      throw new IllegalArgumentException("Description is too long.");
    }
    this.Description = Description;
  }
  public Double getAmount() {
    return Amount;
  }
  public void setAmount(Double Amount) {
    if (Amount<0||Amount>10000){
      throw new IllegalArgumentException("Amount Can Not Be Negative");
    }
    this.Amount = Amount;
  }
  public String getType() {
    return Type;
  }
  public void setType(String Type) {
    Type = Type.replaceAll("[^A-Za-z0-9 - ]","");
    if(Type.length()<4){
      throw new IllegalArgumentException("Type is too short.");
    }
    if(Type.length()>20){
      throw new IllegalArgumentException("Type is too long.");
    }
    this.Type = Type;
  }
  public String getStatus() {
    return Status;
  }
  public void setStatus(String Status) {
    Status = Status.replaceAll("[^A-Za-z0-9 - ]","");
    if(Status.length()<4){
      throw new IllegalArgumentException("Status is too short.");
    }
    if(Status.length()>20){
      throw new IllegalArgumentException("Status is too long.");
    }
    this.Status = Status;
  }
  @Override
  public int compareTo(@NotNull Transaction o) {
    if (this.Transaction_ID.compareTo(o.Transaction_ID)<0){
      return -1;
    }
    else if(this.Transaction_ID.compareTo(o.Transaction_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Category_ID.compareTo(o.Category_ID)<0){
      return -1;
    }
    else if(this.Category_ID.compareTo(o.Category_ID) > 0){
      return 1;
    }
    if (this.Bank_Account_ID.compareTo(o.Bank_Account_ID)<0){
      return -1;
    }
    else if(this.Bank_Account_ID.compareTo(o.Bank_Account_ID) > 0){
      return 1;
    }
    if (this.Post_Date.compareTo(o.Post_Date)<0){
      return -1;
    }
    else if(this.Post_Date.compareTo(o.Post_Date) > 0){
      return 1;
    }
    if (this.Check_No.compareTo(o.Check_No)<0){
      return -1;
    }
    else if(this.Check_No.compareTo(o.Check_No) > 0){
      return 1;
    }
    if (this.Description.compareTo(o.Description)<0){
      return -1;
    }
    else if(this.Description.compareTo(o.Description) > 0){
      return 1;
    }
    if (this.Amount.compareTo(o.Amount)<0){
      return -1;
    }
    else if(this.Amount.compareTo(o.Amount) > 0){
      return 1;
    }
    if (this.Type.compareTo(o.Type)<0){
      return -1;
    }
    else if(this.Type.compareTo(o.Type) > 0){
      return 1;
    }
    if (this.Status.compareTo(o.Status)<0){
      return -1;
    }
    else if(this.Status.compareTo(o.Status) > 0){
      return 1;
    }
    return 0;
  }

}

