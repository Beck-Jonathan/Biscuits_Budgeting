package com.beck.beck_demos.crrg.models;

import com.beck.beck_demos.shared.MyValidators;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class User {
  private String User_ID;
  private String Role_ID;
  private String First_Name;
  private String Last_Name;
  private String Email;
  private DateTime Last_Logged_In;
  private char[] Password;

  public User(){}

  public User(String User_ID, String Role_ID, String First_Name, String Last_Name, String Email, DateTime Last_Logged_In, char[] Password) {

    this.User_ID = User_ID;
    this.Role_ID = Role_ID;
    this.First_Name = First_Name;
    this.Last_Name = Last_Name;
    this.Email = Email;
    this.Last_Logged_In = Last_Logged_In;
    this.Password = Password;
  }

  public User(String User_ID, String Email) {

    this.User_ID = User_ID;
    this.Email = Email;
  }
  public String getUser_ID() {
    return User_ID;
  }
  public void setUser_ID(String User_ID) {
    User_ID = User_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(User_ID.length()<4){
      throw new IllegalArgumentException("User_ID is too short.");
    }
    if(User_ID.length()>100){
      throw new IllegalArgumentException("User_ID is too long.");
    }
    this.User_ID = User_ID;
  }
  public String getRole_ID() {
    return Role_ID;
  }
  public void setRole_ID(String Role_ID) {
    Role_ID = Role_ID.replaceAll("[^A-Za-z0-9 - ]","");
    if(Role_ID.length()<3){
      throw new IllegalArgumentException("Role_ID is too short.");
    }
    if(Role_ID.length()>100){
      throw new IllegalArgumentException("Role_ID is too long.");
    }
    this.Role_ID = Role_ID;
  }
  public String getFirst_Name() {
    return First_Name;
  }
  public void setFirst_Name(String First_Name) {
    First_Name = First_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(First_Name.length()<3){
      throw new IllegalArgumentException("First_Name is too short.");
    }
    if(First_Name.length()>100){
      throw new IllegalArgumentException("First_Name is too long.");
    }
    this.First_Name = First_Name;
  }
  public String getLast_Name() {
    return Last_Name;
  }
  public void setLast_Name(String Last_Name) {
    Last_Name = Last_Name.replaceAll("[^A-Za-z0-9 - ]","");
    if(Last_Name.length()<3){
      throw new IllegalArgumentException("Last_Name is too short.");
    }
    if(Last_Name.length()>100){
      throw new IllegalArgumentException("Last_Name is too long.");
    }
    this.Last_Name = Last_Name;
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
  public DateTime getLast_Logged_In() {
    return Last_Logged_In;
  }
  public void setLast_Logged_In(DateTime Last_Logged_In) {
    this.Last_Logged_In = Last_Logged_In;
  }
  public char[] getPassword() {
    return Password;
  }
  public void setPassword(char[] User_PW) {
    if (Password!=null) {

      String passwordStr = String.valueOf(User_PW);
      Matcher matcher = MyValidators.passwordPattern.matcher(passwordStr);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Password must be 8 characters, with 3 of 4 (lowercase, uppercase, number, symbol)");
      }
    }
    Password = User_PW;
  }
  public boolean isInRole(List<String> rolesNeeded){
    boolean result=false;
    for (String r : rolesNeeded){
      if (r.equals(Role_ID)){
        result=true;
        break;
      }
    }
    return result;
  }

}


