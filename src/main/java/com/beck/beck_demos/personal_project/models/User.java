package com.beck.beck_demos.personal_project.models;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 3/3/2024
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

import java.util.regex.Matcher;

public class User {
  private Integer User_ID;
  private String User_Name;
  char[] User_PW;
  private Integer Status_ID;
  private String Email;
  private Integer Language_ID;
  private Integer Privilege_ID;

  public User(){}

  public User(Integer User_ID, String User_Name, char[] User_PW, Integer Status_ID, String Email, Integer Language_ID, Integer Privilege_ID) {

    this.User_ID = User_ID;
    this.User_Name = User_Name;
    this.User_PW = User_PW;
    this.Status_ID = Status_ID;
    this.Email = Email;
    this.Language_ID = Language_ID;
    this.Privilege_ID = Privilege_ID;
  }
  public Integer getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(Integer User_ID) {
    this.User_ID = User_ID;
  }
  public String getUser_Name() {
    return User_Name;
  }
  public void setUser_Name(String User_Name) {
    User_Name = User_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(User_Name.length()<4){
      throw new IllegalArgumentException("User Name is too short. It must be greater than 3 characters.");
    }
    if(User_Name.length()>100){
      throw new IllegalArgumentException("User_Name is too long, it must be less than 101 characters");
    }
    this.User_Name = User_Name;
  }
  public char[] getUser_PW() {
    return User_PW;
  }
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
  public Integer getStatus_ID() {
    return Status_ID;
  }
  public void setStatus_ID(Integer Status_ID) {
    this.Status_ID = Status_ID;
  }
  public String getEmail() {
    return Email;
  }
  public void setEmail(String Email) {
    Matcher matcher = MyValidators.emailPattern.matcher(Email);
    if (!matcher.matches()){
      throw new IllegalArgumentException("Invalid Email");
    }
    if(Email.length()<4){
      throw new IllegalArgumentException("Email is too short.");
    }
    if(Email.length()>100){
      throw new IllegalArgumentException("Email is too long, it must be under 101 characters");
    }
    this.Email = Email;
  }
  public Integer getLanguage_ID() {
    return Language_ID;
  }
  public void setLanguage_ID(Integer Language_ID) {
    this.Language_ID = Language_ID;
  }
  public Integer getPrivilege_ID() {
    return Privilege_ID;
  }
  public void setPrivilege_ID(Integer Privilege_ID) {
    this.Privilege_ID = Privilege_ID;
  }

}

