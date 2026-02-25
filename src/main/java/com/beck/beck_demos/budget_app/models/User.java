package com.beck.beck_demos.budget_app.models;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 24/7/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines User Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>

import com.beck.beck_demos.shared.MyValidators;

import java.util.List;
import java.util.regex.Matcher;
import org.jetbrains.annotations.NotNull;

public class User implements Comparable<User>{
  private String User_ID;
  private String User_Name;
  private char[] User_PW;
  private String Email;
  private List<String> roles;

  public User(){}

  public User(String User_ID, String User_Name, char[] User_PW, String Email) {

    this.User_ID = User_ID;
    this.User_Name = User_Name;
    this.User_PW = User_PW;
    this.Email = Email;
  }
  /**
   * <p> Gets the User_ID of the associated User object </p>
   * @return the User_ID of this User object.
   */
  public String getUser_ID() {
    return User_ID;
  }
  /**
   * <p> Sets the User_ID of the associated User object </p>
   *@param User_ID the user_id of the user,
   * throws IllegalArgumentException if User_ID is negative or greater than 10,000
   */
  public void setUser_ID(String User_ID) {
    if (User_ID.length() < 36 || User_ID.length() > 36) {
      throw new IllegalArgumentException("Invalid user ID");
    }
    this.User_ID = User_ID;
  }
  /**
   * <p> Gets the User_Name of the associated User object </p>
   * @return the User_Name of this User object.
   */
  public String getUser_Name() {
    return User_Name;
  }
  /**
   * <p> Sets the User_Name of the associated User object </p>
   *@param User_Name the user_name of the user,
   * throws IllegalArgumentException if User_Name under 3 characters or longer than 100 characters
   */
  public void setUser_Name(String User_Name) {
    User_Name = User_Name.replaceAll("[^A-Za-z0-9_ - ]","");
    if(User_Name.length()<4){
      throw new IllegalArgumentException("User_Name is too short.");
    }
    if(User_Name.length()>100){
      throw new IllegalArgumentException("User_Name is too long.");
    }
    this.User_Name = User_Name;
  }
  /**
   * <p> Gets the User_PW of the associated User object </p>
   * @return the User_PW of this User object.
   */
  public char[] getUser_PW() {
    return User_PW;
  }
  /**
   * <p> Sets the User_PW of the associated User object </p>
   *@param User_PW the user_pw of the user,
   * throws IllegalArgumentException if User_PW does not match a standard password regex
   */
  public void setUser_PW(char[] User_PW) {
    if (User_PW!=null) {

      String passwordStr = String.valueOf(User_PW);
      Matcher matcher = MyValidators.passwordPattern.matcher(passwordStr);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Password must be 8 characters, with 3 of 4 (lowercase, uppercase, number, symbol)");
      }
    }
    this.User_PW = User_PW;
  }
  /**
   * <p> Gets the Email of the associated User object </p>
   * @return the Email of this User object.
   */
  public String getEmail() {
    return Email;
  }
  /**
   * <p> Sets the Email of the associated User object </p>
   *@param Email the email of the user,
   * throws IllegalArgumentException if Email does not match a standard email regex
   */
  public void setEmail(String Email) {
    if (Email!=null) {


      Matcher matcher = MyValidators.emailPattern.matcher(Email);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Invalid Email");
      }
    }
    else {
      throw new IllegalArgumentException("Invalid Email");
    }
    this.Email = Email;
  }
  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    for (int i =0;i<roles.size();i++){
      String role = roles.get(i);
      role=role.substring(0, 1).toUpperCase() + role.substring(1);
      roles.set(i,role);
    }
    this.roles = roles;
  }


  @Override
  public int compareTo(@NotNull User o) {
    if (this.User_ID.compareTo(o.User_ID)<0){
      return -1;
    }
    else if(this.User_ID.compareTo(o.User_ID) > 0){
      return 1;
    }
    if (this.User_Name.compareTo(o.User_Name)<0){
      return -1;
    }
    else if(this.User_Name.compareTo(o.User_Name) > 0){
      return 1;
    }

    if (this.Email.compareTo(o.Email)<0){
      return -1;
    }
    else if(this.Email.compareTo(o.Email) > 0){
      return 1;
    }
    return 0;
  }

}

