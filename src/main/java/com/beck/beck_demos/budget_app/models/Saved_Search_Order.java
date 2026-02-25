package com.beck.beck_demos.budget_app.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Saved_Search_Order implements Comparable<Saved_Search_Order> {
  private Integer Saved_Search_Order_ID;
  private String Owned_User;
  private String Nickname;
  private String Description;
  private Date Last_Used;
  private Date Last_Updated;
  private Integer Times_Ran;

  public Saved_Search_Order(){}

  public Saved_Search_Order(Integer Saved_Search_Order_ID, String Owned_User, String Nickname, String Description, Date Last_Used, Date Last_Updated, Integer Times_Ran) {

    this.Saved_Search_Order_ID = Saved_Search_Order_ID;
    this.Owned_User = Owned_User;
    this.Nickname = Nickname;
    this.Description = Description;
    this.Last_Used = Last_Used;
    this.Last_Updated = Last_Updated;
    this.Times_Ran = Times_Ran;
  }

  public Saved_Search_Order(Integer Saved_Search_Order_ID) {

    this.Saved_Search_Order_ID = Saved_Search_Order_ID;
  }
  public Integer getSaved_Search_Order_ID() {
    return Saved_Search_Order_ID;
  }
  public void setSaved_Search_Order_ID(Integer Saved_Search_Order_ID) {
    if (Saved_Search_Order_ID<0||Saved_Search_Order_ID>10000){
      throw new IllegalArgumentException("Saved_Search_Order_ID Can Not Be Negative");
    }
    this.Saved_Search_Order_ID = Saved_Search_Order_ID;
  }
  public String getOwned_User() {
    return Owned_User;
  }
  public void setOwned_User(String Owned_User) {
    if (Owned_User.length()<36||Owned_User.length()>36){
      throw new IllegalArgumentException("Owned_User Can Not Be Negative");
    }
    this.Owned_User = Owned_User;
  }
  public String getNickname() {
    return Nickname;
  }
  public void setNickname(String Nickname) {
    Nickname = Nickname.replaceAll("[^A-Za-z0-9 - ]","");
    if(Nickname.length()<4){
      throw new IllegalArgumentException("Nickname is too short.");
    }
    if(Nickname.length()>100){
      throw new IllegalArgumentException("Nickname is too long.");
    }
    this.Nickname = Nickname;
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
  public Date getLast_Used() {
    return Last_Used;
  }
  public void setLast_Used(Date Last_Used) throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (Last_Used.compareTo(_minDate)<0){
      throw new IllegalArgumentException("Last_Used Can Not Be Before 1991");
    }
    if (Last_Used.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("Last_Used Can Not Be after 2100");
    }
    this.Last_Used = Last_Used;
  }

  public Date getLast_Updated() {
    return Last_Updated;
  }
  public void setLast_Updated(Date Last_Updated) throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (Last_Updated.compareTo(_minDate)<0){
      throw new IllegalArgumentException("Last_Updated Can Not Be Before 1991");
    }
    if (Last_Updated.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("Last_Updated Can Not Be after 2100");
    }
    this.Last_Updated = Last_Updated;
  }
  public Integer getTimes_Ran() {
    return Times_Ran;
  }
  public void setTimes_Ran(Integer Times_Ran) {
    if (Times_Ran<0||Times_Ran>10000){
      throw new IllegalArgumentException("Times_Ran Can Not Be Negative");
    }
    this.Times_Ran = Times_Ran;
  }
  @Override
  public int compareTo(@NotNull Saved_Search_Order o) {
    if (this.Saved_Search_Order_ID.compareTo(o.Saved_Search_Order_ID)<0){
      return -1;
    }
    else if(this.Saved_Search_Order_ID.compareTo(o.Saved_Search_Order_ID) > 0){
      return 1;
    }
    if (this.Owned_User.compareTo(o.Owned_User)<0){
      return -1;
    }
    else if(this.Owned_User.compareTo(o.Owned_User) > 0){
      return 1;
    }
    if (this.Nickname.compareTo(o.Nickname)<0){
      return -1;
    }
    else if(this.Nickname.compareTo(o.Nickname) > 0){
      return 1;
    }
    if (this.Description.compareTo(o.Description)<0){
      return -1;
    }
    else if(this.Description.compareTo(o.Description) > 0){
      return 1;
    }
    if (this.Last_Used.compareTo(o.Last_Used)<0){
      return -1;
    }
    else if(this.Last_Used.compareTo(o.Last_Used) > 0){
      return 1;
    }
    if (this.Last_Updated.compareTo(o.Last_Updated)<0){
      return -1;
    }
    else if(this.Last_Updated.compareTo(o.Last_Updated) > 0){
      return 1;
    }
    if (this.Times_Ran.compareTo(o.Times_Ran)<0){
      return -1;
    }
    else if(this.Times_Ran.compareTo(o.Times_Ran) > 0){
      return 1;
    }
    return 0;
  }

}

