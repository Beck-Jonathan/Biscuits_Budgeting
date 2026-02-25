package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.Category;

import java.sql.SQLException;
import java.util.List;

public interface iCategoryDAO {

  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  int add(Category _category) throws SQLException;

   List<Category> getCategoryByUser(String userID);

   int deleteCategory(String categoryID, String User_ID) throws SQLException;

   int update(Category oldCategory, Category newCategory) throws SQLException;


}



