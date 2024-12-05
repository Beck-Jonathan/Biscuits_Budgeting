package com.beck.beck_demos.personal_project.data;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines EventDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.Event;
import com.beck.beck_demos.personal_project.models.EventVM;
import com.beck.beck_demos.personal_project.models.Facility;
import com.beck.beck_demos.personal_project.models.Type;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class EventDAO {

  public static int add(Event _event) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Event( ?, ?, ?)}")){
          statement.setInt(1,_event.getFacility_ID());
          statement.setDate(2, Date.valueOf(_event.getDate()));
          statement.setInt(3,_event.getType_ID());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Event. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Event. Try again later");
    }
    return numRowsAffected;
  }
  public static EventVM getEventByPrimaryKey(Event _event) throws SQLException{
    EventVM result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Event(?)}")) {
        statement.setString(1, _event.getEvent_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          ResultSetMetaData rsmd = resultSet.getMetaData();
          for (int i=1;i<rsmd.getColumnCount()-1;i++) {
            System.out.println(rsmd.getColumnLabel(i));
          }
          if(resultSet.next()){
            Integer Event_ID = resultSet.getInt("Event_Event_ID");
            Integer Facility_ID = resultSet.getInt("Event_Facility_ID");
            LocalDate Date = resultSet.getDate("Event_Date").toLocalDate();
            Integer Type_ID = resultSet.getInt("Event_Type_ID");
            boolean is_active = resultSet.getBoolean("Event_is_active");
            Integer Facility_Facility_ID = resultSet.getInt("Facility_Facility_ID");
            String Facility_Name = resultSet.getString("Facility_Name");
            String Facility_Addresss = resultSet.getString("Facility_Addresss");
            String Facility_City = resultSet.getString("Facility_City");
            String Facility_State = resultSet.getString("Facility_State");
            String Facility_Zip = resultSet.getString("Facility_Zip");
            boolean Facility_is_active = resultSet.getBoolean("Facility_is_active");
            Facility facility = new Facility(Facility_Facility_ID,Facility_Name,Facility_Addresss,Facility_City,Facility_State,Facility_Zip,Facility_is_active);
            Integer Type_Type_ID = resultSet.getInt("Type_Type_ID");
            String Type_Name = resultSet.getString("Type_Name");
            boolean Type_is_active = resultSet.getBoolean("Type_is_active");
            Type type = new Type(Type_Type_ID,Type_Name,Type_is_active);
            result = new EventVM( Event_ID, Facility_ID, Date, Type_ID, is_active,facility,type);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<EventVM> getAllEvent() {
    List<EventVM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Event()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            Integer Event_ID = resultSet.getInt("Event_Event_ID");
            Integer Facility_ID = resultSet.getInt("Event_Facility_ID");
            LocalDate Date = resultSet.getDate("Event_Date").toLocalDate();
            Integer Type_ID = resultSet.getInt("Event_Type_ID");
            boolean is_active = resultSet.getBoolean("Event_is_active");
            Integer Facility_Facility_ID = resultSet.getInt("Facility_Facility_ID");
            String Facility_Name = resultSet.getString("Facility_Name");
            String Facility_Addresss = resultSet.getString("Facility_Addresss");
            String Facility_City = resultSet.getString("Facility_City");
            String Facility_State = resultSet.getString("Facility_State");
            String Facility_Zip = resultSet.getString("Facility_Zip");
            boolean Facility_is_active = resultSet.getBoolean("Facility_is_active");
            Facility facility = new Facility(Facility_Facility_ID,Facility_Name,Facility_Addresss,Facility_City,Facility_State,Facility_Zip,Facility_is_active);
            Integer Type_Type_ID = resultSet.getInt("Type_Type_ID");
            String Type_Name = resultSet.getString("Type_Name");
            boolean Type_is_active = resultSet.getBoolean("Type_is_active");
            Type type = new Type(Type_Type_ID,Type_Name,Type_is_active);
            EventVM _event = new EventVM( Event_ID, Facility_ID, Date, Type_ID, is_active,facility,type);
            result.add(_event);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Events. Try again later");
    }
    return result;}
  public static List<EventVM> getActiveEvent() {
    List<EventVM> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Event()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {
            Integer Event_ID = resultSet.getInt("Event_Event_ID");
            Integer Facility_ID = resultSet.getInt("Event_Facility_ID");
            LocalDate Date = resultSet.getDate("Event_Date").toLocalDate();
            Integer Type_ID = resultSet.getInt("Event_Type_ID");
            boolean is_active = resultSet.getBoolean("Event_is_active");
            Integer Facility_Facility_ID = resultSet.getInt("Facility_Facility_ID");
            String Facility_Name = resultSet.getString("Facility_Name");
            String Facility_Addresss = resultSet.getString("Facility_Addresss");
            String Facility_City = resultSet.getString("Facility_City");
            String Facility_State = resultSet.getString("Facility_State");
            String Facility_Zip = resultSet.getString("Facility_Zip");
            boolean Facility_is_active = resultSet.getBoolean("Facility_is_active");
            Facility facility = new Facility(Facility_Facility_ID,Facility_Name,Facility_Addresss,Facility_City,Facility_State,Facility_Zip,Facility_is_active);
            Integer Type_Type_ID = resultSet.getInt("Type_Type_ID");
            String Type_Name = resultSet.getString("Type_Name");
            boolean Type_is_active = resultSet.getBoolean("Type_is_active");
            Type type = new Type(Type_Type_ID,Type_Name,Type_is_active);
            EventVM _event = new EventVM( Event_ID, Facility_ID, Date, Type_ID, is_active,facility,type);
            result.add(_event);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Events. Try again later");
    }
    return result;}

  public static int update(Event oldEvent, Event newEvent) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Event(? ,?,?,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldEvent.getEvent_ID());
          statement.setInt(2,oldEvent.getFacility_ID());
          statement.setInt(3,newEvent.getFacility_ID());
          statement.setDate(4, Date.valueOf(oldEvent.getDate()));
          statement.setDate(5, Date.valueOf(newEvent.getDate()));
          statement.setInt(6,oldEvent.getType_ID());
          statement.setInt(7,newEvent.getType_ID());
          statement.setBoolean(8,oldEvent.getis_active());
          statement.setBoolean(9,newEvent.getis_active());
          result=statement.executeUpdate();
        } catch (SQLException e) {
          throw new RuntimeException("Could not update Event . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteEvent(int eventID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Event( ?)}")){
          statement.setInt(1,eventID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Event. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Event. Try again later");
    }
    return rowsAffected;
  }
  public static int undeleteEvent(int eventID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Event( ?)}")){
          statement.setInt(1,eventID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Event. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Event. Try again later");
    }
    return rowsAffected;
  }

}

