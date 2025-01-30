package com.beck.beck_demos.budget_app.models;

import java.util.Date;
import org.jetbrains.annotations.NotNull;

import static java.lang.Character.isAlphabetic;
import static java.lang.Character.isDigit;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Bank_Account implements Comparable<Bank_Account> {
  private String Bank_Account_ID;
  private Integer User_ID;
  private String Account_Nickname;
  private Double Balance;
  private Date Balance_Date;

  public Bank_Account(){}

  public Bank_Account(String Bank_Account_ID, Integer User_ID, String Account_Nickname, Double Balance, Date Balance_Date) {

    this.Bank_Account_ID = Bank_Account_ID;
    this.User_ID = User_ID;
    this.Account_Nickname = Account_Nickname;
    this.Balance = Balance;
    this.Balance_Date = Balance_Date;
  }

  public Bank_Account(String Bank_Account_ID) {

    setBank_Account_ID(Bank_Account_ID);
  }
  public String getBank_Account_ID() {
    return Bank_Account_ID;
  }
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
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    if (User_ID<0||User_ID>10000){
      throw new IllegalArgumentException("User_ID Can Not Be Negative");
    }

    this.User_ID = User_ID;
  }
  public String getAccount_Nickname() {
    return Account_Nickname;
  }
  public void setAccount_Nickname(String Account_Nickname) {
    Account_Nickname = Account_Nickname.replaceAll("[^A-Za-z0-9 - ]","");
    if(Account_Nickname.length()<4){
      throw new IllegalArgumentException("Account_Nickname is too short.");
    }
    if(Account_Nickname.length()>100){
      throw new IllegalArgumentException("Account_Nickname is too long.");
    }
    this.Account_Nickname = Account_Nickname;
  }
  public Double getBalance() {
    return Balance;
  }
  public void setBalance(Double Balance) {
    if (Balance<0||Balance>10000){
      throw new IllegalArgumentException("Balance Can Not Be Negative");
    }
    this.Balance = Balance;
  }
  public Date getBalance_Date() {
    return Balance_Date;
  }
  public void setBalance_Date(Date Balance_Date) {
    this.Balance_Date = Balance_Date;
  }
  @Override
  public int compareTo(@NotNull Bank_Account o) {
    if (this.Bank_Account_ID.compareTo(o.Bank_Account_ID)<0){
      return -1;
    }
    else if(this.Bank_Account_ID.compareTo(o.Bank_Account_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Account_Nickname.compareTo(o.Account_Nickname)<0){
      return -1;
    }
    else if(this.Account_Nickname.compareTo(o.Account_Nickname) > 0){
      return 1;
    }
    if (this.Balance.compareTo(o.Balance)<0){
      return -1;
    }
    else if(this.Balance.compareTo(o.Balance) > 0){
      return 1;
    }
    if (this.Balance_Date.compareTo(o.Balance_Date)<0){
      return -1;
    }
    else if(this.Balance_Date.compareTo(o.Balance_Date) > 0){
      return 1;
    }
    return 0;
  }

}

