package com.beck.beck_demos.budget_app.iData;

import com.beck.beck_demos.budget_app.models.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jonathan
 * @version 1.5
 * @since 1.2
 */

public interface iUserDAO {
  int add(User _user) throws SQLException;
  String getUserID(String email) throws SQLException;

  User getUserByPrimaryKey(User _user) throws SQLException ;

  String get_pw(String username) throws SQLException;

  String getUserIDByUserName(String username) throws Exception;

  boolean usernameFree(String username) throws SQLException;

  boolean addDefaultCategories(String userID) throws SQLException;

  boolean emailFree(String email) throws SQLException ;

  boolean deleteUser(int userID) throws SQLException;
  boolean resetPW(User user) throws SQLException;
  List<String> getUser_Roles(User _user) throws SQLException;
  int addRole(String role, String userID);

  int yearRange(int user_ID) throws SQLException ;

}
