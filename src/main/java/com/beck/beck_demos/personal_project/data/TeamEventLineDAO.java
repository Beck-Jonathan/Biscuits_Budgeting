package com.beck.beck_demos.personal_project.data;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 4/5/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines Team_Event_LineDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.TeamEventLine;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class TeamEventLineDAO {

  public static int add(TeamEventLine _team_event_line) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Team_Event_Line( ?, ?)}")){
          statement.setInt(1,_team_event_line.getTeam_ID());
          statement.setInt(2,_team_event_line.getEvent_ID());

          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Team_Event_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Team_Event_Line. Try again later");
    }
    return numRowsAffected;
  }

  public static int deleteTeam_Event_Line(TeamEventLine _team_event_line) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Team_Event_Line( ?,?)}")){
          statement.setInt(1,_team_event_line.getTeam_ID());
          statement.setInt(2,_team_event_line.getEvent_ID());
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Team_Event_Line. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Team_Event_Line. Try again later");
    }
    return rowsAffected;
  }


}

