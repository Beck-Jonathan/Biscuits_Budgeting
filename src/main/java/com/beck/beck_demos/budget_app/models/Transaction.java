package com.beck.beck_demos.budget_app.models;

import java.util.Date;
import org.jetbrains.annotations.NotNull;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 * @ updated 1/22/2025 to allow accounts to be foreign keyed
 */


public class Transaction implements Comparable<Transaction> {
  private String Transaction_ID;
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

  public Transaction(String Transaction_ID, Integer User_ID, String Category_ID, String Bank_Account_ID, Date Post_Date, Integer Check_No, String Description, Double Amount, String Type, String Status) {

    this.Transaction_ID = Transaction_ID;
    this.User_ID = User_ID;
    this.Category_ID = Category_ID;
    setBank_Account_ID(Bank_Account_ID);
    this.Post_Date = Post_Date;
    this.Check_No = Check_No;
    this.Description = Description;
    this.Amount = Amount;
    this.Type = Type;
    this.Status = Status;
  }

  public Transaction(String Transaction_ID) {

    this.Transaction_ID = Transaction_ID;
  }
  /**
   * <p> Gets the Transaction_ID of the associated Transaction object </p>
   * @return the Transaction_ID of this Transaction object.
   */
  public String getTransaction_ID() {
    return Transaction_ID;
  }
  /**
   * <p> Sets the Transaction_ID of the associated Transaction object </p>
   *@param Transaction_ID the transaction_id of the transaction,
   * throws IllegalArgumentException if Transaction_ID is negative or greater than 10,000
   */
  public void setTransaction_ID(String Transaction_ID) {
    if (Transaction_ID.length() != 36){
      throw new IllegalArgumentException("Transaction_ID invalid");
    }
    this.Transaction_ID = Transaction_ID;
  }
  /**
   * <p> Gets the User_ID of the associated Transaction object </p>
   * @return the User_ID of this Transaction object.
   */
  public Integer getUser_ID() {
    return User_ID;
  }
  /**
   * <p> Sets the User_ID of the associated Transaction object </p>
   *@param User_ID the user_id of the transaction,
   * throws IllegalArgumentException if User_ID is negative or greater than 10,000
   */
  public void setUser_ID(Integer User_ID) {
    if (User_ID<0||User_ID>10000){
      throw new IllegalArgumentException("User_ID Can Not Be Negative");
    }
    this.User_ID = User_ID;
  }
  /**
   * <p> Gets the Category_ID of the associated Transaction object </p>
   * @return the Category_ID of this Transaction object.
   */
  public String getCategory_ID() {
    return Category_ID;
  }
  /**
   * <p> Sets the Category_ID of the associated Transaction object </p>
   *@param Category_ID the category_id of the transaction,
   * throws IllegalArgumentException if Category_ID under 2 characters or longer than 100 characters
   */
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
  /**
   * <p> Gets the Bank_Account_ID of the associated Transaction object </p>
   * @return the Bank_Account_ID of this Transaction object.
   */
  public String getBank_Account_ID() {
    return Bank_Account_ID;
  }
  /**
   * <p> Sets the Bank_Account_ID of the associated Transaction object
   * Replaces all but hte last 4 characters with "x"</p>
   *@param Bank_Account_ID the bank_account_id of the transaction,
   * throws IllegalArgumentException if Bank_Account_ID under 3 characters or longer than 100 characters
   */
  public void setBank_Account_ID(String Bank_Account_ID) {
    Bank_Account_ID=Bank_Account_ID.trim();
    Bank_Account_ID = Bank_Account_ID.replaceAll("[^-A-Za-z0-9 - ]","");
    if(Bank_Account_ID.length()<4){
      throw new IllegalArgumentException("Bank_Account_ID is too short.");
    }
    if(Bank_Account_ID.length()>100){
      throw new IllegalArgumentException("Bank_Account_ID is too long.");
    }
    int size = Bank_Account_ID.length();
    char[] account_no = Bank_Account_ID.toCharArray();
    for (int i=0;i<size-4;i++){
      if(isDigit(account_no[i])||isAlphabetic(account_no[i])){
        account_no[i]='x';
      }
    }
    Bank_Account_ID = new String(account_no);
    this.Bank_Account_ID = Bank_Account_ID;
  }
  /**
   * <p> Gets the Post_Date of the associated Transaction object </p>
   * @return the Post_Date of this Transaction object.
   */
  public Date getPost_Date() {
    return Post_Date;
  }
  /**
   * <p> Sets the Post_Date of the associated Transaction object </p>
   *@param Post_Date the post_date of the transaction,
   * throws IllegalArgumentException if Post_Date is outside a logical range
   */
  public void setPost_Date(Date Post_Date) {
    this.Post_Date = Post_Date;
  }
  /**
   * <p> Gets the Check_No of the associated Transaction object </p>
   * @return the Check_No of this Transaction object.
   */
  public Integer getCheck_No() {
    return Check_No;
  }
  /**
   * <p> Sets the Check_No of the associated Transaction object </p>
   *@param Check_No the check_no of the transaction,
   * throws IllegalArgumentException if Check_No is negative or greater than 10,000
   */
  public void setCheck_No(Integer Check_No) {
    if (Check_No<0||Check_No>10000){
      throw new IllegalArgumentException("Check_No Can Not Be Negative");
    }
    this.Check_No = Check_No;
  }

  /**
   * <p> Gets the Description of the associated Transaction object </p>
   * @return the Description of this Transaction object.
   */
  public String getDescription() {
    return Description;
  }
  /**
   * <p> Sets the Description of the associated Transaction object </p>
   *@param Description the description of the transaction,
   * throws IllegalArgumentException if Description under 3 characters or longer than 255 characters
   */
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
  /**
   * <p> Gets the Amount of the associated Transaction object </p>
   * @return the Amount of this Transaction object.
   */
  public Double getAmount() {
    return Amount;
  }
  /**
   * <p> Sets the Amount of the associated Transaction object </p>
   *@param Amount the amount of the transaction,
   * throws IllegalArgumentException if Amount is outside a logical range
   */
  public void setAmount(Double Amount) {
    if (Amount<0||Amount>10000){
      throw new IllegalArgumentException("Amount Can Not Be Negative");
    }
    this.Amount = Amount;
  }
  /**
   * <p> Gets the Type of the associated Transaction object </p>
   * @return the Type of this Transaction object.
   */
  public String getType() {
    return Type;
  }
  /**
   * <p> Sets the Type of the associated Transaction object </p>
   *@param Type the type of the transaction,
   * throws IllegalArgumentException if Type under 3 characters or longer than 20 characters
   */
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
  /**
   * <p> Gets the Status of the associated Transaction object </p>
   * @return the Status of this Transaction object.
   */
  public String getStatus() {
    return Status;
  }
  /**
   * <p> Sets the Status of the associated Transaction object </p>
   *@param Status the status of the transaction,
   * throws IllegalArgumentException if Status under 3 characters or longer than 20 characters
   */
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

