package com.beck.beck_demos.personal_project.data;/// <summary>
///AUTHOR: Jonathan Beck
///<br />
///CREATED: 18/3/2024
///< br />
///An example class to show how code is expected to be written and documented.
///This is where a description of what your file is supposed to contain goes.
///e.g., "Class with helper methods for input validation.",
///Class that defines FacilityDAO Objects.
///</summary>
///< remarks>
///UPDATER: updater_name
///< br />
/// UPDATED: yyyy-MM-dd
/// < br />
/// Update comments go here, include method or methods were changed or added
/// A new remark should be added for each update.
///</remarks>
import com.beck.beck_demos.personal_project.models.Facility;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.beck.beck_demos.personal_project.data.Database.getConnection;
public class FacilityDAO {

  public static int add(Facility _facility) {
    int numRowsAffected=0;try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_insert_Facility( ?, ?, ?, ?, ?)}")){
          statement.setString(1,_facility.getName());
          statement.setString(2,_facility.getAddresss());
          statement.setString(3,_facility.getCity());
          statement.setString(4,_facility.getState());
          statement.setString(5,_facility.getZip());
          numRowsAffected = statement.executeUpdate();
          if (numRowsAffected == 0) {
            throw new RuntimeException("Could not add Facility. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not add Facility. Try again later");
    }
    return numRowsAffected;
  }
  public static Facility getFacilityByPrimaryKey(Facility _facility) throws SQLException{
    Facility result = null;
    try(Connection connection = getConnection()) {
      try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_pk_Facility(?)}")) {
        statement.setString(1, _facility.getFacility_ID().toString());

        try (ResultSet resultSet = statement.executeQuery()){
          if(resultSet.next()){Integer Facility_ID = resultSet.getInt("Facility_ID");
            String Name = resultSet.getString("Name");
            String Addresss = resultSet.getString("Addresss");
            String City = resultSet.getString("City");
            String State = resultSet.getString("State");
            String Zip = resultSet.getString("Zip");
            boolean is_active = resultSet.getBoolean("is_active");
            result = new Facility( Facility_ID, Name, Addresss, City, State, Zip, is_active);}
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return result;
  }
  public static List<Facility> getAllFacility() {
    List<Facility> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_all_Facility()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Facility_ID = resultSet.getInt("Facility_ID");
            String Name = resultSet.getString("Name");
            String Addresss = resultSet.getString("Addresss");
            String City = resultSet.getString("City");
            String State = resultSet.getString("State");
            String Zip = resultSet.getString("Zip");
            boolean is_active = resultSet.getBoolean("is_active");
            Facility _facility = new Facility( Facility_ID, Name, Addresss, City, State, Zip, is_active);
            result.add(_facility);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Facilitys. Try again later");
    }
    return result;}
  public static List<Facility> getActiveFacility() {
    List<Facility> result = new ArrayList<>();
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try(CallableStatement statement = connection.prepareCall("{CALL sp_retreive_by_active_Facility()}")) {try(ResultSet resultSet = statement.executeQuery()) {
          while (resultSet.next()) {Integer Facility_ID = resultSet.getInt("Facility_ID");
            String Name = resultSet.getString("Name");
            String Addresss = resultSet.getString("Addresss");
            String City = resultSet.getString("City");
            String State = resultSet.getString("State");
            String Zip = resultSet.getString("Zip");
            boolean is_active = resultSet.getBoolean("is_active");
            Facility _facility = new Facility( Facility_ID, Name, Addresss, City, State, Zip, is_active);
            result.add(_facility);
          }
        }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not retrieve Facilitys. Try again later");
    }
    return result;}

  public static int update(Facility oldFacility, Facility newFacility) throws SQLException{
    int result = 0;
    try (Connection connection = getConnection()) {
      if (connection !=null){
        try(CallableStatement statement = connection.prepareCall("{CALL sp_update_Facility(? ,?,?,?,?,?,?,?,?,?,?,?,?)}"))
        {
          statement.setInt(1,oldFacility.getFacility_ID());
          statement.setString(2,oldFacility.getName());
          statement.setString(3,newFacility.getName());
          statement.setString(4,oldFacility.getAddresss());
          statement.setString(5,newFacility.getAddresss());
          statement.setString(6,oldFacility.getCity());
          statement.setString(7,newFacility.getCity());
          statement.setString(8,oldFacility.getState());
          statement.setString(9,newFacility.getState());
          statement.setString(10,oldFacility.getZip());
          statement.setString(11,newFacility.getZip());
          statement.setBoolean(12,oldFacility.getis_active());
          statement.setBoolean(13,newFacility.getis_active());
          result=statement.executeUpdate();
        } catch (Exception e) {
          throw new RuntimeException("Could not update Facility . Try again later");
        }
      }
    }
    return result;
  }
  public static int deleteFacility(int facilityID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_Delete_Facility( ?)}")){
          statement.setInt(1,facilityID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Delete Facility. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Delete Facility. Try again later");
    }
    return rowsAffected;
  }
  public static int undeleteFacility(int facilityID) {
    int rowsAffected=0;
    try (Connection connection = getConnection()) {
      if (connection != null) {
        try (CallableStatement statement = connection.prepareCall("{CALL sp_unDelete_Facility( ?)}")){
          statement.setInt(1,facilityID);
          rowsAffected = statement.executeUpdate();
          if (rowsAffected == 0) {
            throw new RuntimeException("Could not Restore Facility. Try again later");
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Could not Restore Facility. Try again later");
    }
    return rowsAffected;
  }

}

