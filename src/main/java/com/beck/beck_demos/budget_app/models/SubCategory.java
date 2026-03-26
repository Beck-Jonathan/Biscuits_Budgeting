package com.beck.beck_demos.budget_app.models;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

/// Since 12/5/2024
/// Jonathan Beck
public class SubCategory implements Comparable<SubCategory> {
  private static final Set<String> ALLOWED_STRATEGIES = Set.of(
      "ALPHA_SPIKE", "REGRESSION", "LVCF", "AVG_STRICT", "INFLATION_ONLY", "ZERO_SUM"
  );
  private String Category_ID;
  private String Category_Name;
  private String parentCategoryId;
  private String projection_strategy_ID;
  private String User_ID;
  private String color_id;

  public SubCategory(){}

  public SubCategory(String Category_ID, String parent_category_id, String projection_strategy_ID, String User_ID, String Name, String Color_ID) {

    setCategory_ID(Category_ID);
    setParentCategoryId(parent_category_id);
    setprojection_strategy_ID(projection_strategy_ID);
    setCategory_Name(Name);
    setUser_ID(User_ID);
    setcolor_id(Color_ID);
  }

  public SubCategory(String Category_ID, String Name) {

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

    if (Category_ID.toLowerCase().contains("total")||Category_ID.toLowerCase().contains("system_out") ||Category_ID.toLowerCase().contains("system_in")) {
      this.Category_ID = Category_ID;
      return;
    }

    if(Category_ID.length()<36){
      throw new IllegalArgumentException("category_id is too short.");
    }
    if(Category_ID.length()>36){
      throw new IllegalArgumentException("category_id is too long.");
    }
    this.Category_ID = Category_ID;
  }

  /**
   * <p> Gets the parent_category_id of the associated sub_category object </p>
   * @return the parent_category_id of this sub_category object.
   */
  public String getParentCategoryId() {
    return parentCategoryId;
  }

  /**
   * <p> Sets the parent_category_id of the associated sub_category object </p>
   * @param parent_category_id the parent_category_id of the sub_category,
   * throws IllegalArgumentException if parent_category_id under 3 characters or longer than 36 characters
   */
  public void setParentCategoryId(String parent_category_id) {

    if (parent_category_id.toLowerCase().contains("system")||parent_category_id.toLowerCase().contains("system_in")||parent_category_id.toLowerCase().contains("system_out")) {
      this.parentCategoryId = parent_category_id;
      return;
    }

    if(parent_category_id.length()<36){
      throw new IllegalArgumentException("parent_category_id is too short.");
    }
    if(parent_category_id.length()>36){
      throw new IllegalArgumentException("parent_category_id is too long.");
    }
    this.parentCategoryId = parent_category_id;
  }

  public String getprojection_strategy_ID() {
    return projection_strategy_ID;
  }

  /**
   * <p> Sets the projection_strategy_ID of the associated sub_category object </p>
   *
   * @param projection_strategy_ID the projection_strategy_id of the sub_category,
   *                               throws IllegalArgumentException if projection_strategy_ID under 3 characters or longer than 20 characters
   */
  public void setprojection_strategy_ID(String projection_strategy_ID) {
    // 1. Sanitize the input
    String sanitized = projection_strategy_ID.replaceAll("[^A-Z_]", "").trim();

    // 2. Block and Default
    if (ALLOWED_STRATEGIES.contains(sanitized)) {
      this.projection_strategy_ID = sanitized;
    } else {
      // Log a warning and fallback to a safe default
      //System.out.println("Warning: Invalid strategy blocked: " + projection_strategy_ID);
      this.projection_strategy_ID = "AVG_STRICT";
    }
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
    // Added '_' and kept the space.
    // Placing the hyphen at the very end ensures it's treated as a literal character.
    category_name = category_name.replaceAll("[^.,!()A-Za-z0-9_ ]", "");

    if(category_name.length() < 3){
      throw new IllegalArgumentException("category_name is too short.");
    }
    if(category_name.length() > 100){
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
  public int compareTo(@NotNull SubCategory o) {
    if (this.Category_ID.compareTo(o.Category_ID)<0){
      return -1;
    }
    else if(this.Category_ID.compareTo(o.Category_ID) > 0){
      return 1;
    }
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0) {
      return 1;
    }
    if (this.projection_strategy_ID.compareTo(o.projection_strategy_ID) < 0) {
      return -1;
    } else if (this.projection_strategy_ID.compareTo(o.projection_strategy_ID) > 0){
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