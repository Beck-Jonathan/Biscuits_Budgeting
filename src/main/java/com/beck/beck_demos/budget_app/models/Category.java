package com.beck.beck_demos.budget_app.models;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;


public class Category implements Comparable<Category> {
  private String Category_ID;
  private Integer User_ID;

  public Category(){}

  public Category(String Category_ID, Integer User_ID) {

    this.Category_ID = Category_ID;
    this.User_ID = User_ID;
  }


  public String getCategory_ID() {
    return Category_ID;
  }
  public void setCategory_ID(String Category_ID) {
    Category_ID = Category_ID.replaceAll("[^&A-Za-z0-9 - ]","");
    if(Category_ID.length()<3){
      throw new IllegalArgumentException("Category_ID is too short.");
    }
    if(Category_ID.length()>100){
      throw new IllegalArgumentException("Category_ID is too long.");
    }
    this.Category_ID = Category_ID;
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
  @Override
  public int compareTo(@NotNull Category o) {
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
    return 0;
  }

}