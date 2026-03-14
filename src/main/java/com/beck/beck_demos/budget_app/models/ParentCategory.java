package com.beck.beck_demos.budget_app.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

public class ParentCategory implements Comparable<ParentCategory> {
  private String parent_category_id;
  private String super_category_name;
  private String user_id;
  private String color_id;
  private String transaction_type;

  public ParentCategory(){}

  public ParentCategory(String parent_category_id, String super_category_name, String user_id, String color_id, String transaction_type) {

    this.parent_category_id = parent_category_id;
    this.super_category_name = super_category_name;
    this.user_id = user_id;
    this.color_id = color_id;
    this.transaction_type = transaction_type;
  }


  public ParentCategory(String parent_category_id) {

    this.parent_category_id = parent_category_id;
  }

  /**
   * <p> Gets the parent_category_id of the associated parent_category object </p>
   * @return the parent_category_id of this parent_category object.
   */
  public String getparent_category_id() {
    return parent_category_id;
  }

  /**
   * <p> Sets the parent_category_id of the associated parent_category object </p>
   * @param parent_category_id the parent_category_id of the parent_category,
   * throws IllegalArgumentException if parent_category_id under 3 characters or longer than 36 characters
   */
  public void setparent_category_id(String parent_category_id) {

    if(parent_category_id.length()<36){
      throw new IllegalArgumentException("parent_category_id is too short.");
    }
    if(parent_category_id.length()>36){
      throw new IllegalArgumentException("parent_category_id is too long.");
    }
    this.parent_category_id = parent_category_id;
  }

  /**
   * <p> Gets the super_category_name of the associated parent_category object </p>
   * @return the super_category_name of this parent_category object.
   */
  public String getsuper_category_name() {
    return super_category_name;
  }

  /**
   * <p> Sets the super_category_name of the associated parent_category object </p>
   * @param super_category_name the super_category_name of the parent_category,
   * throws IllegalArgumentException if super_category_name under 3 characters or longer than 100 characters
   */
  public void setsuper_category_name(String super_category_name) {
    super_category_name = super_category_name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(super_category_name.length()<4){
      throw new IllegalArgumentException("super_category_name is too short.");
    }
    if(super_category_name.length()>100){
      throw new IllegalArgumentException("super_category_name is too long.");
    }
    this.super_category_name = super_category_name;
  }

  /**
   * <p> Gets the user_id of the associated parent_category object </p>
   * @return the user_id of this parent_category object.
   */
  public String getuser_id() {
    return user_id;
  }

  /**
   * <p> Sets the user_id of the associated parent_category object </p>
   * @param user_id the user_id of the parent_category,
   * throws IllegalArgumentException if user_id under 3 characters or longer than 36 characters
   */
  public void setuser_id(String user_id) {

    if(user_id.length()<36){
      throw new IllegalArgumentException("user_id is too short.");
    }
    if(user_id.length()>36){
      throw new IllegalArgumentException("user_id is too long.");
    }
    this.user_id = user_id;
  }

  /**
   * <p> Gets the color_id of the associated parent_category object </p>
   * @return the color_id of this parent_category object.
   */
  public String getcolor_id() {
    return color_id;
  }

  /**
   * <p> Sets the color_id of the associated parent_category object </p>
   * @param color_id the color_id of the parent_category,
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

  /**
   * <p> Gets the transaction_type of the associated parent_category object </p>
   * @return the transaction_type of this parent_category object.
   */
  public String gettransaction_type() {
    return transaction_type;
  }

  /**
   * <p> Sets the transaction_type of the associated parent_category object </p>
   * @param transaction_type the transaction_type of the parent_category,
   * throws IllegalArgumentException if transaction_type under 3 characters or longer than 20 characters
   */
  public void setTransaction_type(String transaction_type) {
    if (transaction_type == null) {
      throw new IllegalArgumentException("Transaction type cannot be null.");
    }

    // 1. Normalize the input (remove whitespace and make lowercase)
    String cleanType = transaction_type.trim().toLowerCase();

    // 2. Validate against your specific keywords
    if (cleanType.equals("income") ||
        cleanType.equals("expense") ||
        cleanType.equals("investment")) {

      this.transaction_type = cleanType;
    } else {
      throw new IllegalArgumentException("Invalid transaction type. Must be 'income', 'expense', or 'investment'.");
    }
  }
  @Override
  public int compareTo(@NotNull ParentCategory o) {
    if (this.parent_category_id.compareTo(o.parent_category_id)<0){
      return -1;
    }
    else if(this.parent_category_id.compareTo(o.parent_category_id) > 0){
      return 1;
    }
    if (this.super_category_name.compareTo(o.super_category_name)<0){
      return -1;
    }
    else if(this.super_category_name.compareTo(o.super_category_name) > 0){
      return 1;
    }
    if (this.user_id.compareTo(o.user_id)<0){
      return -1;
    }
    else if(this.user_id.compareTo(o.user_id) > 0){
      return 1;
    }
    if (this.color_id.compareTo(o.color_id)<0){
      return -1;
    }
    else if(this.color_id.compareTo(o.color_id) > 0){
      return 1;
    }
    if (this.parent_category_id.compareTo(o.color_id)<0){
      return -1;
    }
    else if(this.parent_category_id.compareTo(o.color_id) > 0){
      return 1;
    }
    return 0;
  }

}

