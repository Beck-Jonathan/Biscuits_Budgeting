package com.beck.beck_demos.budget_app.models;

public class Planned_Transaction_VM extends Planned_Transaction {
  private SubCategory subcategory;
  private Budget budget;

  public Planned_Transaction_VM() {
  }

  public Planned_Transaction_VM(Planned_Transaction planned_transaction) {
    super(planned_transaction.getplanned_transaction_ID(), planned_transaction.getuser_ID(), planned_transaction.getsubcategory_ID(), planned_transaction.getbudget_id(), planned_transaction.getnickname(), planned_transaction.getamount(), planned_transaction.getstart_date(), planned_transaction.gettimes_per_year(), planned_transaction.getoccurrences(), planned_transaction.getis_active());
  }

  public Planned_Transaction_VM(Planned_Transaction planned_transaction, SubCategory subcategory) {
    super(planned_transaction.getplanned_transaction_ID(), planned_transaction.getuser_ID(), planned_transaction.getsubcategory_ID(), planned_transaction.getbudget_id(), planned_transaction.getnickname(), planned_transaction.getamount(), planned_transaction.getstart_date(), planned_transaction.gettimes_per_year(), planned_transaction.getoccurrences(), planned_transaction.getis_active());
    this.subcategory = subcategory;
  }

  public SubCategory getsubcategory() {
    return subcategory;
  }

  public void setsubcategory(SubCategory _subcategory) {
    this.subcategory = _subcategory;
  }

  public Budget getBudget() {
    return budget;
  }

  public void setBudget(Budget _budget) {
    this.budget = _budget;
  }
}
