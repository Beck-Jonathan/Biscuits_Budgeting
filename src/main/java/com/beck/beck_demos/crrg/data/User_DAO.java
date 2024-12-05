package com.beck.beck_demos.crrg.data;

import com.beck.beck_demos.crrg.data_interfaces.iUser_DAO;
import com.beck.beck_demos.crrg.models.User;

import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.beck.beck_demos.crrg.data.Database.getConnection;

public class User_DAO implements iUser_DAO {
//  @Override
  /**
   * DAO Method to add User objects
   * @param _user the User to be added
   * @return number of records added
   * @author Jonathan Beck
   */
  public int add(User _user) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_User( ?, ?, ?, ?, ?, ?)}")){
          statement.setString(1,_user.getUser_ID());
          statement.setString(2,_user.getRole_ID());
          statement.setString(3,_user.getFirst_Name());
          statement.setString(4,_user.getLast_Name());
          statement.setString(5,_user.getEmail());
          String hashPassword = BCrypt.hashpw(String.valueOf(_user.getPassword()), BCrypt.gensalt(12));
          statement.setString(6,hashPassword);

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add User. Try again later");
    }
    return numRowsAffected;
  }
  /**
   * DAO Method to retreive all User objects
   * @return List of User
   * @author Jonathan Beck
   */
  public  List<User> getAllUser(int limit, int offset,String Role_ID) {
    List<User> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_User(?,?,?)}")) {
          statement.setInt(1,limit)
          ;statement.setInt(2,offset);
          statement.setString(3,Role_ID);
          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {String User_ID = resultSet.getString("User_User_ID");
              String _Role_ID = resultSet.getString("User_Role_ID");
              String First_Name = resultSet.getString("User_First_Name");
              String Last_Name = resultSet.getString("User_Last_Name");
              String Email = resultSet.getString("User_Email");
              Timestamp Last = resultSet.getTimestamp("User_Last_Logged_In");
              DateTime Last_Logged_In = null;
              if (Last!=null) {
                Last_Logged_In = new DateTime(resultSet.getTimestamp("User_Last_Logged_In"));
              }
              char[] Password = null;

              User _user = new User( User_ID, _Role_ID, First_Name, Last_Name, Email, Last_Logged_In, Password);
              result.add(_user);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Users. Try again later");
    }
    return result;
  }

  @Override
  public String getPassword(String username) throws SQLException {
    String result = "";
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_get_password(?)}")) {
          statement.setString(1,username);

          try(ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {

              result = resultSet.getString("user_password");
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not verify password. Try again later");
    }
    return result;
  }

  @Override
  public List<String> getDistinctRoleForDropdown(){
    List<String> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Role()}")) {

          try(ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
              String Role_ID = resultSet.getString("Role_Role_ID");

              result.add(Role_ID);
            }
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Roles. Try again later");
    }
    return result;
  }


//
//  @Override
//  public int getUserID(String email) throws SQLException {
//    return 0;
//  }
//
 @Override
public User getUserByUserID(User _user) throws SQLException{
   User result = null;
   try(Connection connection = getConnection()) {
     try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_User(?)}")) {
       statement.setString(1, _user.getUser_ID().toString());

       try (ResultSet resultSet = statement.executeQuery()){
         if(resultSet.next()){String User_ID = resultSet.getString("User_User_ID");
           String Role_ID = resultSet.getString("User_Role_ID");
           String First_Name = resultSet.getString("User_First_Name");
           String Last_Name = resultSet.getString("User_Last_Name");
           String Email = resultSet.getString("User_Email");
           Timestamp Last = resultSet.getTimestamp("User_Last_Logged_In");
           DateTime Last_Logged_In = null;
           if (Last!=null) {
             Last_Logged_In = new DateTime(resultSet.getTimestamp("User_Last_Logged_In"));
           }

           result = new User( User_ID, Role_ID, First_Name, Last_Name, Email, Last_Logged_In, null);}
       }
     }
   } catch (SQLException e) {
     throw new RuntimeException(e);
   }
   return result;
 }

//
//  @Override
//  public List<User> getAllUser() throws SQLException {
//    return null;
//  }
//
//  @Override
//  public int activate(int _userID)throws SQLException {
//    return 0;
//  }
//
//  @Override
//  public String get_pw(String username)throws SQLException {
//    return null;
//  }
//
//  @Override
//  public int getUserIDByUserName(String username) throws SQLException {
//    return 0;
//  }
//
  @Override
public boolean usernameFree(String username) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_check_username_avail(? )}"))
        {
          statement.setString(1,username);

          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              result = (resultSet.getInt(1)==0);
            }

          }

        } catch (SQLException e) {
          throw new RuntimeException("Could complete query. Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public boolean emailFree(String email) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_check_email_avail(? )}"))
        {
          statement.setString(1,email);

          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              result = (resultSet.getInt(1)==0);
            }

          }

        } catch (SQLException e) {
          throw new RuntimeException("Could complete query. Try again later");
        }
      }
    }
    return result;
  }

  @Override
  public boolean updatePassword(User _user) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_User_password(?, ?)}")){
          statement.setString(1,_user.getUser_ID());
          String hashPassword = BCrypt.hashpw(String.valueOf(_user.getPassword()), BCrypt.gensalt(12));
          statement.setString(2,hashPassword);

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not update Password. Try again later (1)");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add update Password. Try again later(2)");
    }
    return numRowsAffected==1;
  }

  @Override
  public int changeRole(String userID, String Role) throws SQLException {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_Role(?, ?)}")){
          statement.setString(1,userID);
          statement.setString(2,Role);


          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not update Role. Try again later (1)");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add update Role. Try again later(2)");
    }
    return numRowsAffected;
  }

  /**
   * * DAO Method to update User objects
   * * @param user the User to be updated
   * * @return number of records updated
   * * @author Jonathan Beck
   *  */
  public int updateLastLoggedIn(User user) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_User_last_log_in(? ,?)}"))
        {
          statement.setString(1,user.getUser_ID());
          statement.setTimestamp(2,new java.sql.Timestamp(user.getLast_Logged_In().toDate().getTime()));
          result = statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update User . Try again later");
        }
      }
    }
    return result;

  }

  /**
   * DAO Method to update User objects
   * @param oldUser the User to be updated
   * @param newUser the updated version of the User
   * @return number of records updated
   * @author Jonathan Beck
   */

  public int update(User oldUser, User newUser) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_User(?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setString(1,oldUser.getUser_ID());
          statement.setString(2,oldUser.getRole_ID());
          statement.setString(3,newUser.getRole_ID());
          statement.setString(4,oldUser.getFirst_Name());
          statement.setString(5,newUser.getFirst_Name());
          statement.setString(6,oldUser.getLast_Name());
          statement.setString(7,newUser.getLast_Name());
          statement.setString(8,oldUser.getEmail());
          statement.setString(9,newUser.getEmail());


          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update User . Try again later");
        }
      }
    }
    return result;
  }

//
//  @Override
//  public boolean deleteUser(int userID) throws SQLException {
//    return false;
//  }
//
//  @Override
//  public boolean resetPW(User user) throws SQLException {
//    return false;
//  }
}
