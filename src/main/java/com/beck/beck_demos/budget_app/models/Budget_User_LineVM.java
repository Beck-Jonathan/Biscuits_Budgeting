package com.beck.beck_demos.budget_app.models;

public class Budget_User_LineVM extends Budget_User_Line{
  private User user;
  private Budget budget;
  

  public Budget_User_LineVM(){}

  public Budget_User_LineVM(Budget_User_Line Budget_User_Line){
    super(Budget_User_Line.getBudget_User_Line_id(), Budget_User_Line.getuser_id(), Budget_User_Line.getbudget_id(), Budget_User_Line.getbudget_role_id(), Budget_User_Line.getcreated_at(), Budget_User_Line.getupdated_at());
  }

  public Budget_User_LineVM(Budget_User_Line Budget_User_Line,User user,Budget budget){
    super( Budget_User_Line.getBudget_User_Line_id(),  Budget_User_Line.getuser_id(),  Budget_User_Line.getbudget_id(),  Budget_User_Line.getbudget_role_id(),  Budget_User_Line.getcreated_at(),  Budget_User_Line.getupdated_at());
    this.user = user;
    this.budget = budget;


  }


  public User getuser() {
    return user;
  }
  public void setuser(User _user) {
    this.user = _user;
  }
  public Budget getbudget() {
    return budget;
  }
  public void setbudget(Budget _budget) {
    this.budget = _budget;
  }


}


