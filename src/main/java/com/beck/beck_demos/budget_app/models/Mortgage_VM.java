package com.beck.beck_demos.budget_app.models;

import java.util.ArrayList;

public class Mortgage_VM extends Mortgage{
  private ArrayList<Month> months;
  public void setMonths(){
    int month = 1;
    double pv = this.getPresent_Value();
    months=new ArrayList<>();
    while (pv>0){
      Month month_obj = new Month(0,month,pv,super.getInterest_Rate(),super.getMonthly_Payment());
      months.add(month_obj);
      pv=month_obj.getEnding_balance();
      month++;
    }
  }
}
