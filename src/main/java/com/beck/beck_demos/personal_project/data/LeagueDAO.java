package com.beck.beck_demos.personal_project.data;
/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines LeagueDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.League;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class LeagueDAO {

  public static int add(League _league) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_League( ?, ?, ?, ?, ?)}")){
          statement.setString(1,_league.getLeague_Name());
          statement.setString(2,_league.getLeague_Level());
          statement.setDouble(3,_league.getMonthly_Due());
          statement.setString(4,_league.getActive_Days());
          statement.setString(5,_league.getDescription());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add League. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add League. Try again later");
    }
    return numRowsAffected;
  }
  public static League getLeagueByPrimaryKey(League _league) throws SQLException{
    League result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_League(?)}")) {
        statement.setString(1, _league.getLeague_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer League_ID = resultSet.getInt("League_ID");
            String League_Name = resultSet.getString("League_Name");
            String League_Level = resultSet.getString("League_Level");
            Double Monthly_Due = resultSet.getDouble("Monthly_Due");
            String Active_Days = resultSet.getString("Active_Days");
            String Description = resultSet.getString("Description");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new League( League_ID, League_Name, League_Level, Monthly_Due, Active_Days, Description, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<League> getAllLeague() {
    List<League> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_League()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer League_ID = resultSet.getInt("League_ID");
            String League_Name = resultSet.getString("League_Name");
            String League_Level = resultSet.getString("League_Level");
            Double Monthly_Due = resultSet.getDouble("Monthly_Due");
            String Active_Days = resultSet.getString("Active_Days");
            String Description = resultSet.getString("Description");
            boolean is_active = resultSet.getBoolean("is_active");
            League _league = new League( League_ID, League_Name, League_Level, Monthly_Due, Active_Days, Description, is_active);
            result.add(_league);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Leagues. Try again later");
    }
    return result;}
  public static List<League> getActiveLeague() {
    List<League> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_League()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer League_ID = resultSet.getInt("League_ID");
            String League_Name = resultSet.getString("League_Name");
            String League_Level = resultSet.getString("League_Level");
            Double Monthly_Due = resultSet.getDouble("Monthly_Due");
            String Active_Days = resultSet.getString("Active_Days");
            String Description = resultSet.getString("Description");
            boolean is_active = resultSet.getBoolean("is_active");
            League _league = new League( League_ID, League_Name, League_Level, Monthly_Due, Active_Days, Description, is_active);
            result.add(_league);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Leagues. Try again later");
    }
    return result;}

  public static int update(League oldLeague, League newLeague) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_League(? ,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldLeague.getLeague_ID());
          statement.setString(2,oldLeague.getLeague_Name());
          statement.setString(3,newLeague.getLeague_Name());
          statement.setString(4,oldLeague.getLeague_Level());
          statement.setString(5,newLeague.getLeague_Level());
          statement.setDouble(6,oldLeague.getMonthly_Due());
          statement.setDouble(7,newLeague.getMonthly_Due());
          statement.setString(8,oldLeague.getActive_Days());
          statement.setString(9,newLeague.getActive_Days());
          statement.setString(10,oldLeague.getDescription());
          statement.setString(11,newLeague.getDescription());
          statement.setBoolean(12,oldLeague.getis_active());
          statement.setBoolean(13,newLeague.getis_active());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update League . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteLeague(int leagueID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_League( ?)}")){
          statement.setInt(1,leagueID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete League. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete League. Try again later");
    }
    return rowsAffected;
  }
  public static int undeleteLeague(int leagueID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_League( ?)}")){
          statement.setInt(1,leagueID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore League. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore League. Try again later");
    }
    return rowsAffected;
  }

}



