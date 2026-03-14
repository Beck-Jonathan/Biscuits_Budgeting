package com.beck.beck_demos.budget_app.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 24/7/2024


import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.ParentCategory;
import com.beck.beck_demos.budget_app.models.SubCategory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class CategoryDAO implements iCategoryDAO {
  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  public int add(SubCategory _category) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_sub_category( ?,?,?,?)}")){
          statement.setString(1,_category.getParentCategoryId());
          statement.setString(2,_category.getCategory_Name());
          statement.setString(3,_category.getUser_ID());
          statement.setString(4,_category.getcolor_id());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add SubCategory. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add SubCategory. Try again later");
    }
    return numRowsAffected;
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

            String Name = resultSet.getString("subcategory_category_name");
            String User_ID = resultSet.getString("User_subCategory_User_ID");

            String Color_ID = resultSet.getString("subCategory_Color_ID");
            SubCategory _category = new SubCategory( Category_ID,ParentCategory_ID,User_ID,Name, Color_ID);
            result.add(_category);
          }
        }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Could not retrieve subCategories. Try again later");
    }
    return result;}

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
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Category(? ,?,?,?)}"))
        {
          statement.setString(1,oldCategory.getCategory_ID());
          statement.setString(2,newCategory.getParentCategoryId());
          statement.setString(3,newCategory.getCategory_Name());
          statement.setString(4,newCategory.getcolor_id());

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
        statement.setString(1, category.getCategory_ID().toString());
        statement.setString(2, category.getUser_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String subcategory_id = resultSet.getString("sub_category_sub_category_id");
            String parentCategory_ID = resultSet.getString("sub_category_parent_category_id");
            String category_name = resultSet.getString("sub_category_category_name");
            String user_id = resultSet.getString("sub_category_user_id");
            String color_id = resultSet.getString("sub_category_color_id");
            result = new SubCategory( subcategory_id,parentCategory_ID, user_id,category_name ,color_id);}
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

}

