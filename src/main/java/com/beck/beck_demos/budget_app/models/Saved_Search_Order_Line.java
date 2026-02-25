package com.beck.beck_demos.budget_app.models;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

public class Saved_Search_Order_Line implements Comparable<Saved_Search_Order_Line> {
  private Integer Saved_Search_Order_ID;
  private Integer Line_No;
  private String Category_ID;
  private String User_ID;
  private String Search_Phrase;
  private boolean Is_Active;

  public Saved_Search_Order_Line(){}

  public Saved_Search_Order_Line(Integer Saved_Search_Order_ID, Integer Line_No, String Category_ID, String User_ID, String Search_Phrase, boolean Is_Active) {

    this.Saved_Search_Order_ID = Saved_Search_Order_ID;
    this.Line_No = Line_No;
    this.Category_ID = Category_ID;
    this.User_ID = User_ID;
    this.Search_Phrase = Search_Phrase;
    this.Is_Active = Is_Active;
  }

  public Saved_Search_Order_Line(Integer Saved_Search_Order_ID, Integer Line_No) {

    this.Saved_Search_Order_ID = Saved_Search_Order_ID;
    this.Line_No = Line_No;
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
  public Integer getLine_No() {
    return Line_No;
  }
  public void setLine_No(Integer Line_No) {
    if (Line_No<0||Line_No>10000){
      throw new IllegalArgumentException("Line_No Can Not Be Negative");
    }
    this.Line_No = Line_No;
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
  public String getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(String User_ID) {
    if (User_ID.length()<36||User_ID.length()>36){
      throw new IllegalArgumentException("User_ID Can Not Be Negative");
    }
    this.User_ID = User_ID;
  }
  public String getSearch_Phrase() {
    return Search_Phrase;
  }
  public void setSearch_Phrase(String Search_Phrase) {
    Search_Phrase = Search_Phrase.replaceAll("[^A-Za-z0-9 - ]","");
    if(Search_Phrase.length()<4){
      throw new IllegalArgumentException("Search_Phrase is too short.");
    }
    if(Search_Phrase.length()>100){
      throw new IllegalArgumentException("Search_Phrase is too long.");
    }
    this.Search_Phrase = Search_Phrase;
  }
  public boolean getIs_Active() {
    return Is_Active;
  }
  public void setIs_Active(boolean Is_Active) {
    this.Is_Active = Is_Active;
  }
  @Override
  public int compareTo(@NotNull Saved_Search_Order_Line o) {
    if (this.Saved_Search_Order_ID.compareTo(o.Saved_Search_Order_ID)<0){
      return -1;
    }
    else if(this.Saved_Search_Order_ID.compareTo(o.Saved_Search_Order_ID) > 0){
      return 1;
    }
    if (this.Line_No.compareTo(o.Line_No)<0){
      return -1;
    }
    else if(this.Line_No.compareTo(o.Line_No) > 0){
      return 1;
    }
    if (this.Category_ID.compareTo(o.Category_ID)<0){
      return -1;
    }
    else if(this.Category_ID.compareTo(o.Category_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.Search_Phrase.compareTo(o.Search_Phrase)<0){
      return -1;
    }
    else if(this.Search_Phrase.compareTo(o.Search_Phrase) > 0){
      return 1;
    }
    if (!this.Is_Active&&o.Is_Active){
      return -1;
    }
    if (this.Is_Active&&!o.Is_Active){
      return 1;
    }
    return 0;
  }

}

