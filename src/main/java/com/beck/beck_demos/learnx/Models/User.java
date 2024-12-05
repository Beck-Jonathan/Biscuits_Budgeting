package com.beck.beck_demos.learnx.Models;

import com.beck.beck_demos.shared.MyValidators;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;


import static java.util.Collections.replaceAll;

public class User {
  int ID;
  String first_name;
  String last_name;
  String email;
  String phone;
  char[] password;
  String status;
  String privileges;
  Instant created_at;
  Instant last_logged_in;
  Instant updated_at;
  String language;
  private static final LocalDate birthday = LocalDate.of(2000,01,01);

  public User(int ID, String first_name, String last_name, String email, String phone, char[] password, String status, String privileges, Instant created_at, Instant last_logged_in, Instant updated_at, String language) {
    this.ID = ID;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.phone = phone;
    this.password = password;
    this.status = status;
    this.privileges = privileges;
    this.created_at = created_at;
    this.last_logged_in = last_logged_in;
    this.updated_at = updated_at;
    this.language = language;
  }

  public User(int id, String firstName, String lastName, String email, String phone, String language, String status, String privileges, Instant created_at, Instant last_logged_in, Instant updated_at) {
    this(id, firstName, lastName, email, phone, null, status, privileges, created_at, last_logged_in, updated_at,language);
  }

  public User() {
  }

  public static LocalDate getBirthday() {
    return birthday;
  }
  public  Date getCreatedDate(){
    return Date.from(created_at);

  }
  public Date getLastLoggedInDae() {
    return Date.from(last_logged_in);
  }
  public Date getUpdatedAtDate(){
    return Date.from(updated_at);
  }
  public Date getBirthdayDate(){
    return java.sql.Date.valueOf(birthday);
  }


  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {

    first_name=first_name.replaceAll("[^A-Za-z0-9@ - ]","");
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {

      Matcher matcher = MyValidators.emailPattern.matcher(email);
          if (!matcher.matches()){
            throw new IllegalArgumentException("Invalid email");
          }
          this.email=email;

    }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public char[] getPassword() {
    return password;
  }

  public void setPassword(char[] password) {
    if (password!=null) {

      String passwordStr = String.valueOf(password);
      Matcher matcher = MyValidators.passwordPattern.matcher(passwordStr);
      if (!matcher.matches()) {
        throw new IllegalArgumentException("Password must be 8 characters, with 3 of 4 (lowercase, uppercase, number, symbol)");
      }
    }
    this.password = password;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPrivileges() {
    return privileges;
  }

  public void setPrivileges(String privileges) {
    this.privileges = privileges;
  }

  public Instant getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Instant created_at) {
    this.created_at = created_at;
  }

  public Instant getLast_logged_in() {
    return last_logged_in;
  }

  public void setLast_logged_in(Instant last_logged_in) {
    this.last_logged_in = last_logged_in;
  }

  public Instant getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Instant updated_at) {
    this.updated_at = updated_at;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    language=language.replaceAll("[^A-Za-z0-9@ - ]","");
    Matcher matcher = MyValidators.languagePattern.matcher(language);
    if (!matcher.matches()){
      throw new IllegalArgumentException("Invalid Language");
    }
    this.language=language;




  }

  @Override
  public String toString() {
    return "User{" +
        "ID=" + ID +
        ", first_name='" + first_name + '\'' +
        ", last_name='" + last_name + '\'' +
        ", email='" + email + '\'' +
        ", phone='" + phone + '\'' +
        ", password=" + Arrays.toString(password) +
        ", status='" + status + '\'' +
        ", privileges='" + privileges + '\'' +
        ", created_at=" + created_at +
        ", last_logged_in=" + last_logged_in +
        ", updated_at=" + updated_at +
        ", language='" + language + '\'' +
        '}';
  }
}
