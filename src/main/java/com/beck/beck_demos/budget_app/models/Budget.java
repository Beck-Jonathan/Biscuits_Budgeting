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

public class Budget  implements Comparable<Budget> {
  private String budget_id;
  private String user_id;
  private String name;
  private String details;
  private LocalDate start_date;
  private Double limit_amount;
  private String currency_code_id;
  private boolean is_active;
  private LocalDate created_at;
  private LocalDate updated_at;

  public Budget (){}

  public Budget (String budget_id, String user_id, String name, String details, LocalDate start_date, Double limit_amount, String currency_code_id, boolean is_active, LocalDate created_at, LocalDate updated_at) {

    this.budget_id = budget_id;
    this.user_id = user_id;
    this.name = name;
    this.details = details;
    this.start_date = start_date;
    this.limit_amount = limit_amount;
    this.currency_code_id = currency_code_id;
    this.is_active = is_active;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public Budget (String budget_id) {

    this.budget_id = budget_id;
  }

  /**
   * <p> Gets the budget_id of the associated budget  object </p>
   * @return the budget_id of this budget  object.
   */
  public String getbudget_id() {
    return budget_id;
  }

  /**
   * <p> Sets the budget_id of the associated budget  object </p>
   * @param budget_id the budget_id of the budget ,
   * throws IllegalArgumentException if budget_id under 3 characters or longer than 36 characters
   */
  public void setbudget_id(String budget_id) {

    if(budget_id.length()<36){
      throw new IllegalArgumentException("budget_id is too short.");
    }
    if(budget_id.length()>36){
      throw new IllegalArgumentException("budget_id is too long.");
    }
    this.budget_id = budget_id;
  }

  /**
   * <p> Gets the user_id of the associated budget  object </p>
   * @return the user_id of this budget  object.
   */
  public String getuser_id() {
    return user_id;
  }

  /**
   * <p> Sets the user_id of the associated budget  object </p>
   * @param user_id the user_id of the budget ,
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
   * <p> Gets the name of the associated budget  object </p>
   * @return the name of this budget  object.
   */
  public String getname() {
    return name;
  }

  /**
   * <p> Sets the name of the associated budget  object </p>
   * @param name the name of the budget ,
   * throws IllegalArgumentException if name under 3 characters or longer than 50 characters
   */
  public void setname(String name) {
    name = name.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(name.length()<4){
      throw new IllegalArgumentException("name is too short.");
    }
    if(name.length()>50){
      throw new IllegalArgumentException("name is too long.");
    }
    this.name = name;
  }

  /**
   * <p> Gets the details of the associated budget  object </p>
   * @return the details of this budget  object.
   */
  public String getdetails() {
    return details;
  }

  /**
   * <p> Sets the details of the associated budget  object </p>
   * @param details the details of the budget ,
   * throws IllegalArgumentException if details under 3 characters or longer than 255 characters
   */
  public void setdetails(String details) {
    details = details.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(details.length()<4){
      throw new IllegalArgumentException("details is too short.");
    }
    if(details.length()>255){
      throw new IllegalArgumentException("details is too long.");
    }
    this.details = details;
  }

  /**
   * <p> Gets the start_date of the associated budget  object </p>
   * @return the start_date of this budget  object.
   */
  public LocalDate getstart_date() {
    return start_date;
  }

  /**
   * <p> Sets the start_date of the associated budget  object </p>
   * @param start_date the start_date of the budget ,
   * throws IllegalArgumentException if start_date is outside of a logical range
   */
  public void setstart_date(LocalDate start_date)throws ParseException {
    LocalDate minDate = LocalDate.of(1991, 1, 1);
    LocalDate maxDate = LocalDate.of(2100, 12, 31);
    if (start_date.compareTo(minDate)<0){
      throw new IllegalArgumentException("start_date Can Not Be Before 1991");
    }
    if (start_date.compareTo(maxDate)>0){
      throw new IllegalArgumentException("start_date Can Not Be after 2100");
    }
    this.start_date = start_date;
  }

  /**
   * <p> Gets the limit_amount of the associated budget  object </p>
   * @return the limit_amount of this budget  object.
   */
  public Double getlimit_amount() {
    return limit_amount;
  }

  /**
   * <p> Sets the limit_amount of the associated budget  object </p>
   * @param limit_amount the limit_amount of the budget ,
   * throws IllegalArgumentException if limit_amount is outside of a logical range
   */
  public void setlimit_amount(Double limit_amount) {
    if (limit_amount<0){
      throw new IllegalArgumentException("limit_amount Can Not Be Negative");
    }
    if (limit_amount>10000000){
      throw new IllegalArgumentException("limit_amount can not be over 10,000,000");
    }
    this.limit_amount = limit_amount;
  }

  /**
   * <p> Gets the currency_code_id of the associated budget  object </p>
   * @return the currency_code_id of this budget  object.
   */
  public String getcurrency_code_id() {
    return currency_code_id;
  }

  /**
   * <p> Sets the currency_code_id of the associated budget  object </p>
   * @param currency_code_id the currency_code_id of the budget ,
   * throws IllegalArgumentException if currency_code_id under 3 characters or longer than 3 characters
   */
  public void setcurrency_code_id(String currency_code_id) {
    currency_code_id = currency_code_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(currency_code_id.length()<3){
      throw new IllegalArgumentException("currency_code_id is too short.");
    }
    if(currency_code_id.length()>3){
      throw new IllegalArgumentException("currency_code_id is too long.");
    }
    this.currency_code_id = currency_code_id;
  }

  /**
   * <p> Gets the is_active of the associated budget  object </p>
   * @return the is_active of this budget  object.
   */
  public boolean getis_active() {
    return is_active;
  }

  /**
   * <p> Sets the is_active of the associated budget  object </p>
   * @param is_active the is_active of the budget ,
   * throws IllegalArgumentException if is_active is outside of a logical range
   */
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

  /**
   * <p> Gets the created_at of the associated budget  object </p>
   * @return the created_at of this budget  object.
   */
  public LocalDate getcreated_at() {
    return created_at;
  }

  /**
   * <p> Sets the created_at of the associated budget  object </p>
   * @param created_at the created_at of the budget ,
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
   * <p> Gets the updated_at of the associated budget  object </p>
   * @return the updated_at of this budget  object.
   */
  public LocalDate getupdated_at() {
    return updated_at;
  }

  /**
   * <p> Sets the updated_at of the associated budget  object </p>
   * @param updated_at the updated_at of the budget ,
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
  public int compareTo(@NotNull Budget  o) {
    if (this.budget_id.compareTo(o.budget_id)<0){
      return -1;
    }
    else if(this.budget_id.compareTo(o.budget_id) > 0){
      return 1;
    }
    if (this.user_id.compareTo(o.user_id)<0){
      return -1;
    }
    else if(this.user_id.compareTo(o.user_id) > 0){
      return 1;
    }
    if (this.name.compareTo(o.name)<0){
      return -1;
    }
    else if(this.name.compareTo(o.name) > 0){
      return 1;
    }
    if (this.details.compareTo(o.details)<0){
      return -1;
    }
    else if(this.details.compareTo(o.details) > 0){
      return 1;
    }
    if (this.start_date.compareTo(o.start_date)<0){
      return -1;
    }
    else if(this.start_date.compareTo(o.start_date) > 0){
      return 1;
    }
    if (this.limit_amount.compareTo(o.limit_amount)<0){
      return -1;
    }
    else if(this.limit_amount.compareTo(o.limit_amount) > 0){
      return 1;
    }
    if (this.currency_code_id.compareTo(o.currency_code_id)<0){
      return -1;
    }
    else if(this.currency_code_id.compareTo(o.currency_code_id) > 0){
      return 1;
    }
    if (!this.is_active&&o.is_active){
      return -1;
    }
    if (this.is_active&&!o.is_active){
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


