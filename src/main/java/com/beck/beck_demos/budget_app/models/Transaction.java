package com.beck.beck_demos.budget_app.models;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 22/7/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines Transaction Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>

import java.sql.Date;

public class Transaction {
  private Integer Transaction_ID;
  private Integer User_ID;
  private String Category_ID;
  private String Account_Num;
  private Date Post_Date;
  private Integer Check_No;
  private String Description;
  private Double Amount;
  private String Type;
  private String Status;

  public Transaction(){}

  public Transaction(Integer Transaction_ID, Integer User_ID,String Category_ID ,String Account_Num, Date Post_Date, Integer Check_No, String Description, Double Amount, String Type, String Status) {

    this.Transaction_ID = Transaction_ID;
    this.User_ID = User_ID;
    this.Category_ID=Category_ID;
    this.Account_Num = Account_Num;
    this.Post_Date = Post_Date;
    this.Check_No = Check_No;
    this.Description = Description;
    this.Amount = Amount;
    this.Type = Type;
    this.Status = Status;
  }

  public Integer getTransaction_ID() {
    return Transaction_ID;
  }
  public void setTransaction_ID(Integer Transaction_ID) {
    if(Transaction_ID<0){
      throw new IllegalArgumentException("Transaction_ID is too short.");
    }
    if(Transaction_ID>1000000){
      throw new IllegalArgumentException("Transaction_ID is too long.");
    }
    this.Transaction_ID = Transaction_ID;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public String getCategory_ID() {
    return Category_ID;
  }
  public void setCategory_ID(String Category_ID) {
    Category_ID = Category_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Category_ID.length()<3){
      throw new IllegalArgumentException("Category_ID is too short.");
    }
    if(Category_ID.length()>100){
      throw new IllegalArgumentException("Category_ID is too long.");
    }
    this.Category_ID = Category_ID;
  }

  public String getAccount_Num() {
    return Account_Num;
  }
  public void setAccount_Num(String Account_Num) {
    Account_Num = Account_Num.replaceAll("[^A-Za-z0-9 - ]","");
    if(Account_Num.length()<4){
      throw new IllegalArgumentException("Account_Num is too short.");
    }
    if(Account_Num.length()>100){
      throw new IllegalArgumentException("Account_Num is too long.");
    }
    this.Account_Num = Account_Num;
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

}


