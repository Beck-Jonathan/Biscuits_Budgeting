package com.beck.beck_demos.crrg.data_fakes;

import com.beck.beck_demos.crrg.data.User_DAO;
import com.beck.beck_demos.crrg.data_interfaces.iUser_DAO;
import com.beck.beck_demos.crrg.models.User;

import java.sql.SQLException;
import java.util.List;

public class User_DAO_Fake implements iUser_DAO {
  @Override
  public int add(User _user) throws SQLException {
    return 0;
  }

  @Override
  public List<String> getDistinctRoleForDropdown() {
    return List.of();
  }

  @Override
  public List<User> getAllUser(int limit, int offset, String Role_ID) throws SQLException {
    return List.of();
  }

  @Override
  public User getUserByUserID(User _user) throws SQLException {
    return null;
  }

  @Override
  public String getPassword(String username) throws SQLException {
    return "";
  }

  @Override
  public boolean usernameFree(String username) throws SQLException {
    return false;
  }

  @Override
  public boolean emailFree(String email) throws SQLException {
    return false;
  }

  @Override
  public boolean updatePassword(User user) throws SQLException {
    return false;
  }

  @Override
  public int changeRole(String userID, String Role) throws SQLException {
    return 0;
  }

  @Override
  public int updateLastLoggedIn(User user) throws SQLException {
    return 0;
  }

  @Override
  public int update(User oldUser, User newUser) throws SQLException {
    return 0;
  }
}
