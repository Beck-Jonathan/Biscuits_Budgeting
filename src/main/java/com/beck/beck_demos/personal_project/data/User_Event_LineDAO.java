package com.beck.beck_demos.personal_project.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines User_Event_LineDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.User_Event_Line;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class User_Event_LineDAO {

  public static int add(User_Event_Line _user_event_line) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_User_Event_Line( ?, ?) }")){
          statement.setInt(1,_user_event_line.getUser_ID());
          statement.setInt(2,_user_event_line.getEvent_ID());

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add User_Event_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add User_Event_Line. Try again later");
    }
    return numRowsAffected;
  }
  public static User_Event_Line getUser_Event_LineByPrimaryKey(User_Event_Line _user_event_line) throws SQLException{
    User_Event_Line result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_User_Event_Line(?)}")) {
        statement.setString(1, _user_event_line.getUser_ID().toString());
        statement.setString(1, _user_event_line.getEvent_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer User_ID = resultSet.getInt("User_ID");
            Integer Event_ID = resultSet.getInt("Event_ID");
            LocalDate Date_assgined = resultSet.getDate("Date_assgined").toLocalDate();
            boolean is_active = resultSet.getBoolean("is_active");
            result = new User_Event_Line( User_ID, Event_ID, Date_assgined, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<User_Event_Line> getAllUser_Event_Line() {
    List<User_Event_Line> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_User_Event_Line()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer User_ID = resultSet.getInt("User_ID");
            Integer Event_ID = resultSet.getInt("Event_ID");
            LocalDate Date_assgined = resultSet.getDate("Date_assgined").toLocalDate();
            boolean is_active = resultSet.getBoolean("is_active");
            User_Event_Line _user_event_line = new User_Event_Line( User_ID, Event_ID, Date_assgined, is_active);
            result.add(_user_event_line);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve User_Event_Lines. Try again later");
    }
    return result;}
  public static List<User_Event_Line> getActiveUser_Event_Line() {
    List<User_Event_Line> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_User_Event_Line()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer User_ID = resultSet.getInt("User_ID");
            Integer Event_ID = resultSet.getInt("Event_ID");
            LocalDate Date_assgined = resultSet.getDate("Date_assgined").toLocalDate();
            boolean is_active = resultSet.getBoolean("is_active");
            User_Event_Line _user_event_line = new User_Event_Line( User_ID, Event_ID, Date_assgined, is_active);
            result.add(_user_event_line);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve User_Event_Lines. Try again later");
    }
    return result;}

  public static int update(User_Event_Line oldUser_Event_Line, User_Event_Line newUser_Event_Line) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_User_Event_Line(? ,? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldUser_Event_Line.getUser_ID());
          statement.setInt(2,oldUser_Event_Line.getEvent_ID());
          statement.setDate(3, Date.valueOf(oldUser_Event_Line.getDate_assgined()));
          statement.setDate(4, Date.valueOf(newUser_Event_Line.getDate_assgined()));
          statement.setBoolean(5,oldUser_Event_Line.getis_active());
          statement.setBoolean(6,newUser_Event_Line.getis_active());
          statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update User_Event_Line . Try again later");
        }
      }
    }
    return result;
  }
  public static int remove(User_Event_Line _user_event_line) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_remove_User_Event_Line( ?, ?) }")){
          statement.setInt(1,_user_event_line.getUser_ID());
          statement.setInt(2,_user_event_line.getEvent_ID());

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add User_Event_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add User_Event_Line. Try again later");
    }
    return numRowsAffected;
  }
  public static int undeleteUser_Event_Line(int user_event_lineID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_User_Event_Line( ?)}")){
          statement.setInt(1,user_event_lineID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore User_Event_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore User_Event_Line. Try again later");
    }
    return rowsAffected;
  }

}

