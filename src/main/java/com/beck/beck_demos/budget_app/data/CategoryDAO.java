package com.beck.beck_demos.budget_app.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 24/7/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines CategoryDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-ddcedar
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.budget_app.iData.iCategoryDAO;
import com.beck.beck_demos.budget_app.models.Category;
import com.beck.beck_demos.budget_app.models.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.budget_app.data.Database.getConnection;
public class CategoryDAO implements iCategoryDAO {
  /**
   * @param _category The category to be added to the database
   * @return number of rows effected
   */
  public int add(Category _category) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Category( ?,?)}")){
          statement.setString(1,_category.getCategory_ID());
          statement.setInt(2,_category.getUser_ID());
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

  public List<Category> getCategoryByUser(int userID) {
    List<Category> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_user_Category(?)}")) {
          statement.setInt(1,userID);
          try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {String Category_ID = resultSet.getString("Category_Category_ID");
            Category _category = new Category( Category_ID,userID);
            result.add(_category);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Categorys. Try again later");
    }
    return result;}

  public int deleteCategory(String categoryID, int User_ID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Category( ?,?)}")){
          statement.setString(1,categoryID);
          statement.setInt(2,User_ID);
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
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Category(? ,?,? )}"))
        {
          statement.setString(1,oldCategory.getCategory_ID());
          statement.setString(2,newCategory.getCategory_ID());
          statement.setInt(3,oldCategory.getUser_ID());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Category . Try again later");
        }
      }
    }
    return result;
  }


}

