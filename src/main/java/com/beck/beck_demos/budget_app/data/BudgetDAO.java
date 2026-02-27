package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iBudgetDAO;
import com.beck.beck_demos.budget_app.models.Budget;
import com.beck.beck_demos.budget_app.models.Budget_User_Line;
import com.beck.beck_demos.budget_app.models.Budget_VM;

import java.sql.*;
import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class BudgetDAO implements iBudgetDAO {
  @Override
  public List<String> getDistinctcurrency_codeForDropdown() {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_currency_code()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
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
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_budget ( ?, ?, ?, ?, ?, ?, ?)}")){
          statement.setString(1,budget.getuser_id());
          statement.setString(2,budget.getname());
          statement.setString(3,budget.getdetails());
          statement.setObject(4,budget.getstart_date());
          statement.setDouble(5,budget.getlimit_amount());
          statement.setString(6,budget.getcurrency_code_id());
          statement.setBoolean(7,true);
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
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_budget(?,?,?,?,?)}")) {
          statement.setInt(1,limit)
          ;statement.setInt(2,offset);
          statement.setString(3,search);
          statement.setString(4,user_id);
          statement.setString(5,currency_code_id);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String budget_id = resultSet.getString("budget_budget_id");
              String _user_id = resultSet.getString("budget_user_id");
              String name = resultSet.getString("budget_name");
              String details = resultSet.getString("budget_details");
              LocalDate start_date = resultSet.getObject("budget_start_date", LocalDate.class);
              Double limit_amount = resultSet.getDouble("budget_limit_amount");
              String _currency_code_id = resultSet.getString("budget_currency_code_id");
              boolean is_active = resultSet.getBoolean("budget_is_active");
              LocalDate created_at = resultSet.getObject("budget_created_at", LocalDate.class);


              LocalDate updated_at = resultSet.getObject("budget_updated_at", LocalDate.class);


              String  user_user_id = resultSet.getString("user_user_id");
              String user_user_name = resultSet.getString("user_user_name");
              String user_user_pw = resultSet.getString("user_user_pw");
              String user_email = resultSet.getString("user_email");
              String currency_code_currency_code_id = resultSet.getString("currency_code_currency_code_id");
              String budget_user_line_id = resultSet.getString("budget_user_line_budget_user_line_id");
              String line_user_id = resultSet.getString("budget_user_line_user_id");
              String line_budget_id = resultSet.getString("budget_user_line_budget_id");
              String line_budget_role_id = resultSet.getString("budget_user_line_budget_role_id");
              LocalDate line_created_at = resultSet.getObject("budget_user_line_created_at", LocalDate.class);
              LocalDate line_updated_at = resultSet.getObject("budget_user_line_updated_at", LocalDate.class);
              Budget _budget = new Budget( budget_id, _user_id, name, details, start_date, limit_amount, _currency_code_id, is_active, created_at, updated_at);
              Budget_User_Line line = new Budget_User_Line( budget_user_line_id, line_user_id, line_budget_id, line_budget_role_id, line_created_at, line_updated_at);
              Budget_VM vm = new Budget_VM(_budget);
              vm.setUser_line(line);
              result.add(vm);
            }
          }
        }
      }
    } catch (SQLException e) {
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
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_budget(? ,?,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldbudget.getbudget_id());
          statement.setString(2,oldbudget.getuser_id());

          statement.setString(3,oldbudget.getname());
          statement.setString(4,newbudget.getname());
          statement.setString(5,oldbudget.getdetails());
          statement.setString(6,newbudget.getdetails());
          statement.setObject(7,oldbudget.getstart_date());
          statement.setObject(8,newbudget.getstart_date());
          statement.setDouble(9,oldbudget.getlimit_amount());
          statement.setDouble(10,newbudget.getlimit_amount());
          statement.setString(11,oldbudget.getcurrency_code_id());
          statement.setString(12,newbudget.getcurrency_code_id());
          statement.setBoolean(13,oldbudget.getis_active());
          statement.setBoolean(14,newbudget.getis_active());

          result=statement.executeUpdate();
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
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_budget(?)}")) {
        statement.setString(1, budget.getbudget_id().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String budget_id = resultSet.getString("budget_budget_id");
            String user_id = resultSet.getString("budget_user_id");
            String name = resultSet.getString("budget_name");
            String details = resultSet.getString("budget_details");
            LocalDate start_date = resultSet.getObject("budget_start_date",LocalDate.class);
            Double limit_amount = resultSet.getDouble("budget_limit_amount");
            String currency_code_id = resultSet.getString("budget_currency_code_id");
            boolean is_active = resultSet.getBoolean("budget_is_active");
            LocalDate created_at = resultSet.getObject("budget_created_at",LocalDate.class);
            LocalDate updated_at = resultSet.getObject("budget_updated_at",LocalDate.class);
            String user_user_id = resultSet.getString("user_user_id");
            String user_user_name = resultSet.getString("user_user_name");
            String user_user_pw = resultSet.getString("user_user_pw");
            String user_email = resultSet.getString("user_email");
            String currency_code_currency_code_id = resultSet.getString("currency_code_currency_code_id");
            result = new Budget( budget_id, user_id, name, details, start_date, limit_amount, currency_code_id, is_active, created_at, updated_at);}
          if (result !=null){
            vm = new Budget_VM(result);
          }

        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return vm;
  }
}
