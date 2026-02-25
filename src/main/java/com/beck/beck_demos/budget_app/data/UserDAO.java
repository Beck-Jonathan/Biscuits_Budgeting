package com.beck.beck_demos.budget_app.data;

import com.beck.beck_demos.budget_app.iData.iUserDAO;
import com.beck.beck_demos.budget_app.models.*;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.beck.beck_demos.budget_app.data.Database.getConnection;

public class UserDAO implements iUserDAO {

  public int add(User _user) {
    int numRowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_User( ?, ?, ?)}")){
          statement.setString(1,_user.getUser_Name());
          String hashPassword = BCrypt.hashpw(String.valueOf(_user.getUser_PW()), BCrypt.gensalt(12));
          statement.setString(2,hashPassword);

          statement.setString(3,_user.getEmail());

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

  public String getUserID(String email) throws SQLException{
    String userId = "";
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_user_id_by_email(?)}")) {
        statement.setString(1,email);

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            userId=resultSet.getString(1);
          }
        }
        catch (SQLException ex ){

          throw new RuntimeException(ex);}
      }


    }
    return userId;
  }

  public  User getUserByPrimaryKey(User _user) throws SQLException{
    User result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_User(?)}")) {
        statement.setString(1, _user.getUser_ID());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){
            String User_ID = resultSet.getString("User_ID");
            String User_Name = resultSet.getString("User_Name");
            char[] User_PW = resultSet.getString("User_PW").toCharArray();

            String Email = resultSet.getString("Email");

            result = new User( User_ID, User_Name, User_PW,  Email );}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }



  public  String get_pw(String username) {
    String pw="";
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_get_pw( ?)}")){
          statement.setString(1,username);



          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              pw = resultSet.getString(1);
            }
          }

          if (pw.equals( "")) {
            throw new RuntimeException("Could not Login User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Login User. Try again later");
    }
    return pw;
  }

  public String getUserIDByUserName(String username) {
    String id="";
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_user_id_by_username( ?)}")){
          statement.setString(1,username);



          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              id = resultSet.getString(1);
            }

          }

          if (id.equals("")) {
            throw new RuntimeException("Could not Login User. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Login User. Try again later");
    }
    return id;
  }


  public  boolean usernameFree(String username) throws SQLException {
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

  public  boolean addDefaultCategories(String userID) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_create_default_categories(? )}")) {
          statement.setString(1, userID);

          try {
            int results = statement.executeUpdate();

            if (results < 1) {
              result = false;
            }

          } catch (SQLException e) {
            throw new RuntimeException("Could complete query. Try again later");
          }
        }
      }

    }
    return result;
  }

  public  boolean emailFree(String email) throws SQLException {
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



  public  boolean deleteUser(int userID) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_delete_user(? )}")) {
          statement.setInt(1, userID);

          try {
            int value = statement.executeUpdate();
            result = (value == 1);
          } catch (SQLException e) {
            throw new RuntimeException("Could delete user. Try again later");
          }
        }
      }
      return result;
    }
  }
  public  boolean resetPW(User user) throws SQLException {
    boolean result = true;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_update_password(?,? )}")) {
          statement.setString(1, user.getUser_Name());
          String hashPassword = BCrypt.hashpw(String.valueOf(user.getUser_PW()), BCrypt.gensalt(12));

          statement.setString(2,hashPassword);

          try {
            int value = statement.executeUpdate();
            result = (value == 1);
          } catch (SQLException e) {
            throw new RuntimeException("Could update password. Try again later");
          }
        }
      }
      return result;
    }

  }
  public  List<String> getUser_Roles(User _user) throws SQLException{
    List<String> result = new ArrayList<>();
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_User_User_Role_Line(?)}")) {

        statement.setString(1, _user.getUser_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          while(resultSet.next()){
            String Role_ID = resultSet.getString("User_Role_Line_Role_ID");
            result.add(Role_ID);

          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public  int addRole(String role, String userID ) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_User_Role_Line( ?, ?)}")){
          statement.setString(1,role);
          statement.setString(2,userID);
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add User_Role_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add User_Role_Line. Try again later");
    }
    return numRowsAffected;
  }

  public  int yearRange(int user_ID) throws SQLException {
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_user_transaction_time_range(? )}"))
        {
          statement.setInt(1,user_ID);

          try (ResultSet resultSet = statement.executeQuery()){
            if(resultSet.next()) {
              result = resultSet.getInt(1);
            }

          }

        } catch (SQLException e) {
          throw new RuntimeException("Could complete query. Try again later");
        }
      }
    }
    return result;
  }


}