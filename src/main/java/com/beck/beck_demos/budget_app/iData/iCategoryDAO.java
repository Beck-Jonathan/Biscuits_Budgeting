package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public interface iCategoryDAO {

  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  int add(Category _category) throws SQLException;

   List<Category> getCategoryByUser(int userID);

   int deleteCategory(String categoryID, int User_ID) throws SQLException;

   int update(Category oldCategory, Category newCategory) throws SQLException;


}



