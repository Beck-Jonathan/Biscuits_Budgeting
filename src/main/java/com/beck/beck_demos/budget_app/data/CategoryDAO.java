package com.beck.beck_demos.budget_app.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 24/7/2024


import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.Category;

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
  public int add(Category _category) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Category( ?,?,?)}")){
          statement.setString(1,_category.getCategory_Name());
          statement.setString(2,_category.getUser_ID());
          statement.setString(3,_category.getcolor_id());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Category. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Category. Try again later");
    }
    return numRowsAffected;
  }

  public List<Category> getCategoryByUser(String userID) {
    List<Category> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_user_Category(?)}")) {
          statement.setString(1,userID);
          try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            String Category_ID = resultSet.getString("Category_Category_ID");

            String Name = resultSet.getString("Category_Category_Name");
            String User_ID = resultSet.getString("User_Category_User_ID");

            String Color_ID = resultSet.getString("Category_Color_ID");
            Category _category = new Category( Category_ID,User_ID,Name, Color_ID);
            result.add(_category);
          }
        }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Could not retrieve Categorys. Try again later");
    }
    return result;}

  public int deleteCategory(String categoryID, String User_ID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Category( ?,?)}")){
          statement.setString(1,categoryID);
          statement.setString(2,User_ID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Category. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Category. Try again later");
    }
    return rowsAffected;
  }
  public int update(Category oldCategory, Category newCategory) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Category(? ,?,?)}"))
        {
          statement.setString(1,oldCategory.getCategory_ID());

          statement.setString(2,newCategory.getCategory_Name());
          statement.setString(3,newCategory.getcolor_id());

          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Category . Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public Category getCategoryByPrimaryKey(Category category) throws SQLException {
    Category result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retrieve_by_pk_category(?,?)}")) {
        statement.setString(1, category.getCategory_ID().toString());
        statement.setString(2, category.getUser_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String category_id = resultSet.getString("category_category_id");
            String category_name = resultSet.getString("category_category_name");
            String user_id = resultSet.getString("category_user_id");
            String color_id = resultSet.getString("category_color_id");
            result = new Category( category_id, user_id,category_name ,color_id);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

}

