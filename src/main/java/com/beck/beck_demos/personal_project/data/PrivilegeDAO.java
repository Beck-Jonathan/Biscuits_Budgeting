package com.beck.beck_demos.personal_project.data;

/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines PrivilegeDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.Privilege;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class PrivilegeDAO {

  public static int add(Privilege _privilege) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Privilege( ?)}")){
          statement.setString(1,_privilege.getName());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Privilege. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Privilege. Try again later");
    }
    return numRowsAffected;
  }
  public static Privilege getPrivilegeByPrimaryKey(Privilege _privilege) throws SQLException{
    Privilege result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Privilege(?)}")) {
        statement.setString(1, _privilege.getPrivilege_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Privilege_ID = resultSet.getInt("Privilege_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Privilege( Privilege_ID, Name, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Privilege> getAllPrivilege() {
    List<Privilege> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Privilege()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Privilege_ID = resultSet.getInt("Privilege_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Privilege _privilege = new Privilege( Privilege_ID, Name, is_active);
            result.add(_privilege);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Privileges. Try again later");
    }
    return result;}
  public static List<Privilege> getActivePrivilege() {
    List<Privilege> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Privilege()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Privilege_ID = resultSet.getInt("Privilege_ID");
            String Name = resultSet.getString("Name");
            boolean is_active = resultSet.getBoolean("is_active");
            Privilege _privilege = new Privilege( Privilege_ID, Name, is_active);
            result.add(_privilege);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Privileges. Try again later");
    }
    return result;}

  public static int update(Privilege oldPrivilege, Privilege newPrivilege) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Privilege(? ,?,?,?,?)}"))
        {
          statement.setInt(1,oldPrivilege.getPrivilege_ID());
          statement.setString(2,oldPrivilege.getName());
          statement.setString(3,newPrivilege.getName());
          statement.setBoolean(4,oldPrivilege.getis_active());
          statement.setBoolean(5,newPrivilege.getis_active());
          statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Privilege . Try again later");
        }
      }
    }
    return result;
  }
  public static int deletePrivilege(int privilegeID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Privilege( ?)}")){
          statement.setInt(1,privilegeID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Privilege. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Privilege. Try again later");
    }
    return rowsAffected;
  }
  public static int undeletePrivilege(int privilegeID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Privilege( ?)}")){
          statement.setInt(1,privilegeID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            //throw new RuntimeException("Could not Restore Privilege. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Privilege. Try again later");
    }
    return rowsAffected;
  }

}


