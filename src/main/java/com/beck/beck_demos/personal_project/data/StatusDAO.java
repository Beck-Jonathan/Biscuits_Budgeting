package com.beck.beck_demos.personal_project.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines StatusDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.Status;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class StatusDAO {

  public static int add(Status _status) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Status( ?)}")){
          statement.setString(1,_status.getName());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Status. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Status. Try again later");
    }
    return numRowsAffected;
  }
  public static Status getStatusByPrimaryKey(Status _status) throws SQLException{
    Status result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Status(?)}")) {
        statement.setString(1, _status.getStatus_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Status_ID = resultSet.getInt("Status_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Status( Status_ID, Name, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Status> getAllStatus() {
    List<Status> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Status()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Status_ID = resultSet.getInt("Status_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Status _status = new Status( Status_ID, Name, is_active);
            result.add(_status);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Statuss. Try again later");
    }
    return result;}
  public static List<Status> getActiveStatus() {
    List<Status> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Status()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Status_ID = resultSet.getInt("Status_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Status _status = new Status( Status_ID, Name, is_active);
            result.add(_status);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Statuss. Try again later");
    }
    return result;}

  public static int update(Status oldStatus, Status newStatus) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Status(? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldStatus.getStatus_ID());
          statement.setString(2,oldStatus.getName());
          statement.setString(3,newStatus.getName());
          statement.setBoolean(4,oldStatus.getis_active());
          statement.setBoolean(5,newStatus.getis_active());
          statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Status . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteStatus(int statusID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Status( ?)}")){
          statement.setInt(1,statusID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Status. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Status. Try again later");
    }
    return rowsAffected;
  }
  public static int undeleteStatus(int statusID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Status( ?)}")){
          statement.setInt(1,statusID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Status. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Status. Try again later");
    }
    return rowsAffected;
  }

}


