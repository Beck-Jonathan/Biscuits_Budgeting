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
import java.util.Date;

public class Budget_Line_Item implements Comparable<Budget_Line_Item> {
  private String Budget_Line_Item_id;
  private String budget_id;
  private String name;
  private String details;
  private Date line_item_date;
  private Double amount;
  private String budget_line_type_id;
  private String budget_line_status_id;
  private String transaction_id;
  private Date created_at;
  private Date updated_at;

  public Budget_Line_Item(){}

  public Budget_Line_Item(String Budget_Line_Item_id, String budget_id, String name, String details, Date line_item_date, Double amount, String budget_line_type_id, String budget_line_status_id, String transaction_id, Date created_at, Date updated_at) {

    this.Budget_Line_Item_id = Budget_Line_Item_id;
    this.budget_id = budget_id;
    this.name = name;
    this.details = details;
    this.line_item_date = line_item_date;
    this.amount = amount;
    this.budget_line_type_id = budget_line_type_id;
    this.budget_line_status_id = budget_line_status_id;
    this.transaction_id = transaction_id;
    this.created_at = created_at;
    this.updated_at = updated_at;
  }

  public Budget_Line_Item(String Budget_Line_Item_id) {

    this.Budget_Line_Item_id = Budget_Line_Item_id;
  }

  /**
   * <p> Gets the Budget_Line_Item_id of the associated Budget_Line_Item object </p>
   * @return the Budget_Line_Item_id of this Budget_Line_Item object.
   */
  public String getBudget_Line_Item_id() {
    return Budget_Line_Item_id;
  }

  /**
   * <p> Sets the Budget_Line_Item_id of the associated Budget_Line_Item object </p>
   * @param Budget_Line_Item_id the Budget_Line_Item_id of the Budget_Line_Item,
   * throws IllegalArgumentException if Budget_Line_Item_id under 3 characters or longer than 36 characters
   */
  public void setBudget_Line_Item_id(String Budget_Line_Item_id) {
    Budget_Line_Item_id = Budget_Line_Item_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(Budget_Line_Item_id.length()<4){
      throw new IllegalArgumentException("Budget_Line_Item_id is too short.");
    }
    if(Budget_Line_Item_id.length()>36){
      throw new IllegalArgumentException("Budget_Line_Item_id is too long.");
    }
    this.Budget_Line_Item_id = Budget_Line_Item_id;
  }

  /**
   * <p> Gets the budget_id of the associated Budget_Line_Item object </p>
   * @return the budget_id of this Budget_Line_Item object.
   */
  public String getbudget_id() {
    return budget_id;
  }

  /**
   * <p> Sets the budget_id of the associated Budget_Line_Item object </p>
   * @param budget_id the budget_id of the Budget_Line_Item,
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
   * <p> Gets the name of the associated Budget_Line_Item object </p>
   * @return the name of this Budget_Line_Item object.
   */
  public String getname() {
    return name;
  }

  /**
   * <p> Sets the name of the associated Budget_Line_Item object </p>
   * @param name the name of the Budget_Line_Item,
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
   * <p> Gets the details of the associated Budget_Line_Item object </p>
   * @return the details of this Budget_Line_Item object.
   */
  public String getdetails() {
    return details;
  }

  /**
   * <p> Sets the details of the associated Budget_Line_Item object </p>
   * @param details the details of the Budget_Line_Item,
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
   * <p> Gets the line_item_date of the associated Budget_Line_Item object </p>
   * @return the line_item_date of this Budget_Line_Item object.
   */
  public Date getline_item_date() {
    return line_item_date;
  }

