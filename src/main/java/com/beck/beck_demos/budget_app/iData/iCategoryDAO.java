package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.ParentCategory;
import com.beck.beck_demos.budget_app.models.SubCategory;
import com.beck.beck_demos.budget_app.models.User;

import java.sql.SQLException;
import java.util.List;

public interface iCategoryDAO {

  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  int add(SubCategory _category) throws SQLException;

   List<SubCategory> getsubCategoryByUser(String userID);

   int deleteSubCategory(String categoryID, String User_ID) throws SQLException;

   int update(SubCategory oldCategory, SubCategory newCategory) throws SQLException;

  SubCategory getCategoryByPrimaryKey(SubCategory category) throws SQLException;

   List<ParentCategory> getParentCategoryByUser(String userID) throws SQLException;

  int SmartAssignProjectionModel(User user) throws SQLException;

}



