package com.beck.beck_demos.budget_app.models;
/**
 * @ author Jonathan Beck
 * @ version 1.0
 * @ since 1.0
 */

public class Budget_Line_ItemVM extends Budget_Line_Item {
  private Budget budget;
  private Transaction transaction;

  public Budget_Line_ItemVM(){}

  public Budget_Line_ItemVM(Budget_Line_Item Budget_Line_Item){
    super(Budget_Line_Item.getBudget_Line_Item_id(), Budget_Line_Item.getbudget_id(), Budget_Line_Item.getname(), Budget_Line_Item.getdetails(), Budget_Line_Item.getline_item_date(), Budget_Line_Item.getamount(), Budget_Line_Item.getbudget_line_type_id(), Budget_Line_Item.getbudget_line_status_id(), Budget_Line_Item.gettransaction_id(), Budget_Line_Item.getcreated_at(), Budget_Line_Item.getupdated_at());
  }

  public Budget_Line_ItemVM(Budget_Line_Item Budget_Line_Item,Budget budget,Transaction transaction){
    super( Budget_Line_Item.getBudget_Line_Item_id(),  Budget_Line_Item.getbudget_id(),  Budget_Line_Item.getname(),  Budget_Line_Item.getdetails(),  Budget_Line_Item.getline_item_date(),  Budget_Line_Item.getamount(),  Budget_Line_Item.getbudget_line_type_id(),  Budget_Line_Item.getbudget_line_status_id(),  Budget_Line_Item.gettransaction_id(),  Budget_Line_Item.getcreated_at(),  Budget_Line_Item.getupdated_at());
    this.budget = budget;
    this.transaction = transaction;

  }


  public Budget getbudget() {
    return budget;
  }
  public void setbudget(Budget _budget) {
    this.budget = _budget;
  }

  public Transaction gettransaction() {
    return transaction;
  }
  public void settransaction(Transaction _transaction) {
    this.transaction = _transaction;
  }

}