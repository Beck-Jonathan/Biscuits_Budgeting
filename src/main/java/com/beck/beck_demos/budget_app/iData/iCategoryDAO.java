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

  int add(Category _category, int user_ID) ;

   List<Category> getCategoryByUser(int userID);

   int deleteCategory(String categoryID, int User_ID);

   int update(Category oldCategory, Category newCategory, User user) throws SQLException;


}



