package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.models.*;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class BudgetDAO implements iBudgetDAO {
  @Override
  public List<String> getDistinctcurrency_codeForDropdown() {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_currency_code()}")) {

          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String currency_code_id = resultSet.getString("currency_code_currency_code_id");

              result.add(currency_code_id);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve currency_codes. Try again later");
    }
    return result;
  }

  @Override
  public int add(Budget budget) throws SQLException {
    int numRowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_budget ( ?, ?, ?, ?, ?, ?, ?)}")) {
          statement.setString(1, budget.getuser_id());
          statement.setString(2, budget.getname());
          statement.setString(3, budget.getdetails());
          statement.setObject(4, budget.getstart_date());
          statement.setDouble(5, budget.getlimit_amount());
          statement.setString(6, budget.getcurrency_code_id());
          statement.setBoolean(7, true);
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add budget . Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add budget . Try again later");
    }
    return numRowsAffected;
  }

  @Override
  public List<Budget_VM> getAllbudget(int offset, int limit, String search, String user_id, String currency_code_id) throws SQLException {
    List<Budget_VM> result = new ArrayList<>();

    try (Connection connection = getConnection()) {
      // STEP 1: Fetch Budgets and User/Role Info
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_budget(?,?,?,?,?)}")) {
        statement.setInt(1, limit);
        statement.setInt(2, offset);
        statement.setString(3, search);
        statement.setString(4, user_id);
        statement.setString(5, currency_code_id);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            Budget _budget = new Budget(
                resultSet.getString("budget_budget_id"),
                resultSet.getString("budget_user_id"),
                resultSet.getString("budget_name"),
                resultSet.getString("budget_details"),
                resultSet.getObject("budget_start_date", LocalDate.class),
                resultSet.getDouble("budget_limit_amount"),
                resultSet.getString("budget_currency_code_id"),
                resultSet.getBoolean("budget_is_active"),
                resultSet.getObject("budget_created_at", LocalDate.class),
                resultSet.getObject("budget_updated_at", LocalDate.class)
            );

            Budget_User_Line line = new Budget_User_Line(
                resultSet.getString("budget_user_line_budget_user_line_id"),
                resultSet.getString("budget_user_line_user_id"),
                resultSet.getString("budget_user_line_budget_id"),
                resultSet.getString("budget_user_line_budget_role_id"),
                resultSet.getObject("budget_user_line_created_at", LocalDate.class),
                resultSet.getObject("budget_user_line_updated_at", LocalDate.class)
            );

            Budget_VM vm = new Budget_VM(_budget);
            vm.setUser_line(line);
            vm.setLines(new ArrayList<>()); // Initialize to avoid nulls
            result.add(vm);
          }
        }
      }

      // STEP 2: Batch Load Line Items for all retrieved Budgets
      if (!result.isEmpty()) {
        // 1. Map for lookup
        Map<String, Budget_VM> vmMap = result.stream()
            .collect(Collectors.toMap(Budget::getbudget_id, vm -> vm));

        // 2. Convert list of IDs to a single comma-separated String
        String commaSeparatedIds = result.stream()
            .map(Budget::getbudget_id)
            .collect(Collectors.joining(","));

        // 3. Call the Stored Procedure
        try (CallableStatement lineStmt = connection.prepareCall("{CALL sp_retrieve_line_items_batch(?)}")) {
          lineStmt.setString(1, commaSeparatedIds);

          try (ResultSet rsLines = lineStmt.executeQuery()) {
            while (rsLines.next()) {
              String bId = rsLines.getString("budget_id");

              // Create the Line Item Object
              Budget_Line_ItemVM item = new Budget_Line_ItemVM();
              item.setBudget_Line_Item_id(rsLines.getString("budget_line_item_budget_line_item_id"));
              item.setamount(rsLines.getDouble("budget_line_item_amount"));
              item.setname(rsLines.getString("budget_line_item_name"));
              item.setline_item_date(rsLines.getObject("budget_line_item_line_item_date", LocalDate.class));
              item.setbudget_line_type_id(rsLines.getString("budget_line_item_budget_line_type_id"));
              item.setbudget_line_status_id(rsLines.getString("budget_line_item_budget_line_status_id"));

              SubCategory category = new SubCategory();
              item.setCategory(category);
              category.setCategory_ID(rsLines.getString("category_category_id"));
              category.setCategory_Name(rsLines.getString("category_category_name"));
              category.setcolor_id(rsLines.getString("category_color_id"));

              // Add to the correct VM using the map
              if (vmMap.containsKey(bId)) {
                vmMap.get(bId).getLines().add(item);
              }
            }
          }

        }
      }
    } catch (SQLException | ParseException e) {
      // Log the error for debugging
      e.printStackTrace();
      throw new RuntimeException("Could not retrieve budgets. Try again later");
    }
    return result;
  }

  @Override
  public int getbudgetCount(String searchTerm, String userId, String currencyCodeId) {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_count_by_all_budget(?,?,?)}")) {
          statement.setString(1, searchTerm);
          statement.setString(2, userId);
          statement.setString(3, currencyCodeId);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              result = resultSet.getInt(1);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public int update(Budget oldbudget, Budget newbudget) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_budget(? ,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
          statement.setString(1, oldbudget.getbudget_id());
          statement.setString(2, oldbudget.getuser_id());

          statement.setString(3, oldbudget.getname());
          statement.setString(4, newbudget.getname());
          statement.setString(5, oldbudget.getdetails());
          statement.setString(6, newbudget.getdetails());
          statement.setObject(7, oldbudget.getstart_date());
          statement.setObject(8, newbudget.getstart_date());
          statement.setDouble(9, oldbudget.getlimit_amount());
          statement.setDouble(10, newbudget.getlimit_amount());
          statement.setString(11, oldbudget.getcurrency_code_id());
          statement.setString(12, newbudget.getcurrency_code_id());
          statement.setBoolean(13, oldbudget.getis_active());
          statement.setBoolean(14, newbudget.getis_active());

          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update budget . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public Budget_VM getBudgetByPrimaryKey(Budget budget) throws SQLException {
    Budget result = null;
    Budget_VM vm = null;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_budget(?)}")) {
        statement.setString(1, budget.getbudget_id());

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            String budget_id = resultSet.getString("budget_budget_id");
            String user_id = resultSet.getString("budget_user_id");
            String name = resultSet.getString("budget_name");
            String details = resultSet.getString("budget_details");
            LocalDate start_date = resultSet.getObject("budget_start_date", LocalDate.class);
            Double limit_amount = resultSet.getDouble("budget_limit_amount");
            String currency_code_id = resultSet.getString("budget_currency_code_id");
            boolean is_active = resultSet.getBoolean("budget_is_active");
            LocalDate created_at = resultSet.getObject("budget_created_at", LocalDate.class);
            LocalDate updated_at = resultSet.getObject("budget_updated_at", LocalDate.class);
            String user_user_id = resultSet.getString("user_user_id");
            String user_user_name = resultSet.getString("user_user_name");
            String user_user_pw = resultSet.getString("user_user_pw");
            String user_email = resultSet.getString("user_email");
            String currency_code_currency_code_id = resultSet.getString("currency_code_currency_code_id");
            result = new Budget(budget_id, user_id, name, details, start_date, limit_amount, currency_code_id, is_active, created_at, updated_at);
          }
          if (result != null) {
            vm = new Budget_VM(result);
          }

        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return vm;
  }

  /**
   * DAO Method to delete budget objects
   *
   * @param budget_id the budget to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  @Override
  public int deletebudget(String budget_id) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_budget( ?)}")) {
          statement.setString(1, budget_id);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete budget. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete budget. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int deactivateBudget(Budget budget) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_deactivate_budget( ?,?)}")) {
          statement.setString(1, budget.getbudget_id());
          statement.setString(2, budget.getuser_id());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Deactivate budget. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Deactivate budget. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int activateBudget(Budget budget) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_activate_budget( ?,?)}")) {
          statement.setString(1, budget.getbudget_id());
          statement.setString(2, budget.getuser_id());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Activate budget. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Activate budget. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public List<Budget_VM> getAllActiveBudgetsWithLines(String user_id) {
    List<Budget_VM> result = new ArrayList<>();

    try (Connection connection = getConnection()) {
      // STEP 1: Fetch Active Budgets for this user
      // We'll use a specific stored procedure for active ones,
      // or you can reuse sp_retrieve_by_all_budget with specific flags if it supports it.
      String sql = "{CALL sp_retrieve_active_budgets_active(?)}";
      try (CallableStatement statement = connection.prepareCall(sql)) {
        statement.setString(1, user_id);

        try (ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            Budget _budget = new Budget();
            _budget.setbudget_id(resultSet.getString("budget_budget_id"));
            _budget.setuser_id(resultSet.getString("budget_user_id"));
            _budget.setname(resultSet.getString("budget_name"));
            _budget.setdetails(resultSet.getString("budget_details"));
            _budget.setstart_date(resultSet.getObject("budget_start_date", LocalDate.class));
            _budget.setlimit_amount(resultSet.getDouble("budget_limit_amount"));
            _budget.setis_active(resultSet.getBoolean("budget_is_active"));

            Budget_VM vm = new Budget_VM(_budget);
            vm.setLines(new ArrayList<>()); // Crucial for your getTotalSpent() logic
            result.add(vm);
          }
        }
      }

      // STEP 2: Batch Load Line Items for all active Budgets
      if (!result.isEmpty()) {
        // Map for quick lookup by ID
        Map<String, Budget_VM> vmMap = result.stream()
            .collect(Collectors.toMap(Budget::getbudget_id, vm -> vm));

        // Create comma-separated string for the batch SP
        String commaSeparatedIds = result.stream()
            .map(Budget::getbudget_id)
            .collect(Collectors.joining(","));

        try (CallableStatement lineStmt = connection.prepareCall("{CALL sp_retrieve_line_items_batch_active(?)}")) {
          lineStmt.setString(1, commaSeparatedIds);

          try (ResultSet rsLines = lineStmt.executeQuery()) {
            while (rsLines.next()) {
              String bId = rsLines.getString("budget_id");

              // Create the Line Item VM
              Budget_Line_ItemVM item = new Budget_Line_ItemVM();
              item.setBudget_Line_Item_id(rsLines.getString("budget_line_item_budget_line_item_id"));
              item.setamount(rsLines.getDouble("budget_line_item_amount"));
              item.setname(rsLines.getString("budget_line_item_name"));
              item.setline_item_date(rsLines.getObject("budget_line_item_line_item_date", LocalDate.class));
              item.setbudget_line_status_id(rsLines.getString("budget_line_item_budget_line_status_id"));

              // SubCategory for the progress bar color
              // Inside the rsLines.next() loop
              String catId = rsLines.getString("category_category_id");
              if (catId != null) {
                SubCategory category = new SubCategory();
                category.setCategory_ID(catId);
                category.setCategory_Name(rsLines.getString("category_category_name"));
                category.setcolor_id(rsLines.getString("category_color_id"));
                item.setCategory(category);
                item.setCategory_id(catId); // Synchronize the ID string in the parent class
              } else {

                item.setCategory_id("00000000-0000-0000-0000-000000000000"); // Or a default UUID
              }

              // Attach to the parent VM
              if (vmMap.containsKey(bId)) {
                vmMap.get(bId).getLines().add(item);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Error calculating project affordability: " + e.getMessage());
    }
    return result;
  }

  @Override
  public Budget getBudgetOverviewBy_ID(String budgetID, String userId) throws SQLException {
    Budget result = new Budget();

    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_getBudgetOverviewBy_ID(?,?)}")) {
        statement.setString(1, budgetID);
        statement.setString(2, userId);
        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            LocalDate StartDate = resultSet.getObject("budget_start_date", LocalDate.class);
            Double amount = resultSet.getDouble("budgeT_total");
            result.setstart_date(StartDate);
            result.setlimit_amount(amount);

          }

        } catch (SQLException | ParseException e) {
          throw new RuntimeException(e);
        }
      }

    }
    return result;
  }
}
