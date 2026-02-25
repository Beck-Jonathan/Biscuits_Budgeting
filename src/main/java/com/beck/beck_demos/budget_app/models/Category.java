package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;


/// Since 12/5/2024
/// Jonathan Beck
public class Category implements Comparable<Category> {
  private String Category_ID;
  private String User_ID;

  public Category(){}

  public Category(String Category_ID, String User_ID) {

    setCategory_ID(Category_ID);
    setUser_ID(User_ID);
  }

  /**
   * <p> Gets the Category_ID of the associated Category object </p>
   * @return the Category_ID of this Category object.
   */
  public String getCategory_ID() {
    return Category_ID;
  }
  /**
   * <p> Sets the Category_ID of the associated Category object </p>
   *@param Category_ID the category_id of the category,
   * throws IllegalArgumentException if Category_ID under 3 characters or longer than 100 characters
   */
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
  /**
   * <p> Gets the User_ID of the associated Category object </p>
   * @return the User_ID of this Category object.
   */
  public String getUser_ID() {
    return User_ID;
  }

  /**
   * <p> Sets the User_ID of the associated Category object </p>
   *@param User_ID the user_id of the category,
   * throws IllegalArgumentException if User_ID is negative or greater than 10,000
   */
  public void setUser_ID(String User_ID) {
    if (User_ID.length() < 36 || User_ID.length() > 36) {
      throw new IllegalArgumentException("User_ID Can Not Be greater than 10,000");
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