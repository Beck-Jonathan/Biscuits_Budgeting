package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iBudget_Line_ItemDAO;
import com.beck.beck_demos.budget_app.models.Budget_Line_Item;
import com.beck.beck_demos.budget_app.models.Budget_Line_ItemVM;
import com.beck.beck_demos.budget_app.models.SubCategory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class Budget_Line_ItemDAO implements iBudget_Line_ItemDAO {
  @Override
  public List<String> getDistinctcolorForDropdown() {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_color()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String color_ID = resultSet.getString("color_color_id");

              result.add(color_ID);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve color_codes. Try again later");
    }
    return result;
  }

  @Override
  public List<String> getDistinctbudget_line_statusForDropdown() {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_budget_line_status()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String status_id = resultSet.getString("budget_line_status_budget_line_status_id");

              result.add(status_id);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve color_codes. Try again later");
    }
    return result;
  }

  @Override
  public List<String> getDistinctbudget_line_typeForDropdown() {
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_all_budget_line_type()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String type_id = resultSet.getString("budget_line_type_budget_line_type_id");

              result.add(type_id);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve color_codes. Try again later");
    }
    return result;
  }

  @Override
  public List<Budget_Line_ItemVM> getLineItemsByBudget(String budget_ID) {
    List<Budget_Line_ItemVM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_budget_line_item_by_budget(?)}")) {
          statement.setString(1,budget_ID);

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String budget_line_item_id = resultSet.getString("budget_line_item_budget_line_item_id");
              String budget_id = resultSet.getString("budget_line_item_budget_id");
              String category_id = resultSet.getString("budget_line_item_category_id");
              String name = resultSet.getString("budget_line_item_name");
              String details = resultSet.getString("budget_line_item_details");
              LocalDate line_item_date = resultSet.getObject("budget_line_item_line_item_date", LocalDate.class);
              Double amount = resultSet.getDouble("budget_line_item_amount");
              String budget_line_type_id = resultSet.getString("budget_line_item_budget_line_type_id");
              String budget_line_status_id = resultSet.getString("budget_line_item_budget_line_status_id");
              String transaction_id = resultSet.getString("budget_line_item_transaction_id");
              if(resultSet.wasNull()){
                transaction_id="";}
              LocalDate created_at = resultSet.getObject("budget_line_item_created_at", LocalDate.class);
              LocalDate updated_at = resultSet.getObject("budget_line_item_updated_at", LocalDate.class);
              String subcategory_category_id = resultSet.getString("subcategory_subcategory_id");
              String subcategory_parent_category_id = resultSet.getString("subcategory_parent_category_id");

              String subcategory_category_name = resultSet.getString("subcategory_category_name");
              String subcategory_user_id = resultSet.getString("subcategory_user_id");
              String subcategory_color_id = resultSet.getString("subcategory_color_id");
              SubCategory category = new SubCategory(subcategory_category_id, subcategory_parent_category_id, "", subcategory_user_id, subcategory_category_name, subcategory_color_id);
              Budget_Line_Item budget_line_item =  new Budget_Line_Item( budget_line_item_id, budget_id, category_id, name, details, line_item_date, amount, budget_line_type_id, budget_line_status_id, transaction_id, created_at, updated_at);
              Budget_Line_ItemVM vm = new Budget_Line_ItemVM(budget_line_item);
              vm.setCategory(category);
              result.add(vm);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve budget_line_items. Try again later");
    }
    return result;
  }

  @Override
  public int add(Budget_Line_Item _budget_line_item) throws SQLException {
    String newUuid = null;
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        // Updated to use executeQuery since the SP returns a result set (the ID)
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_budget_line_item(?, ?, ?, ?, ?, ?, ?, ?)}")) {
          statement.setString(1, _budget_line_item.getbudget_id());
          statement.setString(2, _budget_line_item.getCategory_id());
          statement.setString(3, _budget_line_item.getname());
          statement.setString(4, _budget_line_item.getdetails());
          statement.setObject(5, _budget_line_item.getline_item_date());
          statement.setDouble(6, _budget_line_item.getamount());
          statement.setString(7, _budget_line_item.getbudget_line_type_id());
          statement.setString(8, _budget_line_item.getbudget_line_status_id());

          try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
              // Assuming the SP returns the ID in the first column

              newUuid = resultSet.getString(1);
              if (newUuid!=null){
                result = 1;
                _budget_line_item.setBudget_Line_Item_id(newUuid);
              }


            }
          }

          if (newUuid == null) {
            throw new RuntimeException("Could not add budget_line_item. No ID returned.");
          }
        }
      }
    } catch (SQLException e) {
      // Log the actual error for your own debugging
      e.printStackTrace();
      throw new RuntimeException("Could not add budget_line_item. Try again later");
    }
    return result;
  }

  /**
   * DAO Method to update budget_line_item objects
   * @param oldbudget_line_item the budget_line_item to be updated
   * @param newbudget_line_item the updated version of the budget_line_item
   * @return number of records updated
   * @author Jonathan Beck
   */

  public int update(Budget_Line_Item oldbudget_line_item, Budget_Line_Item newbudget_line_item) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_budget_line_item(?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldbudget_line_item.getBudget_Line_Item_id());
          statement.setString(2,newbudget_line_item.getCategory_id());
          statement.setString(3,newbudget_line_item.getname());
          statement.setString(4,newbudget_line_item.getdetails());
          statement.setObject(5,newbudget_line_item.getline_item_date());
          statement.setDouble(6,newbudget_line_item.getamount());
          statement.setString(7,newbudget_line_item.getbudget_line_type_id());
          statement.setString(8,newbudget_line_item.getbudget_line_status_id());

          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update budget_line_item . Try again later");
        }
      }
    }
    return result;
  }
  /**
   * DAO Method to delete budget_line_item objects
   * @param budgetLineItemID the budget_line_item to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  @Override
  public Integer deleteBudget_Line_Item(String budgetLineItemID) throws SQLException {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_delete_budget_line_item( ?)}")){
          statement.setString(1,budgetLineItemID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete budget_line_item. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete budget_line_item. Try again later");
    }
    return rowsAffected;
  }
}
