package com.beck.beck_demos.budget_app.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 24/7/2024


import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.*;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class CategoryDAO implements iCategoryDAO {
  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  public String add(SubCategory _category) {
    String newUUID = "0"; // Default failure state

    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_sub_category(?,?,?,?,?)}")) {
          statement.setString(1, _category.getParentCategoryId());
          statement.setString(2, _category.getCategory_Name());
          statement.setString(3, _category.getUser_ID());
          statement.setString(4, _category.getcolor_id());
          statement.setString(5, _category.getprojection_strategy_ID());

          // We use executeQuery because the SP returns a result set containing the UUID
          try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
              newUUID = rs.getString(1);
              // Also update the model object just in case it's needed elsewhere
              try {
                _category.setCategory_ID(newUUID);
              } catch (Exception e) {
                return "-10"; // Return the Database Error code directly
              }
            }
          }
        }
      }
    } catch (Exception e) {
      System.err.println("SQL Error in SubCategory.add: " + e.getMessage());
      return "-10"; // Return the Database Error code directly
    }

    return newUUID;
  }

  public List<SubCategory> getsubCategoryByUser(String userID) {
    List<SubCategory> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_user_subCategory(?)}")) {
          statement.setString(1,userID);
          try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Category_ID = resultSet.getString("subcategory_category_id");
            String ParentCategory_ID = resultSet.getString("subcategory_parent_category_id");
            String projectionStrategy = resultSet.getString("sub_category_projection_strategy_ID");


            String Name = resultSet.getString("subcategory_category_name");
            String User_ID = resultSet.getString("User_subCategory_User_ID");

            String Color_ID = resultSet.getString("subCategory_Color_ID");
            BigDecimal threshhold = resultSet.getBigDecimal("subCategory_target_threshold");
            boolean is_Active = resultSet.getBoolean("subCategory_is_locked");
            SubCategory _category = new SubCategory(Category_ID, ParentCategory_ID, projectionStrategy, User_ID, Name, Color_ID, threshhold, is_Active);
            result.add(_category);
          }
        }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Could not retrieve subCategories. Try again later");
    }
    return result;
  }

  public int deleteSubCategory(String categoryID, String User_ID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_subCategory( ?,?)}")){
          statement.setString(1,categoryID);
          statement.setString(2,User_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete subCategory. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete subCategory. Try again later");
    }
    return rowsAffected;
  }
  public int update(SubCategory oldCategory, SubCategory newCategory) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_subcategory(? ,?,?,?,?)}"))
        {
          statement.setString(1,oldCategory.getCategory_ID());
          statement.setString(2,newCategory.getParentCategoryId());
          statement.setString(3, newCategory.getprojection_strategy_ID());
          statement.setString(4, newCategory.getCategory_Name());
          statement.setString(5, newCategory.getcolor_id());

          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update SubCategory . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public SubCategory getCategoryByPrimaryKey(SubCategory category) throws SQLException {
    SubCategory result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_subcategory(?,?)}")) {
        statement.setString(1, category.getCategory_ID());
        statement.setString(2, category.getUser_ID());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String subcategory_id = resultSet.getString("sub_category_sub_category_id");
            String parentCategory_ID = resultSet.getString("sub_category_parent_category_id");
            String category_name = resultSet.getString("sub_category_category_name");
            String projectionStrategyId = resultSet.getString("sub_category_projection_strategy_ID");

            String user_id = resultSet.getString("sub_category_user_id");
            String color_id = resultSet.getString("sub_category_color_id");
            BigDecimal targetThreshold = resultSet.getBigDecimal("subCategory_target_threshold");
            boolean isLocked = resultSet.getBoolean("subCategory_is_locked");
            result = new SubCategory(subcategory_id, parentCategory_ID, projectionStrategyId, user_id, category_name, color_id, targetThreshold, isLocked);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  @Override
  public List<ParentCategory> getParentCategoryByUser(String userID) throws SQLException {
    List<ParentCategory> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_parent_category(?)}")) {

          statement.setString(1,userID);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String parent_category_id = resultSet.getString("parent_category_parent_category_id");
              String super_category_name = resultSet.getString("parent_category_super_category_name");
              String user_id = resultSet.getString("parent_category_user_id");
              String color_id = resultSet.getString("parent_category_color_id");
              String transaction_type = resultSet.getString("parent_category_transaction_type");
              ParentCategory _parent_category = new ParentCategory( parent_category_id, super_category_name, user_id, color_id,transaction_type);
              result.add(_parent_category);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve parent_categorys. Try again later");
    }
    return result;
  }

  @Override
  public int SmartAssignProjectionModel(User user) throws SQLException {
    int rowsAffected = 0;
    // Use a string for the SQL call
    String query = "{CALL auto_assign_projection_strategies(?)}";

    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall(query)) {
          statement.setString(1, user.getUser_ID());

          // Since the SP ends with a SELECT, we use executeQuery()
          try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
              // Match the alias "categories_updated" from your SQL SELECT
              rowsAffected = rs.getInt("categories_updated");
            }
          }
        }
      }
    } catch (SQLException e) {
      // Log the actual error internally before throwing the user-friendly one
      throw new SQLException("Can not Updated Projections");
    }
    return rowsAffected;
  }

  @Override
  public List<ProjectionAnalysisDTO> getProjectionData(String userId, String subcatId, int histMonths, int projMonths) {
    List<ProjectionAnalysisDTO> report = new ArrayList<>();
    String query = "{CALL proc_get_projection_analysis(?, ?, ?, ?)}";

    try (Connection conn = getConnection();
         CallableStatement stmt = conn.prepareCall(query)) {

      stmt.setString(1, userId);
      stmt.setString(2, subcatId);
      stmt.setInt(3, histMonths);
      stmt.setInt(4, projMonths);

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          ProjectionAnalysisDTO row = new ProjectionAnalysisDTO();
          row.setMonthDate(rs.getString("m_date"));

          // Use getObject() and cast to handle SQL NULLs gracefully in Java

          row.setHistoricalTruth(readDouble(rs, "historicalTruth"));
          row.setRegression(readDouble(rs, "regression"));
          row.setAvgStrict(readDouble(rs, "avgStrict"));
          row.setLvcf(readDouble(rs, "lvcf"));
          row.setInflation(readDouble(rs, "inflation"));
          row.setAlphaSpike(readDouble(rs, "alphaSpike"));
          row.setZeroSum(readDouble(rs, "zeroSum"));

          report.add(row);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      // Log error specifically for the jjbeck budget engine
    }
    return report;
  }

  @Override
  public int lockSubCategory(SubCategory subCategory) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_lock_subcategory( ?,?)}")) {
          statement.setString(1, subCategory.getCategory_ID());
          statement.setString(2, subCategory.getUser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not lock subcategory. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not lock subcategory. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public int unlockSubCategory(SubCategory subCategory) throws SQLException {
    int rowsAffected = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unlock_subcategory( ?,?)}")) {
          statement.setString(1, subCategory.getCategory_ID());
          statement.setString(2, subCategory.getUser_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not unlock subcategory. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not unlock subcategory. Try again later");
    }
    return rowsAffected;
  }

  @Override
  public List<CategoryPerformanceDTO> getCategoryPerformance(String userId, String subcatId, int year) throws SQLException {
    List<CategoryPerformanceDTO> result = new ArrayList<>();

    // 1. Establish connection using your existing helper
    try (Connection connection = getConnection()) {
      if (connection != null) {
        // 2. Prepare the call to the performance stored procedure
        // Parameters: UserID, SubCategoryID, TargetYear
        try (CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_category_performance(?, ?, ?)}")) {
          statement.setString(1, userId);
          statement.setString(2, subcatId);
          statement.setInt(3, year);

          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              // 3. Map the DB columns to the DTO
              // Assuming your SP returns columns named: "period", "budgeted", and "actual"
              String period = resultSet.getString("period");

              double budgeted = resultSet.getDouble("budgeted");
              double actual = resultSet.getBigDecimal("actual").doubleValue();
              double threshold = resultSet.getBigDecimal("threshold").doubleValue();

              CategoryPerformanceDTO dto = new CategoryPerformanceDTO(period, budgeted, actual, threshold);
              dto.setTransactionType(resultSet.getString("transaction_type"));
              result.add(dto);
            }
          }
        }
      }
    } catch (SQLException e) {
      // Log the error and rethrow to be caught by the Servlet's -2 error handler
      throw new SQLException("Error retrieving category performance for subcat: " + subcatId, e);
    }

    return result;
  }

  @Override
  public List<CategoryPerformanceDTO> getAllCategoryPerformanceByMonth(String userId, int year, int month) throws SQLException {
    List<CategoryPerformanceDTO> result = new ArrayList<>();
    int mode = 0;
    // Handle the -1 (Annual) case by defaulting to January 1st
    int effectiveMonth = (month == -1) ? 1 : month;
    if (month == -1) {
      mode = 1;
    }
    // Use LocalDate to avoid formatting headaches
    java.time.LocalDate localDate = java.time.LocalDate.of(year, effectiveMonth, 1);
    java.sql.Date targetDate = java.sql.Date.valueOf(localDate);

    try (Connection connection = getConnection()) {
      if (connection != null) {
        // Updated call: UserID, TargetDate, Mode (0 = Month)
        try (CallableStatement statement = connection.prepareCall("{CALL proc_get_single_month_forecast_all_category(?, ?, ?)}")) {
          statement.setString(1, userId);
          statement.setDate(2, targetDate);
          statement.setInt(3, mode); // Mode 0 for Single Month

          try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              CategoryPerformanceDTO dto = new CategoryPerformanceDTO();

              // Map the specific columns returned by FinalUserForecast
              dto.setCategoryID(resultSet.getString("subcategory_id"));
              dto.setCategoryName(resultSet.getString("category_name"));
              dto.setTransactionType(resultSet.getString("transaction_type"));
              // In "All Category" mode, period is usually displayed as the month/year searched
              dto.setPeriod(String.format("%02d/%d", effectiveMonth, year));

              // Numeric mapping
              dto.setBudgetedValue(resultSet.getDouble("projected_amount"));
              dto.setActualValue(resultSet.getDouble("total_actual"));

              // For the Global view, threshold is often treated as the projected_amount
              // but you can adjust if your DB schema has a separate hard limit.
              dto.setThreshold(resultSet.getDouble("projected_amount"));

              result.add(dto);
            }
          }
        }
      }
    } catch (Exception e) {
      throw new SQLException("Error in Global Month Performance fetch for user: " + userId, e);
    }
    Collections.sort(result);
    return result;
  }

  private Double readDouble(ResultSet rs, String columnLabel) throws SQLException {
    // 1. Get the object from the result set
    Object value = rs.getObject(columnLabel);

    // 2. Check for SQL NULL
    if (value == null) {
      return null;
    }

    // 3. Handle BigDecimal to Double conversion
    if (value instanceof java.math.BigDecimal) {
      return ((java.math.BigDecimal) value).doubleValue();
    }

    // 4. Fallback for other numeric types (Long, Integer, etc.)
    if (value instanceof Number) {
      return ((Number) value).doubleValue();
    }

    return null;
  }
}

