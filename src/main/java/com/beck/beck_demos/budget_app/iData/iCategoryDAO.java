package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.*;

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

  List<ProjectionAnalysisDTO> getProjectionData(String userId, String subcatId, int histMonths, int projMonths) throws SQLException;

  /**
   * DAO Method to lock subCategory objects
   *
   * @param subCategory the subCategory to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int lockSubCategory(SubCategory subCategory) throws SQLException;

  /**
   * DAO Method to unlock subCategory objects
   *
   * @param subCategory the subCategory to be deleted
   * @return number of records deleted
   * @author Jonathan Beck
   */
  int unlockSubCategory(SubCategory subCategory) throws SQLException;

  List<CategoryPerformanceDTO> getCategoryPerformance(String userId, String subcatId, int year) throws SQLException;

  List<CategoryPerformanceDTO> getAllCategoryPerformanceByMonth(String userId, int year, int month) throws SQLException;
}



