package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction_Comment implements Comparable<Transaction_Comment> {
  private Integer User_ID;
  private String Transaction_ID;
  private Integer Transaction_Comment_ID;
  private String Content;
  private Date Post_Date;

  public Transaction_Comment(){}

  public Transaction_Comment(Integer User_ID, String Transaction_ID, Integer Transaction_Comment_ID, String Content, Date Post_Date) {

    this.User_ID = User_ID;
    this.Transaction_ID = Transaction_ID;
    this.Transaction_Comment_ID = Transaction_Comment_ID;
    this.Content = Content;
    this.Post_Date = Post_Date;
  }

  public Transaction_Comment(Integer User_ID, String Transaction_ID, Integer Transaction_Comment_ID) {

    this.User_ID = User_ID;
    this.Transaction_ID = Transaction_ID;
    this.Transaction_Comment_ID = Transaction_Comment_ID;
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
  public String getTransaction_ID() {
    return Transaction_ID;
  }
  public void setTransaction_ID(String Transaction_ID) {
    if (Transaction_ID.length() != 36){
      throw new IllegalArgumentException("Transaction_ID invalid");
    }
    this.Transaction_ID = Transaction_ID;
  }
  public Integer getTransaction_Comment_ID() {
    return Transaction_Comment_ID;
  }
  public void setTransaction_Comment_ID(Integer Transaction_Comment_ID) {
    if (Transaction_Comment_ID<0||Transaction_Comment_ID>10000){
      throw new IllegalArgumentException("Transaction_Comment_ID Can Not Be Negative");
    }
    this.Transaction_Comment_ID = Transaction_Comment_ID;
  }
  public String getContent() {
    return Content;
  }
  public void setContent(String Content) {
    Content = Content.replaceAll("[^A-Za-z0-9 - ]","");
    if(Content.length()<4){
      throw new IllegalArgumentException("Content is too short.");
    }
    if(Content.length()>1000){
      throw new IllegalArgumentException("Content is too long.");
    }
    this.Content = Content;
  }
  public Date getPost_Date() {
    return Post_Date;
  }
  public void setPost_Date(Date Post_Date)throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (Post_Date.compareTo(_minDate)<0){
      throw new IllegalArgumentException("Post_Date Can Not Be Before 1991");
    }
    if (Post_Date.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("Post_Date Can Not Be after 2100");
    }
    this.Post_Date = Post_Date;
  }
  @Override
  public int compareTo(@NotNull Transaction_Comment o) {
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Transaction_ID.compareTo(o.Transaction_ID)<0){
      return -1;
    }
    else if(this.Transaction_ID.compareTo(o.Transaction_ID) > 0){
      return 1;
    }
    if (this.Transaction_Comment_ID.compareTo(o.Transaction_Comment_ID)<0){
      return -1;
    }
    else if(this.Transaction_Comment_ID.compareTo(o.Transaction_Comment_ID) > 0){
      return 1;
    }
    if (this.Content.compareTo(o.Content)<0){
      return -1;
    }
    else if(this.Content.compareTo(o.Content) > 0){
      return 1;
    }
    if (this.Post_Date.compareTo(o.Post_Date)<0){
      return -1;
    }
    else if(this.Post_Date.compareTo(o.Post_Date) > 0){
      return 1;
    }
    return 0;
  }

}
