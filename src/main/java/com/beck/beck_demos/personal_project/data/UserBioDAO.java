package com.beck.beck_demos.personal_project.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 5/5/2024
///< br />

import com.beck.beck_demos.personal_project.models.UserBio;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class UserBioDAO {

  public static int add(UserBio _user_bio) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_User_Bio( ?, ?,?,?)}")){
          statement.setInt(1,_user_bio.getUser_ID());
          statement.setString(2, _user_bio.getPosition());
          statement.setString(3,_user_bio.getBio());
          statement.setDate(4, new java.sql.Date(_user_bio.getBegan_Skating().getTime()));
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add User_Bio. Try again later");
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException("Could not add User_Bio. Try again later");
    }
    return numRowsAffected;
  }
  public static UserBio getUser_BioByPrimaryKey(int userID) throws SQLException{
    UserBio result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_User_Bio(?)}")) {
        statement.setInt(1, userID);

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer User_ID = resultSet.getInt("User_Bio_User_ID");
            String Postion = resultSet.getString("User_Bio_position");
            String Bio = resultSet.getString("User_Bio_Bio");
            Date Began_Skating = resultSet.getDate("User_Bio_Began_Skating");

            result = new UserBio( User_ID, Postion, Bio, Began_Skating);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }

  public static int update(UserBio oldUser_Bio, UserBio newUser_Bio) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_User_Bio(? ,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldUser_Bio.getUser_ID());
          statement.setString(2,oldUser_Bio.getPosition());
          statement.setString(3,newUser_Bio.getPosition());
          statement.setString(4,oldUser_Bio.getBio());
          statement.setString(5,newUser_Bio.getBio());
          statement.setDate(6, new java.sql.Date(oldUser_Bio.getBegan_Skating().getTime()));
          statement.setDate(7, new java.sql.Date(newUser_Bio.getBegan_Skating().getTime()));
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update User_Bio . Try again later");
        }
      }
    }
    return result;
  }

  public static boolean checkForBio(int userID) throws SQLException {
    boolean result = false;
    try (Connection connection = getConnection()) {
      try (CallableStatement statement = connection.prepareCall("{CALL sp_check_for_User_Bio(?)}")) {
        statement.setInt(1, userID);

        try (ResultSet resultSet = statement.executeQuery()) {
          if (resultSet.next()) {
            int count = resultSet.getInt(1);

            result = (count == 1);
          }
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

    }
    return result;
  }



}

