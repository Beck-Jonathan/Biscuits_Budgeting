package com.beck.beck_demos.budget_app.models;

/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Budget_VM extends Budget {
  private User user;
  

  public Budget_VM(){}

  public Budget_VM(Budget budget){
    super(budget.getbudget_id(), budget.getuser_id(), budget.getname(), budget.getdetails(), budget.getstart_date(), budget.getlimit_amount(), budget.getcurrency_code_id(), budget.getis_active(), budget.getcreated_at(), budget.getupdated_at());
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


}

