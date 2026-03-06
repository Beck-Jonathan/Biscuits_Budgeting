package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;


/// Since 12/5/2024
/// Jonathan Beck
public class Category implements Comparable<Category> {
  private String Category_ID;
  private String Category_Name;
  private String User_ID;
  private String color_id;

  public Category(){}

  public Category(String Category_ID,String User_ID,  String Name,String Color_ID) {

    setCategory_ID(Category_ID);
    setCategory_Name(Name);
    setUser_ID(User_ID);
    setcolor_id(Color_ID);
  }

  public Category(String Category_ID,String Name) {

    setCategory_ID(Category_ID);

    setCategory_Name(Name);

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
    if(Category_ID.length()<36){
      throw new IllegalArgumentException("category_id is too short.");
    }
    if(Category_ID.length()>36){
      throw new IllegalArgumentException("category_id is too long.");
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



  /**
   * <p> Gets the color_id of the associated category object </p>
   * @return the color_id of this category object.
   */
  public String getcolor_id() {
    return color_id;
  }

  /**
   * <p> Gets the category_name of the associated category object </p>
   * @return the category_name of this category object.
   */
  public String getCategory_Name() {
    return Category_Name;
  }

  /**
   * <p> Sets the category_name of the associated category object </p>
   * @param category_name the category_name of the category,
   * throws IllegalArgumentException if category_name under 3 characters or longer than 100 characters
   */
  public void setCategory_Name(String category_name) {
    category_name = category_name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(category_name.length()<4){
      throw new IllegalArgumentException("category_name is too short.");
    }
    if(category_name.length()>100){
      throw new IllegalArgumentException("category_name is too long.");
    }
    this.Category_Name = category_name;
  }

  /**
   * <p> Sets the color_id of the associated category object </p>
   * @param color_id the color_id of the category,
   * throws IllegalArgumentException if color_id under 3 characters or longer than 7 characters
   */
  public void setcolor_id(String color_id) {
    if (color_id == null) {
      throw new IllegalArgumentException("color_id cannot be null.");
    }



    // 3. Validate format (must be 0-9, a-f, or A-F)
    if (!color_id.matches("^#(?:[0-9a-fA-F]{3}){1,2}$")) {
      throw new IllegalArgumentException("color_id contains invalid hexadecimal characters.");
    }

    // 4. Store consistently (usually uppercase for DB consistency)
    this.color_id = color_id.toUpperCase();
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
    if (this.getCategory_Name().compareTo(o.getCategory_Name())<0){
      return -1;
    }
    else if(this.getCategory_Name().compareTo(o.getCategory_Name()) > 0){
      return 1;
    }
    if (this.color_id.compareTo(o.color_id)<0){
      return -1;
    }
    else if(this.color_id.compareTo(o.color_id) > 0){
      return 1;
    }
    return 0;
  }

}