package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Budget_Line_Item;
import com.beck.beck_demos.budget_app.models.Budget_Line_ItemVM;

import java.sql.SQLException;
import java.util.List;

public interface iBudget_Line_ItemDAO {
  List<String> getDistinctcolorForDropdown();

  List<String> getDistinctbudget_line_statusForDropdown();

  List<String> getDistinctbudget_line_typeForDropdown();

  List<Budget_Line_ItemVM> getLineItemsByBudget(String budget_ID);

  int add(Budget_Line_Item budgetLineItem) throws SQLException;

  int update(Budget_Line_Item oldbudgetLineItem, Budget_Line_Item newbudgetLineItem) throws SQLException;

  Integer deleteBudget_Line_Item(String budgetLineItemID) throws SQLException;
}
