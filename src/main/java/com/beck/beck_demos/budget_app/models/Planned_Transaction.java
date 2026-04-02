package com.beck.beck_demos.budget_app.models;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Planned_Transaction implements Comparable<Planned_Transaction> {
  private String planned_transaction_ID;
  private String user_ID;
  private String subcategory_ID;
  private String budget_id;
  private String nickname;
  private Double amount;
  private Date start_date;
  private Integer times_per_year;
  private Integer occurrences;
  private boolean is_active;

  public Planned_Transaction() {
  }

  public Planned_Transaction(String planned_transaction_ID, String user_ID, String subcategory_ID, String Budget_ID, String nickname, Double amount, Date start_date, Integer times_per_year, Integer occurrences, boolean is_active) {

    this.planned_transaction_ID = planned_transaction_ID;
    this.user_ID = user_ID;
    this.subcategory_ID = subcategory_ID;
    this.budget_id = Budget_ID;
    this.nickname = nickname;
    this.amount = amount;
    this.start_date = start_date;
    this.times_per_year = times_per_year;
    this.occurrences = occurrences;
    this.is_active = is_active;
  }

  public Planned_Transaction(String planned_transaction_ID) {

    this.planned_transaction_ID = planned_transaction_ID;
  }

  /**
   * <p> Gets the planned_transaction_ID of the associated planned_transaction object </p>
   *
   * @return the planned_transaction_ID of this planned_transaction object.
   */
  public String getplanned_transaction_ID() {
    return planned_transaction_ID;
  }

  /**
   * <p> Sets the planned_transaction_ID of the associated planned_transaction object </p>
   *
   * @param planned_transaction_ID the planned_transaction_id of the planned_transaction,
   *                               throws IllegalArgumentException if planned_transaction_ID under 3 characters or longer than 36 characters
   */
  public void setplanned_transaction_ID(String planned_transaction_ID) {
    if (planned_transaction_ID.length() < 36) {
      throw new IllegalArgumentException("planned_transaction_ID is too short.");
    }
    if (planned_transaction_ID.length() > 36) {
      throw new IllegalArgumentException("planned_transaction_ID is too long.");
    }
    this.planned_transaction_ID = planned_transaction_ID;
  }

  /**
   * <p> Gets the user_ID of the associated planned_transaction object </p>
   *
   * @return the user_ID of this planned_transaction object.
   */
  public String getuser_ID() {
    return user_ID;
  }

  /**
   * <p> Sets the user_ID of the associated planned_transaction object </p>
   *
   * @param user_ID the user_id of the planned_transaction,
   *                throws IllegalArgumentException if user_ID under 3 characters or longer than 36 characters
   */
  public void setuser_ID(String user_ID) {

    if (user_ID.length() < 36) {
      throw new IllegalArgumentException("user_ID is too short.");
    }
    if (user_ID.length() > 36) {
      throw new IllegalArgumentException("user_ID is too long.");
    }
    this.user_ID = user_ID;
  }

  /**
   * <p> Gets the subcategory_ID of the associated planned_transaction object </p>
   *
   * @return the subcategory_ID of this planned_transaction object.
   */
  public String getsubcategory_ID() {
    return subcategory_ID;
  }

  /**
   * <p> Sets the subcategory_ID of the associated planned_transaction object </p>
   *
   * @param subcategory_ID the subcategory_id of the planned_transaction,
   *                       throws IllegalArgumentException if subcategory_ID under 3 characters or longer than 36 characters
   */
  public void setsubcategory_ID(String subcategory_ID) {

    if (subcategory_ID.length() < 36) {
      throw new IllegalArgumentException("subcategory_ID is too short.");
    }
    if (subcategory_ID.length() > 36) {
      throw new IllegalArgumentException("subcategory_ID is too long.");
    }
    this.subcategory_ID = subcategory_ID;
  }

  /**
   * <p> Gets the budget_id of the associated planned_transaction object </p>
   *
   * @return the budget_id of this planned_transaction object.
   */
  public String getbudget_id() {
    return budget_id;
  }

  /**
   * <p> Sets the budget_id of the associated planned_transaction object </p>
   *
   * @param budget_id the budget_id of the planned_transaction,
   *                  throws IllegalArgumentException if budget_id under 3 characters or longer than 36 characters
   */
  public void setbudget_id(String budget_id) {
    if (budget_id == null || budget_id.isEmpty()) {
      this.budget_id = null;
      return;
    }
    if (budget_id.length() < 36) {
      throw new IllegalArgumentException("budget_id is too short.");
    }
    if (budget_id.length() > 36) {
      throw new IllegalArgumentException("budget_id is too long.");
    }
    this.budget_id = budget_id;
  }

  /**
   * <p> Gets the nickname of the associated planned_transaction object </p>
   *
   * @return the nickname of this planned_transaction object.
   */
  public String getnickname() {
    return nickname;
  }

  /**
   * <p> Sets the nickname of the associated planned_transaction object </p>
   *
   * @param nickname the nickname of the planned_transaction,
   *                 throws IllegalArgumentException if nickname under 3 characters or longer than 100 characters
   */
  public void setnickname(String nickname) {
    nickname = nickname.replaceAll("[^.,!()A-Za-z0-9 - ]", "");
    if (nickname.length() < 3) {
      throw new IllegalArgumentException("nickname is too short.");
    }
    if (nickname.length() > 100) {
      throw new IllegalArgumentException("nickname is too long.");
    }
    this.nickname = nickname;
  }

  /**
   * <p> Gets the amount of the associated planned_transaction object </p>
   *
   * @return the amount of this planned_transaction object.
   */
  public Double getamount() {
    return amount;
  }

  /**
   * <p> Sets the amount of the associated planned_transaction object </p>
   *
   * @param amount the amount of the planned_transaction,
   *               throws IllegalArgumentException if amount is outside of a logical range
   */
  public void setamount(Double amount) {
    if (amount < -200000 || amount > 200000) {
      throw new IllegalArgumentException("Invalid Amount");
    }
    this.amount = amount;
  }

  /**
   * <p> Gets the start_date of the associated planned_transaction object </p>
   *
   * @return the start_date of this planned_transaction object.
   */
  public Date getstart_date() {
    return start_date;
  }

  /**
   * <p> Sets the start_date of the associated planned_transaction object </p>
   *
   * @param start_date the start_date of the planned_transaction,
   *                   throws IllegalArgumentException if start_date is outside of a logical range
   */
  public void setstart_date(Date start_date) {
    this.start_date = start_date;
  }

  /**
   * <p> Gets the times_per_year of the associated planned_transaction object </p>
   *
   * @return the times_per_year of this planned_transaction object.
   */
  public Integer gettimes_per_year() {
    return times_per_year;
  }

  /**
   * <p> Sets the times_per_year of the associated planned_transaction object </p>
   *
   * @param times_per_year the times_per_year of the planned_transaction,
   *                       throws IllegalArgumentException if times_per_year is negative or greater than 10,000
   */
  public void settimes_per_year(Integer times_per_year) {
    List<Integer> allowed = new ArrayList<>();
    allowed.add(0);
    allowed.add(1);
    allowed.add(2);
    allowed.add(3);
    allowed.add(4);
    allowed.add(6);
    allowed.add(12);
    if (!allowed.contains(times_per_year)) {
      throw new IllegalArgumentException("invalid times per year");
    }
    this.times_per_year = times_per_year;
  }

  /**
   * <p> Gets the occurrences of the associated planned_transaction object </p>
   *
   * @return the occurrences of this planned_transaction object.
   */
  public Integer getoccurrences() {
    return occurrences;
  }

  /**
   * <p> Sets the occurrences of the associated planned_transaction object </p>
   *
   * @param occurrences the occurrences of the planned_transaction,
   *                    throws IllegalArgumentException if occurrences is negative or greater than 10,000
   */
  public void setoccurrences(Integer occurrences) {
    if (occurrences == -1) {
      this.occurrences = occurrences;
      return;
    }
    if (occurrences > 0 && occurrences < 1440) {
      this.occurrences = occurrences;
      return;

    }
    throw new IllegalArgumentException("invalid occurrences");
  }

  /**
   * <p> Gets the is_active of the associated planned_transaction object </p>
   *
   * @return the is_active of this planned_transaction object.
   */
  public boolean getis_active() {
    return is_active;
  }

  /**
   * <p> Sets the is_active of the associated planned_transaction object </p>
   *
   * @param is_active the is_active of the planned_transaction,
   *                  throws IllegalArgumentException if is_active is outside of a logical range
   */
  public void setis_active(boolean is_active) {
    this.is_active = is_active;
  }

  @Override
  public int compareTo(@NotNull Planned_Transaction o) {
    if (this.planned_transaction_ID.compareTo(o.planned_transaction_ID) < 0) {
      return -1;
    } else if (this.planned_transaction_ID.compareTo(o.planned_transaction_ID) > 0) {
      return 1;
    }
    if (this.user_ID.compareTo(o.user_ID) < 0) {
      return -1;
    } else if (this.user_ID.compareTo(o.user_ID) > 0) {
      return 1;
    }
    if (this.subcategory_ID.compareTo(o.subcategory_ID) < 0) {
      return -1;
    } else if (this.subcategory_ID.compareTo(o.subcategory_ID) > 0) {
      return 1;
    }
    if (this.budget_id.compareTo(o.budget_id) < 0) {
      return -1;
    } else if (this.budget_id.compareTo(o.budget_id) > 0) {
      return 1;
    }
    if (this.nickname.compareTo(o.nickname) < 0) {
      return -1;
    } else if (this.nickname.compareTo(o.nickname) > 0) {
      return 1;
    }
    if (this.amount.compareTo(o.amount) < 0) {
      return -1;
    } else if (this.amount.compareTo(o.amount) > 0) {
      return 1;
    }
    if (this.start_date.compareTo(o.start_date) < 0) {
      return -1;
    } else if (this.start_date.compareTo(o.start_date) > 0) {
      return 1;
    }
    if (this.times_per_year.compareTo(o.times_per_year) < 0) {
      return -1;
    } else if (this.times_per_year.compareTo(o.times_per_year) > 0) {
      return 1;
    }
    if (this.occurrences.compareTo(o.occurrences) < 0) {
      return -1;
    } else if (this.occurrences.compareTo(o.occurrences) > 0) {
      return 1;
    }
    if (!this.is_active && o.is_active) {
      return -1;
    }
    if (this.is_active && !o.is_active) {
      return 1;
    }
    return 0;
  }

}

