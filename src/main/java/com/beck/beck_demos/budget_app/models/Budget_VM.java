package com.beck.beck_demos.budget_app.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Budget_VM extends Budget {
  private User user;
  private Budget_User_Line user_line;

  public Budget_VM(){}

  public Budget_VM(Budget budget){
    super(budget.getbudget_id(), budget.getuser_id(), budget.getname(), budget.getdetails(), budget.getstart_date(), budget.getlimit_amount(), budget.getcurrency_code_id(), budget.getis_active(), budget.getcreated_at(), budget.getupdated_at());
  }

  public Budget_VM(Budget budget,User user,Budget_User_Line user_line){
    super( budget.getbudget_id(),  budget.getuser_id(),  budget.getname(),  budget.getdetails(),  budget.getstart_date(),  budget.getlimit_amount(),  budget.getcurrency_code_id(),  budget.getis_active(),  budget.getcreated_at(),  budget.getupdated_at());
    this.user = user;
    this.user_line = user_line;

  }
  public Budget_VM(Budget budget,User user){
    super( budget.getbudget_id(),  budget.getuser_id(),  budget.getname(),  budget.getdetails(),  budget.getstart_date(),  budget.getlimit_amount(),  budget.getcurrency_code_id(),  budget.getis_active(),  budget.getcreated_at(),  budget.getupdated_at());
    this.user = user;

  }

  public User getuser() {
    return user;
  }
  public void setuser(User _user) {
    this.user = _user;
  }

  public Budget_User_Line getUser_line() {
    return user_line;
  }
  public void setUser_line(Budget_User_Line _user_line) {
    this.user_line = _user_line;
  }
}