  /**
   * <p> Sets the line_item_date of the associated Budget_Line_Item object </p>
   * @param line_item_date the line_item_date of the Budget_Line_Item,
   * throws IllegalArgumentException if line_item_date is outside of a logical range
   */
  public void setline_item_date(Date line_item_date)throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (line_item_date.compareTo(_minDate)<0){
      throw new IllegalArgumentException("line_item_date Can Not Be Before 1991");
    }
    if (line_item_date.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("line_item_date Can Not Be after 2100");
    }
    this.line_item_date = line_item_date;
  }

  /**
   * <p> Gets the amount of the associated Budget_Line_Item object </p>
   * @return the amount of this Budget_Line_Item object.
   */
  public Double getamount() {
    return amount;
  }

  /**
   * <p> Sets the amount of the associated Budget_Line_Item object </p>
   * @param amount the amount of the Budget_Line_Item,
   * throws IllegalArgumentException if amount is outside of a logical range
   */
  public void setamount(Double amount) {
    if (amount<0||amount>10000){
      throw new IllegalArgumentException("amount Can Not Be Negative");
    }
    this.amount = amount;
  }

  /**
   * <p> Gets the budget_line_type_id of the associated Budget_Line_Item object </p>
   * @return the budget_line_type_id of this Budget_Line_Item object.
   */
  public String getbudget_line_type_id() {
    return budget_line_type_id;
  }

  /**
   * <p> Sets the budget_line_type_id of the associated Budget_Line_Item object </p>
   * @param budget_line_type_id the budget_line_type_id of the Budget_Line_Item,
   * throws IllegalArgumentException if budget_line_type_id under 3 characters or longer than 50 characters
   */
  public void setbudget_line_type_id(String budget_line_type_id) {
    budget_line_type_id = budget_line_type_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(budget_line_type_id.length()<4){
      throw new IllegalArgumentException("budget_line_type_id is too short.");
    }
    if(budget_line_type_id.length()>50){
      throw new IllegalArgumentException("budget_line_type_id is too long.");
    }
    this.budget_line_type_id = budget_line_type_id;
  }

  /**
   * <p> Gets the budget_line_status_id of the associated Budget_Line_Item object </p>
   * @return the budget_line_status_id of this Budget_Line_Item object.
   */
  public String getbudget_line_status_id() {
    return budget_line_status_id;
  }

  /**
   * <p> Sets the budget_line_status_id of the associated Budget_Line_Item object </p>
   * @param budget_line_status_id the budget_line_status_id of the Budget_Line_Item,
   * throws IllegalArgumentException if budget_line_status_id under 3 characters or longer than 50 characters
   */
  public void setbudget_line_status_id(String budget_line_status_id) {
    budget_line_status_id = budget_line_status_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(budget_line_status_id.length()<4){
      throw new IllegalArgumentException("budget_line_status_id is too short.");
    }
    if(budget_line_status_id.length()>50){
      throw new IllegalArgumentException("budget_line_status_id is too long.");
    }
    this.budget_line_status_id = budget_line_status_id;
  }

  /**
   * <p> Gets the transaction_id of the associated Budget_Line_Item object </p>
   * @return the transaction_id of this Budget_Line_Item object.
   */
  public String gettransaction_id() {
    return transaction_id;
  }

  /**
   * <p> Sets the transaction_id of the associated Budget_Line_Item object </p>
   * @param transaction_id the transaction_id of the Budget_Line_Item,
   * throws IllegalArgumentException if transaction_id under 3 characters or longer than 36 characters
   */
  public void settransaction_id(String transaction_id) {
    transaction_id = transaction_id.replaceAll("[^.,!()A-Za-z0-9 - ]","");
    if(transaction_id.length()<4){
      throw new IllegalArgumentException("transaction_id is too short.");
    }
    if(transaction_id.length()>36){
      throw new IllegalArgumentException("transaction_id is too long.");
    }
    this.transaction_id = transaction_id;
  }

  /**
   * <p> Gets the created_at of the associated Budget_Line_Item object </p>
   * @return the created_at of this Budget_Line_Item object.
   */
  public Date getcreated_at() {
    return created_at;
  }

  /**
   * <p> Sets the created_at of the associated Budget_Line_Item object </p>
   * @param created_at the created_at of the Budget_Line_Item,
   * throws IllegalArgumentException if created_at is outside of a logical range
   */
  public void setcreated_at(Date created_at)throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (created_at.compareTo(_minDate)<0){
      throw new IllegalArgumentException("created_at Can Not Be Before 1991");
    }
    if (created_at.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("created_at Can Not Be after 2100");
    }
    this.created_at = created_at;
  }

  /**
   * <p> Gets the updated_at of the associated Budget_Line_Item object </p>
   * @return the updated_at of this Budget_Line_Item object.
   */
  public Date getupdated_at() {
    return updated_at;
  }

  /**
   * <p> Sets the updated_at of the associated Budget_Line_Item object </p>
   * @param updated_at the updated_at of the Budget_Line_Item,
   * throws IllegalArgumentException if updated_at is outside of a logical range
   */
  public void setupdated_at(Date updated_at)throws ParseException {
    String minDate = "01/01/1991";
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Date _minDate = df.parse(minDate);
    String maxDate = "12/31/2100";
    Date _maxDate = df.parse(maxDate);
    if (updated_at.compareTo(_minDate)<0){
      throw new IllegalArgumentException("updated_at Can Not Be Before 1991");
    }
    if (updated_at.compareTo(_maxDate)>0){
      throw new IllegalArgumentException("updated_at Can Not Be after 2100");
    }
    this.updated_at = updated_at;
  }
  @Override
  public int compareTo(@NotNull Budget_Line_Item o) {
    if (this.Budget_Line_Item_id.compareTo(o.Budget_Line_Item_id)<0){
      return -1;
    }
    else if(this.Budget_Line_Item_id.compareTo(o.Budget_Line_Item_id) > 0){
      return 1;
    }
    if (this.budget_id.compareTo(o.budget_id)<0){
      return -1;
    }
    else if(this.budget_id.compareTo(o.budget_id) > 0){
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
    if (this.line_item_date.compareTo(o.line_item_date)<0){
      return -1;
    }
    else if(this.line_item_date.compareTo(o.line_item_date) > 0){
      return 1;
    }
    if (this.amount.compareTo(o.amount)<0){
      return -1;
    }
    else if(this.amount.compareTo(o.amount) > 0){
      return 1;
    }
    if (this.budget_line_type_id.compareTo(o.budget_line_type_id)<0){
      return -1;
    }
    else if(this.budget_line_type_id.compareTo(o.budget_line_type_id) > 0){
      return 1;
    }
    if (this.budget_line_status_id.compareTo(o.budget_line_status_id)<0){
      return -1;
    }
    else if(this.budget_line_status_id.compareTo(o.budget_line_status_id) > 0){
      return 1;
    }
    if (this.transaction_id.compareTo(o.transaction_id)<0){
      return -1;
    }
    else if(this.transaction_id.compareTo(o.transaction_id) > 0){
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

