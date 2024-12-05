package com.beck.beck_demos.budget_app.models;

import java.util.Objects;

public class Category {
  private String Category_ID;

  public Category(){}

  public Category(String Category_ID) {

    this.Category_ID = Category_ID;
  }
  public String getCategory_ID() {
    return Category_ID;
  }
  public void setCategory_ID(String Category_ID) {
    if (Category_ID.equals("")){
      this.Category_ID = "";
      return;
    }
    Category_ID = Category_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Category_ID.length()<2){
      throw new IllegalArgumentException("Category_ID is too short.");
    }
    if(Category_ID.length()>100){
      throw new IllegalArgumentException("Category_ID is too long.");
    }
    this.Category_ID = Category_ID;
  }

}
