package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.Budget_VM;

import java.sql.SQLException;
import java.util.List;

public interface iBudgetDAO {
  /**
   * DAO Method to retrieve all Currency Codes stored in the database
   * @return List of String
   * @author Jonathan Beck
   */
  List<String> getDistinctcurrency_codeForDropdown();

  /**
   * DAO Method to add budget objects
   * @param budget the budget to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  int add(Budget budget) throws SQLException;
  /**
   * DAO Method to retrieve all budget objects
   * @return List of budgets
   * @author Jonathan Beck
   */
  List<Budget_VM> getAllbudget(int offset, int limit, String search, String user_id, String currency_code_id) throws SQLException;

  int getbudgetCount(String searchTerm, String userId, String currencyCodeId);

  int update(Budget oldbudget, Budget newbudget) throws SQLException;

  Budget_VM getBudgetByPrimaryKey(Budget budget) throws SQLException;
}
