package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iPlanned_TransactionDAO;
import com.beck.beck_demos.budget_app.models.Planned_Transaction;
import com.beck.beck_demos.budget_app.models.Planned_Transaction_VM;
import com.beck.beck_demos.budget_app.models.SubCategory;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class Planned_TransactionDAO implements iPlanned_TransactionDAO {
  @Override
  public String add(Planned_Transaction _planned_transaction) throws SQLException {
    String generatedId = null;

    try (Connection connection = getConnection()) {
      if (connection != null) {
        // Updated call string to ensure 9 parameters match your SP
        String sql = "{CALL sp_insert_planned_transaction(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (CallableStatement statement = connection.prepareCall(sql)) {

          statement.setString(1, _planned_transaction.getuser_ID());
          statement.setString(2, _planned_transaction.getsubcategory_ID());
          statement.setString(3, _planned_transaction.getnickname());
          statement.setString(4, _planned_transaction.getbudget_id());
          statement.setDouble(5, _planned_transaction.getamount());
          statement.setDate(6, new java.sql.Date(_planned_transaction.getstart_date().getTime()));
          statement.setInt(7, _planned_transaction.gettimes_per_year());
          statement.setInt(8, _planned_transaction.getoccurrences());
          statement.setBoolean(9, _planned_transaction.getis_active());

          // CHANGE: Use executeQuery() to catch the SELECT from the SP
          try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
              generatedId = rs.getString("generated_uuid");
            }
          }

          if (generatedId == null || generatedId.isEmpty()) {
            throw new RuntimeException("Could not add planned_transaction. No ID returned.");
          }
        }
      }
    } catch (SQLException e) {
      // Log the actual error for debugging since you're nearing 400 rows and scale matters
      e.printStackTrace();
      throw new RuntimeException("Database error adding planned_transaction: " + e.getMessage());
    }

    return generatedId;
  }

  @Override
  public int update(Planned_Transaction oldplanned_transaction, Planned_Transaction newplanned_transaction) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_planned_transaction(?,?,?,?,?,?,?,?,?,?)}")) {
          statement.setString(1, oldplanned_transaction.getplanned_transaction_ID());
          statement.setString(2, oldplanned_transaction.getuser_ID());
          statement.setString(3, newplanned_transaction.getsubcategory_ID());
          statement.setString(4, newplanned_transaction.getbudget_id());
          statement.setString(5, newplanned_transaction.getnickname());
          statement.setDouble(6, newplanned_transaction.getamount());

          statement.setDate(7, new java.sql.Date(newplanned_transaction.getstart_date().getTime()));
          statement.setInt(8, newplanned_transaction.gettimes_per_year());
          statement.setInt(9, newplanned_transaction.getoccurrences());
          statement.setBoolean(10, newplanned_transaction.getis_active());
          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update planned_transaction . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public List<Planned_Transaction_VM> getAllplanned_transaction(int offset, int limit, String search, String user_ID, String subcategory_ID, String budget_ID) throws SQLException {
    List<Planned_Transaction_VM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_planned_transaction(?,?,?,?,?,?)}")) {
          statement.setInt(1, limit)
          ;
          statement.setInt(2, offset);
          statement.setString(3, search);
          statement.setString(4, user_ID);
          statement.setString(5, subcategory_ID);
          statement.setString(6, budget_ID);
          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String planned_transaction_ID = resultSet.getString("planned_transaction_planned_transaction_ID");
              String _user_ID = resultSet.getString("planned_transaction_user_ID");
              String _subcategory_ID = resultSet.getString("planned_transaction_subcategory_ID");
              String budget_id = resultSet.getString("planned_transaction_budget_id");
              String nickname = resultSet.getString("planned_transaction_nickname");
              Double amount = resultSet.getDouble("planned_transaction_amount");
              Date start_date = resultSet.getDate("planned_transaction_start_date");
              Integer times_per_year = resultSet.getInt("planned_transaction_times_per_year");
              Integer occurrences = resultSet.getInt("planned_transaction_occurrences");
              boolean is_active = resultSet.getBoolean("planned_transaction_is_active");
              String user_user_id = resultSet.getString("user_user_id");
              String user_user_name = resultSet.getString("user_user_name");
              String user_user_pw = resultSet.getString("user_user_pw");
              String user_email = resultSet.getString("user_email");
              String cat_id = resultSet.getString("sub_category_sub_category_id");
              String cat_name = resultSet.getString("sub_category_category_name");
              String color_id = resultSet.getString("sub_category_color_id");

              Planned_Transaction _planned_transaction = new Planned_Transaction(planned_transaction_ID, user_ID, _subcategory_ID, budget_id, nickname, amount, start_date, times_per_year, occurrences, is_active);
              SubCategory subCategory = new SubCategory();
              subCategory.setCategory_ID(cat_id);
              subCategory.setCategory_Name(cat_name);
              subCategory.setcolor_id(color_id);
              Planned_Transaction_VM vm = new Planned_Transaction_VM(_planned_transaction);
              vm.setsubcategory(subCategory);
              result.add(vm);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve planned_transactions. Try again later");
    }
    return result;
  }

  @Override
  public int deactivatePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_deactivate_planned_transaction( ?,?)}")) {
          statement.setString(1, _planned_transaction.getplanned_transaction_ID());
          statement.setString(2, _planned_transaction.getuser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Deactivate planned_transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Deactivate planned_transaction. Try again later");
    }
    return rowsAffected;

  }

  @Override
  public int deletePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_delete_planned_transaction( ?,?)}")) {
          statement.setString(1, _planned_transaction.getplanned_transaction_ID());
          statement.setString(2, _planned_transaction.getuser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete planned_transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete planned_transaction. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int reactivatePlanned_transaction(Planned_Transaction _planned_transaction) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_reactivate_planned_transaction( ?,?)}")) {
          statement.setString(1, _planned_transaction.getplanned_transaction_ID());
          statement.setString(2, _planned_transaction.getuser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Reactivate planned_transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Reactivate planned_transaction. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int deactivateAllPlanned_Transaction(User user) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_deactivate_planned_transaction_by_user( ?)}")) {

          statement.setString(1, user.getUser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Deactivate all planned_transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Deactivate all planned_transaction. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int reactivateAllPlanned_Transaction(User user) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_reactivate_planned_transaction_by_user( ?)}")) {

          statement.setString(1, user.getUser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Reactivate all planned_transaction. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Reactivate all planned_transaction. Try again later");
    }
    return rowsAffected;
  }
}
