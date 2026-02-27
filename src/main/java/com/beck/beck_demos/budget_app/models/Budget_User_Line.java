package com.beck.beck_demos.budget_app.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */
import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class Budget_User_Line implements Comparable<Budget_User_Line> {
  private String Budget_User_Line_id;
  private String user_id;
  private String budget_id;
  private String budget_role_id;
  private LocalDate created_at;
  private LocalDate updated_at;

  public Budget_User_Line(){}

  public Budget_User_Line(String Budget_User_Line_id, String user_id, String budget_id, String budget_role_id, LocalDate created_at, LocalDate updated_at) {

    this.Budget_User_Line_id = Budget_User_Line_id;
    this.user_id = user_id;
    this.budget_id = budget_id;
    this.budget_role_id = budget_role_id;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public Budget_User_Line(String Budget_User_Line_id) {

    this.Budget_User_Line_id = Budget_User_Line_id;
  }

  /**
   * <p> Gets the Budget_User_Line_id of the associated Budget_User_Line object </p>
   * @return the Budget_User_Line_id of this Budget_User_Line object.
   */
  public String getBudget_User_Line_id() {
    return Budget_User_Line_id;
  }

  /**
   * <p> Sets the Budget_User_Line_id of the associated Budget_User_Line object </p>
   * @param Budget_User_Line_id the Budget_User_Line_id of the Budget_User_Line,
   * throws IllegalArgumentException if Budget_User_Line_id under 3 characters or longer than 36 characters
   */
  public void setBudget_User_Line_id(String Budget_User_Line_id) {
    Budget_User_Line_id = Budget_User_Line_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Budget_User_Line_id.length()<4){
      throw new IllegalArgumentException("Budget_User_Line_id is too short.");
    }
    if(Budget_User_Line_id.length()>36){
      throw new IllegalArgumentException("Budget_User_Line_id is too long.");
    }
    this.Budget_User_Line_id = Budget_User_Line_id;
  }

  /**
   * <p> Gets the user_id of the associated Budget_User_Line object </p>
   * @return the user_id of this Budget_User_Line object.
   */
  public String getuser_id() {
    return user_id;
  }

  /**
   * <p> Sets the user_id of the associated Budget_User_Line object </p>
   * @param user_id the user_id of the Budget_User_Line,
   * throws IllegalArgumentException if user_id under 3 characters or longer than 36 characters
   */
  public void setuser_id(String user_id) {
    user_id = user_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(user_id.length()<4){
      throw new IllegalArgumentException("user_id is too short.");
    }
    if(user_id.length()>36){
      throw new IllegalArgumentException("user_id is too long.");
    }
    this.user_id = user_id;
  }

  /**
   * <p> Gets the budget_id of the associated Budget_User_Line object </p>
   * @return the budget_id of this Budget_User_Line object.
   */
  public String getbudget_id() {
    return budget_id;
  }

  /**
   * <p> Sets the budget_id of the associated Budget_User_Line object </p>
   * @param budget_id the budget_id of the Budget_User_Line,
   * throws IllegalArgumentException if budget_id under 3 characters or longer than 36 characters
   */
  public void setbudget_id(String budget_id) {
    budget_id = budget_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(budget_id.length()<4){
      throw new IllegalArgumentException("budget_id is too short.");
    }
    if(budget_id.length()>36){
      throw new IllegalArgumentException("budget_id is too long.");
    }
    this.budget_id = budget_id;
  }

  /**
   * <p> Gets the budget_role_id of the associated Budget_User_Line object </p>
   * @return the budget_role_id of this Budget_User_Line object.
   */
  public String getbudget_role_id() {
    return budget_role_id;
  }

  /**
   * <p> Sets the budget_role_id of the associated Budget_User_Line object </p>
   * @param budget_role_id the budget_role_id of the Budget_User_Line,
   * throws IllegalArgumentException if budget_role_id under 3 characters or longer than 50 characters
   */
  public void setbudget_role_id(String budget_role_id) {
    budget_role_id = budget_role_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(budget_role_id.length()<4){
      throw new IllegalArgumentException("budget_role_id is too short.");
    }
    if(budget_role_id.length()>50){
      throw new IllegalArgumentException("budget_role_id is too long.");
    }
    this.budget_role_id = budget_role_id;
  }

  /**
   * <p> Gets the created_at of the associated Budget_User_Line object </p>
   * @return the created_at of this Budget_User_Line object.
   */
  public LocalDate getcreated_at() {
    return created_at;
  }

  /**
   * <p> Sets the created_at of the associated Budget_User_Line object </p>
   * @param created_at the created_at of the Budget_User_Line,
   * throws IllegalArgumentException if created_at is outside of a logical range
   */
  public void setcreated_at(LocalDate created_at)throws ParseException {
    LocalDate minDate = LocalDate.of(1991, 1, 1);
    LocalDate maxDate = LocalDate.of(2100, 12, 31);
    if (created_at.compareTo(minDate)<0){
      throw new IllegalArgumentException("created_at Can Not Be Before 1991");
    }
    if (created_at.compareTo(maxDate)>0){
      throw new IllegalArgumentException("created_at Can Not Be after 2100");
    }
    this.created_at = created_at;
  }

  /**
   * <p> Gets the updated_at of the associated Budget_User_Line object </p>
   * @return the updated_at of this Budget_User_Line object.
   */
  public LocalDate getupdated_at() {
    return updated_at;
  }

  /**
   * <p> Sets the updated_at of the associated Budget_User_Line object </p>
   * @param updated_at the updated_at of the Budget_User_Line,
   * throws IllegalArgumentException if updated_at is outside of a logical range
   */
  public void setupdated_at(LocalDate updated_at)throws ParseException {
    LocalDate minDate = LocalDate.of(1991, 1, 1);
    LocalDate maxDate = LocalDate.of(2100, 12, 31);
    if (updated_at.compareTo(minDate)<0){
      throw new IllegalArgumentException("updated_at Can Not Be Before 1991");
    }
    if (updated_at.compareTo(maxDate)>0){
      throw new IllegalArgumentException("updated_at Can Not Be after 2100");
    }
    this.updated_at = updated_at;
  }
  @Override
  public int compareTo(@NotNull Budget_User_Line o) {
    if (this.Budget_User_Line_id.compareTo(o.Budget_User_Line_id)<0){
      return -1;
    }
    else if(this.Budget_User_Line_id.compareTo(o.Budget_User_Line_id) > 0){
      return 1;
    }
    if (this.user_id.compareTo(o.user_id)<0){
      return -1;
    }
    else if(this.user_id.compareTo(o.user_id) > 0){
      return 1;
    }
    if (this.budget_id.compareTo(o.budget_id)<0){
      return -1;
    }
    else if(this.budget_id.compareTo(o.budget_id) > 0){
      return 1;
    }
    if (this.budget_role_id.compareTo(o.budget_role_id)<0){
      return -1;
    }
    else if(this.budget_role_id.compareTo(o.budget_role_id) > 0){
      return 1;
    }
    if (this.created_at.compareTo(o.created_at)<0){
      return -1;
    }
    else if(this.created_at.compareTo(o.created_at) > 0){
      return 1;
    }
    if (this.updated_at.compareTo(o.updated_at)<0){
      return -1;
    }
    else if(this.updated_at.compareTo(o.updated_at) > 0){
      return 1;
    }
    return 0;
  }

}

